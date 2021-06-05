package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Manager extends User implements Serializable {

	public Manager(String userName, String password, String firstName, String lastName, String userId, String email) {
		super(userName, password, firstName, lastName, userId, email, "Manager");
	}
	
}
