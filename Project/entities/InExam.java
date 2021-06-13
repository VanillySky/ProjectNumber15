package entities;

import java.io.Serializable;

/**
 * 
 * @author Viktoria
 *
 */
public class InExam implements Serializable {
	String ExamCode;
	String userName;
	String userId;
	
	/**
	 * Construction
	 * @param examCode
	 * @param userName
	 * @param userId
	 */
	public InExam(String examCode, String userName, String userId) {
		super();
		ExamCode = examCode;
		this.userName = userName;
		this.userId = userId;
	}

	/**
	 * @return the examCode
	 */
	public String getExamCode() {
		return ExamCode;
	}

	/**
	 * @param examCode the examCode to set
	 */
	public void setExamCode(String examCode) {
		ExamCode = examCode;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
