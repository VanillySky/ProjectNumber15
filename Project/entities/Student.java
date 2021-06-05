package entities;

import java.io.Serializable;



@SuppressWarnings("serial")
public class Student extends User implements Serializable {

	public Student(String userName, String password, String firstName, String lastName, String userId, String email) {
		super(userName, password, firstName, lastName, userId, email, "Student");
	}
}
