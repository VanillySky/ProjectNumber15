
package entities;

import java.io.Serializable;

/*
 Algebra = "01";
 Geometry = "02";
 Logic = "03";
 Physics1 = "04";
 Physics2 = "05";
 Geography = "06";
 Chemistry = "07";
}
*/
/**
 * This class presents a exam question
 * @author Saher
 *
 */
public class Question implements Serializable {

	public String QuestionNumber;
	public String QuestionCode;
	public String Question;
	public String Subject;
	public String QuestionInstruction;
	public String Answer1;
	public String Answer2;
	public String Answer3;
	public String Answer4;
	public String RightAnswer;
	public String Author;
	public String point;

	/**
	 * Construction
	 * @param QuestionCode
	 * @param QuestionNumber
	 * @param Subject
	 * @param Question
	 * @param QuestionInstruction
	 * @param Answer1
	 * @param Answer2
	 * @param Answer3
	 * @param Answer4
	 * @param RightAnswer
	 * @param Author
	 * @param point
	 */
	public Question(String QuestionCode, String QuestionNumber, String Subject, String Question,
			String QuestionInstruction, String Answer1, String Answer2, String Answer3, String Answer4,
			String RightAnswer, String Author, String point) {

		this.QuestionNumber = QuestionNumber;
		this.Subject = Subject;
		this.Question = Question;
		this.QuestionInstruction = QuestionInstruction;
		this.Answer1 = Answer1;
		this.Answer2 = Answer2;
		this.Answer3 = Answer3;
		this.Answer4 = Answer4;
		this.RightAnswer = RightAnswer;
		this.QuestionCode = QuestionCode;
		this.Author = Author;
		this.point = point;
	}

	/**
	 * @return the questionNumber
	 */
	public String getQuestionNumber() {
		return QuestionNumber;
	}

	/**
	 * @param questionNumber the questionNumber to set
	 */
	public void setQuestionNumber(String questionNumber) {
		QuestionNumber = questionNumber;
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
	 * @return the question
	 */
	public String getQuestion() {
		return Question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		Question = question;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return Subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		Subject = subject;
	}

	/**
	 * @return the questionInstruction
	 */
	public String getQuestionInstruction() {
		return QuestionInstruction;
	}

	/**
	 * @param questionInstruction the questionInstruction to set
	 */
	public void setQuestionInstruction(String questionInstruction) {
		QuestionInstruction = questionInstruction;
	}

	/**
	 * @return the answer1
	 */
	public String getAnswer1() {
		return Answer1;
	}

	/**
	 * @param answer1 the answer1 to set
	 */
	public void setAnswer1(String answer1) {
		Answer1 = answer1;
	}

	/**
	 * @return the answer2
	 */
	public String getAnswer2() {
		return Answer2;
	}

	/**
	 * @param answer2 the answer2 to set
	 */
	public void setAnswer2(String answer2) {
		Answer2 = answer2;
	}

	/**
	 * @return the answer3
	 */
	public String getAnswer3() {
		return Answer3;
	}

	/**
	 * @param answer3 the answer3 to set
	 */
	public void setAnswer3(String answer3) {
		Answer3 = answer3;
	}

	/**
	 * @return the answer4
	 */
	public String getAnswer4() {
		return Answer4;
	}

	/**
	 * @param answer4 the answer4 to set
	 */
	public void setAnswer4(String answer4) {
		Answer4 = answer4;
	}

	/**
	 * @return the rightAnswer
	 */
	public String getRightAnswer() {
		return RightAnswer;
	}

	/**
	 * @param rightAnswer the rightAnswer to set
	 */
	public void setRightAnswer(String rightAnswer) {
		RightAnswer = rightAnswer;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return Author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		Author = author;
	}

	/**
	 * @return the point
	 */
	public String getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(String point) {
		this.point = point;
	}
	
}