package entities;

import java.io.Serializable;

/**
 * This class presents the student as a user  
 * @author shaden
 *
 */
@SuppressWarnings("serial")
public class Student extends User implements Serializable {
	/**
	 * Construction
	 * @param userName
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param userId
	 * @param email
	 * role = "student"
	 */
	public Student(String userName, String password, String firstName, String lastName, String userId, String email) {
		super(userName, password, firstName, lastName, userId, email, "Student");
	}
}
