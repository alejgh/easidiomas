package easidiomas.chatservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easidiomas.chatservice.model.ChatDTO;
import easidiomas.chatservice.repository.ChatsRepository;

@Service
public class ChatsServiceImpl implements ChatsService {

	@Autowired
	private ChatsRepository chatsRepository;

	@Override
	public List<ChatDTO> getChats(String user) {
		return chatsRepository.findAllChatsByUser1OrUser2(user, user);
	}

	@Override
	public ChatDTO insert(ChatDTO chat) {
		return chatsRepository.insert(chat);
	}
	
	@Override
	public ChatDTO save(ChatDTO chat) {
		return chatsRepository.save(chat);
	}

	@Override
	public ChatDTO findChatById(String id) {
		return chatsRepository.findChatById(id);
	}

	@Override
	public ChatDTO findChatByUser1AndUser2(String user1, String user2) {
		return chatsRepository.findChatByUser1AndUser2(user1, user2);
	}

}
