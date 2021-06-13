package entities;

import java.io.Serializable;

/**
 * This class presents the manager as a user  
 * @author shaden
 *
 */
@SuppressWarnings("serial")
public class Manager extends User implements Serializable {

	/**
	 * Construction
	 * @param userName
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param userId
	 * @param email
	 * role = "Manager"
	 */
	public Manager(String userName, String password, String firstName, String lastName, String userId, String email) {
		super(userName, password, firstName, lastName, userId, email, "Manager");
	}
	
}
