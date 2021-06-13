package entities;

public class Statistics {
	String studentUserName;
	String examCode;
	String examCourse;
	String examGrade;
	String TeacherName;
/**
 * Construction 
 * @param studentUserName
 * @param examCode
 * @param examCourse
 * @param examGrade
 * @param TeacherName
 */
	public Statistics(String studentUserName, String examCode, String examCourse, String examGrade, String TeacherName) {
		super();
		studentUserName = studentUserName;
		examCode = examCode;
		examCourse = examCourse;
		examGrade = examGrade;
		TeacherName = TeacherName;
	}

	/**
	 * @return the studentUserName
	 */
	public String getStudentUserName() {
		return studentUserName;
	}

	/**
	 * @param studentUserName the studentUserName to set
	 */
	public void setStudentUserName(String studentUserName) {
		this.studentUserName = studentUserName;
	}

	/**
	 * @return the examCode
	 */
	public String getExamCode() {
		return examCode;
	}

	/**
	 * @param examCode the examCode to set
	 */
	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	/**
	 * @return the examCourse
	 */
	public String getExamCourse() {
		return examCourse;
	}

	/**
	 * @param examCourse the examCourse to set
	 */
	public void setExamCourse(String examCourse) {
		this.examCourse = examCourse;
	}

	/**
	 * @return the examGrade
	 */
	public String getExamGrade() {
		return examGrade;
	}

	/**
	 * @param examGrade the examGrade to set
	 */
	public void setExamGrade(String examGrade) {
		this.examGrade = examGrade;
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
	
	
}
