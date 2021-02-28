package com.easidiomas.chatservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.easidiomas.chatservice.model.ChatDTO;

@Repository
public interface ChatsRepository extends MongoRepository<ChatDTO, String> {

	ChatDTO findChatById(String id);

	List<ChatDTO> findAllChatsByUser1OrUser2(String user1, String user2);
	
	ChatDTO findChatByUser1AndUser2(String user1, String user2);

}