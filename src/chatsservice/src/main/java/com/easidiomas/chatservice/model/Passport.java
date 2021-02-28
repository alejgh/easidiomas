package com.easidiomas.chatservice.model;

public class Passport {

	private String userId_;
	private String username_;
	private String role_;
	private String name_;
	private String surname_;
	private String email_;
	private String avatarUrl_;
	private String expirationDate_;

	public Passport() {
	}

	public Passport(String userId_, String username_, String role_, String name_, String surname_, String email_,
			String avatarUrl_, String expirationDate_) {
		super();
		this.userId_ = userId_;
		this.username_ = username_;
		this.role_ = role_;
		this.name_ = name_;
		this.surname_ = surname_;
		this.email_ = email_;
		this.avatarUrl_ = avatarUrl_;
		this.expirationDate_ = expirationDate_;
	}

	public String getUserId_() {
		return userId_;
	}

	public void setUserId_(String userId_) {
		this.userId_ = userId_;
	}

	public String getUsername_() {
		return username_;
	}

	public void setUsername_(String username_) {
		this.username_ = username_;
	}

	public String getRole_() {
		return role_;
	}

	public void setRole_(String role_) {
		this.role_ = role_;
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public String getSurname_() {
		return surname_;
	}

	public void setSurname_(String surname_) {
		this.surname_ = surname_;
	}

	public String getEmail_() {
		return email_;
	}

	public void setEmail_(String email_) {
		this.email_ = email_;
	}

	public String getAvatarUrl_() {
		return avatarUrl_;
	}

	public void setAvatarUrl_(String avatarUrl_) {
		this.avatarUrl_ = avatarUrl_;
	}

	public String getExpirationDate_() {
		return expirationDate_;
	}

	public void setExpirationDate_(String expirationDate_) {
		this.expirationDate_ = expirationDate_;
	}

}
