package user;

public class UserDTO {
	private String userID;
	private String userPassword;
	private String userEmail;
	private String userEmailHash;
	private boolean userEamilChecked;
	
	public UserDTO() {
		
	}
	
	public UserDTO(String userID, String userPassword, String userEmail, String userEmailHash,
			boolean userEamilChecked) {
		this.userID = userID;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userEmailHash = userEmailHash;
		this.userEamilChecked = userEamilChecked;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserEmailHash() {
		return userEmailHash;
	}
	public void setUserEmailHash(String userEmailHash) {
		this.userEmailHash = userEmailHash;
	}

	public boolean isUserEamilChecked() {
		return userEamilChecked;
	}

	public void setUserEamilChecked(boolean userEamilChecked) {
		this.userEamilChecked = userEamilChecked;
	}
	
	
}
