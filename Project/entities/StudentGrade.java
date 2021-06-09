package entities;

import java.io.Serializable;

public class StudentGrade implements Serializable {
	private String StudentUserName, ExamCode,ExamCourse,ExamGrade,TecherName;

	/**
	 * @param studentUserName
	 * @param examCode
	 * @param examCourse
	 * @param examGrade
	 */
	public StudentGrade(String studentUserName, String examCode, String examCourse, String examGrade,String TeacherName) {
		super();
		StudentUserName = studentUserName;
		ExamCode = examCode;
		ExamCourse = examCourse;
		ExamGrade = examGrade;
		this.TecherName=TeacherName;
	}

	public String getTecherName() {
		return TecherName;
	}

	public void setTecherName(String techerName) {
		TecherName = techerName;
	}

	/**
	 * @return the studentUserName
	 */
	public String getStudentUserName() {
		return StudentUserName;
	}

	/**
	 * @param studentUserName the studentUserName to set
	 */
	public void setStudentUserName(String studentUserName) {
		StudentUserName = studentUserName;
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
	 * @return the examGrade
	 */
	public String getExamGrade() {
		return ExamGrade;
	}

	/**
	 * @param examGrade the examGrade to set
	 */
	public void setExamGrade(String examGrade) {
		ExamGrade = examGrade;
	}
	
}
