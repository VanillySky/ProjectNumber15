package entities;

import java.io.Serializable;

public class User implements Serializable {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String userId;
	private String email;
	private String role;
	/**
	 * @param userName
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param userId
	 * @param email
	 * @param role
	 */
	public User(String userName, String password, String firstName, String lastName, String userId, String email,
			String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.email = email;
		this.role = role;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
