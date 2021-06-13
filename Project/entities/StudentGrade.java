package entities;

import java.io.Serializable;
/**
 * Student Grade class
 * @author shaden
 *
 */
public class StudentGrade implements Serializable {
	private String StudentUserName, ExamCode,ExamCourse,ExamGrade,TeacherName,instr;

	/**
	 * First Construction
	 * @param studentUserName
	 * @param examCode
	 * @param examCourse
	 * @param examGrade
	 */
	public StudentGrade(String studentUserName, String examCode, String examCourse, String examGrade,String teacherName) {
		super();
		StudentUserName = studentUserName;
		ExamCode = examCode;
		ExamCourse = examCourse;
		ExamGrade = examGrade;
		TeacherName=teacherName; 
		
	}
	
	/**
	 * Second Construction 
	 * @param studentUserName
	 * @param examCode
	 * @param examCourse
	 * @param examGrade
	 * @param teacherName
	 * @param instr
	 * in this cunstruction we define the instructions too
	 */
	public StudentGrade(String studentUserName, String examCode, String examCourse, String examGrade, String teacherName,
			String instr) {
		super();
		StudentUserName = studentUserName;
		ExamCode = examCode;
		ExamCourse = examCourse;
		ExamGrade = examGrade;
		TeacherName = teacherName;
		this.instr = instr;
	}
	
	/**
	 * This function 
	 * @return the exam cunstroction
	 */
	public String getInstr() {
		return instr;
	}

	public void setInstr(String instr) {
		this.instr = instr;
	}

	/**
	 * This function 
	 * @return the teacher name
	 */
	public String getTeacherName() {
		return TeacherName;
	}

	public void setTeacherName(String techerName) {
		TeacherName = techerName;
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
