package entities;

import java.io.Serializable;

public class commonmistake implements Serializable {
	String ExamCode;
	String QuestionCode;
	String UserName1;
	String UserName2;
	public commonmistake(String examCode, String questionCode, String userName1, String userName2) {
		super();
		ExamCode = examCode;
		QuestionCode = questionCode;
		UserName1 = userName1;
		UserName2 = userName2;
	}
	public String getExamCode() {
		return ExamCode;
	}
	public void setExamCode(String examCode) {
		ExamCode = examCode;
	}
	public String getQuestionCode() {
		return QuestionCode;
	}
	public void setQuestionCode(String questionCode) {
		QuestionCode = questionCode;
	}
	public String getUserName1() {
		return UserName1;
	}
	public void setUserName1(String userName1) {
		UserName1 = userName1;
	}
	public String getUserName2() {
		return UserName2;
	}
	public void setUserName2(String userName2) {
		UserName2 = userName2;
	}

	
	
}
