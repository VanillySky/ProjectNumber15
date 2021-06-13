package entities;

import java.io.Serializable;
/**
 * Date: 13.06.2021
 * @author Rostik
 */
public class ExamResponse implements Serializable {
	String ExamCode;
	String UserName;
	String QuestionCode;
	String StudentAnswer;
	String Point;
	
	/**
	 * 
	 * @param examCode
	 * @param userName
	 * @param questionCode
	 * @param string
	 */
	public ExamResponse(String examCode, String userName, String questionCode, String string ) {
		super();
		ExamCode = examCode;
		UserName = userName;
		QuestionCode = questionCode;
		StudentAnswer = string;
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
		return UserName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
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
	 * @return the studentAnswer
	 */
	public String getStudentAnswer() {
		return StudentAnswer;
	}

	/**
	 * @param studentAnswer the studentAnswer to set
	 */
	public void setStudentAnswer(String studentAnswer) {
		StudentAnswer = studentAnswer;
	}

	/**
	 * @return the point
	 */
	public String getPoint() {
		return Point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(String point) {
		Point = point;
	}
}
