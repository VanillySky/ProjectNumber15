package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * User class presents the data of the user that entiered the system
 *
 */
public class User implements Serializable {
	/**
	 * The user has 7 @param: 
	 * his username, password, firstname, lastName, userId, email and role.
	 */
	private String userName; 
	private String password;	
	private String firstName;
	private String lastName;
	private String userId;
	private String email;
	private String role;
	
	/** Construction
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
	
	/**
	 * This function 
	 * @return the user userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * This function receives 
	 * @param userName
	 * and set it as user userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * This function 
	 * @return the user password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * This function receives 
	 * @param getPassword
	 * and set it as user getPassword
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * This function 
	 * @return the user first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * 	 * This function receives 
	 *
	 * @param firstName
	 * and set it as user firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * This function 
	 * @return the user last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * This function receives 
	 * @param lastName
	 * and set it as user last Name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * This function 
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * This function receives 
	 * @param userId
	 * and set it as user Id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * This function 
	 * @return the user email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * This function receives 
	 * @param email
	 * and set it as user email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * This function 
	 * @return the user role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * This function receives 
	 * @param role
	 * and set it as user role
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
