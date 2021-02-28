package com.easidiomas.chatservice.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "chats")
public class ChatDTO {

	@Id
	private String id;
	@JsonIgnore
	private List<Message> messagesDTO;
	private String messages;
	private long lastUpdated;
	private String user1;
	private String user2;

	public ChatDTO(String user1, String user2) {
		this.user1 = user1;
		this.user2 = user2;
		this.messagesDTO = new ArrayList<>();
		this.lastUpdated = System.currentTimeMillis();
	}

	public void updateURL() {
		this.messages = "/api/chats/" + getId() + "/messages";
	}

	public void addMessage(Message message) {
		message.setId(messagesDTO.size());
		this.messagesDTO.add(message);
		this.lastUpdated = System.currentTimeMillis();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Message> getMessagesDTO() {
		return messagesDTO;
	}

	public void setMessagesDTO(List<Message> messagesDTO) {
		this.messagesDTO = messagesDTO;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public String getUser2() {
		return user2;
	}

	public void setUser2(String user2) {
		this.user2 = user2;
	}

}
