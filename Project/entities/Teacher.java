package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * This class presents the teacher as a user  
 * @author shaden
 *
 */
public class Teacher extends User implements Serializable {

	/**
	 * Construction
	 * @param userName
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param userId
	 * @param email
	 * The role = "Teacher"
	 */
	public Teacher(String userName, String password, String firstName, String lastName, String userId, String email) {
		super(userName, password, firstName, lastName, userId, email, "Teacher");
	}
}
