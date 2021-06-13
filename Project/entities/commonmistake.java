package entities;

import java.io.Serializable;

/**
 * Date: 12.06.2021
 * @author shaden
 *
 */
public class commonmistake implements Serializable {
	String ExamCode;
	String QuestionCode;
	String UserName1;
	String UserName2;

	/**
	 * Construction
	 * 
	 * @param examCode
	 * @param questionCode
	 * @param userName1
	 * @param userName2
	 */
	public commonmistake(String examCode, String questionCode, String userName1, String userName2) {
		super();
		ExamCode = examCode;
		QuestionCode = questionCode;
		UserName1 = userName1;
		UserName2 = userName2;
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
	 * @return the questionCode
	 */
	public String getQuestionCode() {
		return QuestionCode;
	}

	/**
	 * @param questionCode the questionCode to set
	 */
	public void setQuestionCode(String questionCode) {
		QuestionCode = questionCode;
	}

	/**
	 * @return the userName1
	 */
	public String getUserName1() {
		return UserName1;
	}

	/**
	 * @param userName1 the userName1 to set
	 */
	public void setUserName1(String userName1) {
		UserName1 = userName1;
	}

	/**
	 * @return the userName2
	 */
	public String getUserName2() {
		return UserName2;
	}

	/**
	 * @param userName2 the userName2 to set
	 */
	public void setUserName2(String userName2) {
		UserName2 = userName2;
	}

}
