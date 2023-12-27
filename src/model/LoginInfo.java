package model;

public class LoginInfo {
	private String emailString;
	private String passwordString;
	
	public LoginInfo(String emailString, String passwordString) {
		super();
		this.emailString = emailString;
		this.passwordString = passwordString;
	}
	
	public String getEmailString() {
		return emailString;
	}
	
	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}
	
	public String getPasswordString() {
		return passwordString;
	}
	
	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}
}
