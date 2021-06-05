package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Teacher extends User implements Serializable {

	public Teacher(String userName, String password, String firstName, String lastName, String userId, String email) {
		super(userName, password, firstName, lastName, userId, email, "Teacher");
	}
}
