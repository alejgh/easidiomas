package easidiomas.chatservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import easidiomas.chatservice.model.ChatDTO;

@Service
public interface ChatsService {

	List<ChatDTO> getChats(String user);

	ChatDTO insert(ChatDTO chat);

	ChatDTO save(ChatDTO chat);

	ChatDTO findChatById(String id);
	
	ChatDTO findChatByUser1AndUser2(String user1, String user2);

}
