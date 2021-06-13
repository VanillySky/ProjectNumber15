package entities;

import java.io.Serializable;

public class ExamResponse implements Serializable {
	String ExamCode;
	String UserName;
	String QuestionCode;
	String StudentAnswer;
	String Point;
	public ExamResponse(String examCode, String userName, String questionCode, String string ) {
		super();
		ExamCode = examCode;
		UserName = userName;
		QuestionCode = questionCode;
		StudentAnswer = string;
	}
	public String getExamCode() {
		return ExamCode;
	}
	public void setExamCode(String examCode) {
		ExamCode = examCode;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getQuestionCode() {
		return QuestionCode;
	}
	public void setQuestionCode(String questionCode) {
		QuestionCode = questionCode;
	}
	public String getStudentAnswer() {
		return StudentAnswer;
	}
	public void setStudentAnswer(String studentAnswer) {
		StudentAnswer = studentAnswer;
	}
	
	
	
	
}
