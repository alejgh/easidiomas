package easidiomas.chatservice.model;

public class Passport {

	private String userId;
	private String userURL;
	private String username;
	private String name;
	private String surname;
	private String email;
	private String userProfilePicUrl;
	private String expirationDate;

	public Passport() {
	}

	public Passport(String userId, String username, String name, String surname, String email, String userProfilePicUrl,
			String expirationDate, String userURL) {
		super();
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.userProfilePicUrl = userProfilePicUrl;
		this.expirationDate = expirationDate;
		this.userURL = userURL;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserProfilePicUrl() {
		return userProfilePicUrl;
	}

	public void setUserProfilePicUrl(String userProfilePicUrl) {
		this.userProfilePicUrl = userProfilePicUrl;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getUserURL() {
		return userURL;
	}

	public void setUserURL(String userURL) {
		this.userURL = userURL;
	}

	@Override
	public String toString() {
		return "Passport [userId=" + userId + ", username=" + username + ", name=" + name + ", surname=" + surname
				+ ", email=" + email + ", userProfilePicUrl=" + userProfilePicUrl + ", expirationDate=" + expirationDate
				+ ", userURL=" + userURL + "]";
	}

}
