
package entities;
/*
ConstCourse {
 Algebra = "01";
 Geometry = "02";
 Logic = "03";
 Physics1 = "04";
 Physics2 = "05";
 Geography = "06";
 Chemistry = "07";
}
*/
public class Question {

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

	

	public Question(String QuestionNumber, String Subject, String Question, String QuestionInstruction, String Answer1,
			String Answer2, String Answer3, String Answer4, String RightAnswer, String Author,String point) {

		this.QuestionNumber = QuestionNumber;
		this.Subject = Subject;
		this.Question = Question;
		this.QuestionInstruction = QuestionInstruction;
		this.Answer1 = Answer1;
		this.Answer2 = Answer2;
		this.Answer3 = Answer3;
		this.Answer4 = Answer4;
		this.RightAnswer = RightAnswer;
		this.QuestionCode = Subject + QuestionNumber;
		this.Author = Author;
		this.point=point;

	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getQuestionNumber() {
		return QuestionNumber;
	}

	public void setQuestionNumber(String questionNumber) {
		QuestionNumber = questionNumber;
	}

	public String getQuestionCode() {
		return QuestionCode;
	}

	public void setQuestionCode(String questionCode) {
		QuestionCode = questionCode;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getQuestionInstruction() {
		return QuestionInstruction;
	}

	public void setQuestionInstruction(String questionInstruction) {
		QuestionInstruction = questionInstruction;
	}

	public String getAnswer1() {
		return Answer1;
	}

	public void setAnswer1(String answer1) {
		Answer1 = answer1;
	}

	public String getAnswer2() {
		return Answer2;
	}

	public void setAnswer2(String answer2) {
		Answer2 = answer2;
	}

	public String getAnswer3() {
		return Answer3;
	}

	public void setAnswer3(String answer3) {
		Answer3 = answer3;
	}

	public String getAnswer4() {
		return Answer4;
	}

	public void setAnswer4(String answer4) {
		Answer4 = answer4;
	}

	public String getRightAnswer() {
		return RightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		RightAnswer = rightAnswer;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}
	
	

}
