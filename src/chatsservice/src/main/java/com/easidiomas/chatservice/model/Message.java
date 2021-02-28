package com.easidiomas.chatservice.model;

public class Message {

	private int id;
	private long createdAt;
	private String text;
	private String sender;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String text, String sender) {
		super();
		this.createdAt = System.currentTimeMillis();
		this.text = text;
		this.sender = sender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", createdAt=" + createdAt + ", text=" + text + ", sender=" + sender + "]";
	}

}
