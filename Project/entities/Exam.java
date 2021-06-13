package entities;
import java.io.Serializable;
/**
 * This class presents an exam with all his fields 
 * @author Rostik
 *
 */
public class Exam implements Serializable{
	
	String ExamCode;
	String ExamNumber;
	String ExamSubject;
	String ExamCourse;
	String ExamTime;
	String TeacherName;
	String ChosenQuestion;
	String QuestionPoint ;
	String StudentInstructions;
	String TeacherInstructions;
	
	/**
	 * Construction
	 * @param ExamCode
	 * @param ExamNumber
	 * @param ExamSubject
	 * @param ExamCourse
	 * @param ExamTime
	 * @param TeacherName
	 * @param ChosenQuestion
	 * @param QuestionPoint
	 * @param StudentInstructions
	 * @param TeacherInstructions
	 */
	public Exam(String ExamCode,String ExamNumber,String ExamSubject,String ExamCourse,String  ExamTime,
	String TeacherName,String ChosenQuestion,String QuestionPoint ,String StudentInstructions,String TeacherInstructions) {
		super();
		this.ExamCode=ExamCode;
		this.ExamNumber=ExamNumber;
		this.ExamSubject=ExamSubject;
		this.ExamCourse=ExamCourse;
		this.ExamTime=ExamTime;
		this.TeacherName=TeacherName;
		this.ChosenQuestion=ChosenQuestion;
		this.QuestionPoint=QuestionPoint;
		this.StudentInstructions=StudentInstructions;
		this.TeacherInstructions=TeacherInstructions;
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
	 * @return the examNumber
	 */
	public String getExamNumber() {
		return ExamNumber;
	}

	/**
	 * @param examNumber the examNumber to set
	 */
	public void setExamNumber(String examNumber) {
		ExamNumber = examNumber;
	}

	/**
	 * @return the examSubject
	 */
	public String getExamSubject() {
		return ExamSubject;
	}

	/**
	 * @param examSubject the examSubject to set
	 */
	public void setExamSubject(String examSubject) {
		ExamSubject = examSubject;
	}

	/**
	 * @return the examCourse
	 */
	public String getExamCourse() {
		return ExamCourse;
	}

	/**
	 * @param examCourse the examCourse to set
	 */
	public void setExamCourse(String examCourse) {
		ExamCourse = examCourse;
	}

	/**
	 * @return the examTime
	 */
	public String getExamTime() {
		return ExamTime;
	}

	/**
	 * @param examTime the examTime to set
	 */
	public void setExamTime(String examTime) {
		ExamTime = examTime;
	}

	/**
	 * @return the teacherName
	 */
	public String getTeacherName() {
		return TeacherName;
	}

	/**
	 * @param teacherName the teacherName to set
	 */
	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}

	/**
	 * @return the chosenQuestion
	 */
	public String getChosenQuestion() {
		return ChosenQuestion;
	}

	/**
	 * @param chosenQuestion the chosenQuestion to set
	 */
	public void setChosenQuestion(String chosenQuestion) {
		ChosenQuestion = chosenQuestion;
	}

	/**
	 * @return the questionPoint
	 */
	public String getQuestionPoint() {
		return QuestionPoint;
	}

	/**
	 * @param questionPoint the questionPoint to set
	 */
	public void setQuestionPoint(String questionPoint) {
		QuestionPoint = questionPoint;
	}

	/**
	 * @return the studentInstructions
	 */
	public String getStudentInstructions() {
		return StudentInstructions;
	}

	/**
	 * @param studentInstructions the studentInstructions to set
	 */
	public void setStudentInstructions(String studentInstructions) {
		StudentInstructions = studentInstructions;
	}

	/**
	 * @return the teacherInstructions
	 */
	public String getTeacherInstructions() {
		return TeacherInstructions;
	}

	/**
	 * @param teacherInstructions the teacherInstructions to set
	 */
	public void setTeacherInstructions(String teacherInstructions) {
		TeacherInstructions = teacherInstructions;
	}
}
