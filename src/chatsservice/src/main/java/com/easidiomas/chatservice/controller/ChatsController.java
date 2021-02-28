package com.easidiomas.chatservice.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easidiomas.chatservice.clients.statisticsservice.IStatisticsService;
import com.easidiomas.chatservice.clients.statisticsservice.IStatisticsServiceService;
import com.easidiomas.chatservice.model.ChatDTO;
import com.easidiomas.chatservice.model.Message;
import com.easidiomas.chatservice.model.Passport;
import com.easidiomas.chatservice.services.ChatsService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@RestController
@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET })
@RequestMapping("/api/chats")
public class ChatsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChatsController.class);
	private static final String STATS_SERVICE_WDSL = System.getenv("STATS_SERVICE_WDSL")!=null ? System.getenv("STATS_SERVICE_WDSL"): "http://localhost:5000/soapws/statistics?wsdl";

	@Autowired
	private ChatsService chatsService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getUserChats(HttpServletRequest request) {		

		Passport passport = getPassport(request);
		if (passport == null) {
			LOGGER.error(String.format("Missing passport header"));
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}

		String user = passport.getUserId();
		if (user == null) {
			LOGGER.error(String.format("Missing userId in passport header"));
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}

		LOGGER.info(String.format("Getting the chats for user [%s].", user));
		return ResponseEntity.ok(chatsService.getChats(user));

	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createChat(HttpServletRequest request, @RequestBody Map<String, String> payload)
			throws MalformedURLException {
		
		Passport passport = getPassport(request);
		if (passport == null) {
			LOGGER.error(String.format("Missing passport header"));
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}

		String user1 = passport.getUserId();
		String user2 = payload.get("user2");
		if (user1 == null || user2 == null) {
			LOGGER.error(String.format("Missing some parram"));
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}

		if (chatsService.findChatByUser1AndUser2(user1, user2) != null) {
			LOGGER.error(String.format("Chat between user [%s] and user [%s] already exists.", user1, user2));
			return new ResponseEntity<String>("Conflict", HttpStatus.CONFLICT);
		}

		LOGGER.info(String.format("Trying to create a chat between user [%s] and user [%s].", user1, user2));

		ChatDTO chat = new ChatDTO(user1, user2);
		chat = chatsService.insert(chat);
		chat.updateURL(); // the url contains the id generated after the insertion
		chat = chatsService.save(chat);
		
		// Register the created chat in the statistics service
        IStatisticsServiceService service = new IStatisticsServiceService(new URL(STATS_SERVICE_WDSL));
        IStatisticsService statisticsService = service.getIStatisticsServicePort();
        statisticsService.registerChatCreatedEvent();
		

		LOGGER.info(String.format("Chat between user [%s] and user [%s] created.", user1, user2));
		return ResponseEntity.created(URI.create("/api/chats/" + chat.getId())).body(chat);
	}

	@GetMapping("/{id}/messages")
	public ResponseEntity getChatMessages(HttpServletRequest request, @PathVariable String id) {

		Passport passport = getPassport(request);
		if (passport == null) {
			LOGGER.error(String.format("Missing passport header"));
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}

		String user = passport.getUserId();
		if (user == null) {
			LOGGER.error(String.format("Missing userURL in passport header"));
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}

		ChatDTO chat = chatsService.findChatById(id);

		if (chat == null) {
			LOGGER.error(String.format("Chat not found"));
			return new ResponseEntity<String>("NotFound", HttpStatus.NOT_FOUND);
		}

		if (hasPermission(user, chat)) {
			LOGGER.info(String.format("Getting the messages for chat [%s]", chat));
			return ResponseEntity.ok(chat.getMessagesDTO());
		}

		LOGGER.error(String.format("User [%s] doesn´t have permission for chat", user, chat));
		return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);

	}

	@PostMapping("/{id}/messages")
	public ResponseEntity addMessage(HttpServletRequest request, @PathVariable String id,
			@RequestBody Map<String, String> payload) {

		Passport passport = getPassport(request);
		if (passport == null) {
			LOGGER.error(String.format("Missing passport header"));
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}

		String user = passport.getUserId();
		String text = payload.get("text");
		String sender = passport.getUserId();
		if (user == null || text == null || sender == null) {
			LOGGER.error(String.format("Missing some parram"));
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}

		ChatDTO chat = chatsService.findChatById(id);
		if (chat == null) {
			LOGGER.error(String.format("Chat not found"));
			return new ResponseEntity<String>("NotFound", HttpStatus.NOT_FOUND);
		}

		if (hasPermission(user, chat)) {
			LOGGER.info(String.format("Trying to post a new message for chat [%s]", chat));
			Message message = new Message(text, sender);
			chat.addMessage(message);
			chatsService.save(chat);
			LOGGER.info(String.format("New message created in chat [%s]", chat));
			return ResponseEntity.created(URI.create("/api/chats/" + chat.getId() + "/messages" + message.getId()))
					.body(message);
		}
				
		LOGGER.error(String.format("User [%s] doesn´t have permission for chat", user, chat));
		return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
	}

	private boolean hasPermission(String user, ChatDTO chat) {
		return chat.getUser1().equals(user) || chat.getUser2().equals(user);
	}

	private Passport getPassport(HttpServletRequest request) {
		String passportHeader = request.getHeader("passport");
		Passport passsport = null;
		try {
			passsport = new Gson().fromJson(passportHeader, Passport.class);
			;
		} catch (JsonSyntaxException e) {
			LOGGER.error(String.format("Error when trying to parse passport"));
			e.printStackTrace();
		}
		return passsport;
	}

}
