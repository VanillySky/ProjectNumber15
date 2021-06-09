package entities;

public class Statistics {
	String studentUserName;
	String examCode;
	String examCourse;
	String examGrade;
	String TeacherName;

	public Statistics(String studentUserName, String examCode, String examCourse, String examGrade, String TeacherName) {
		super();
		studentUserName = studentUserName;
		examCode = examCode;
		examCourse = examCourse;
		examGrade = examGrade;
		TeacherName = TeacherName;
	}

	public String getstudentUserName() {
		return studentUserName;
	}

	public void setstudentUserName(String studentUserName) {
		studentUserName = studentUserName;
	}

	public String getexamCode() {
		return examCode;
	}

	public void setexamCode(String examCode) {
		examCode = examCode;
	}

	public String getexamCourse() {
		return examCourse;
	}

	public void setexamCourse(String examCourse) {
		examCourse = examCourse;
	}

	public String getexamGrade() {
		return examGrade;
	}

	public void setexamGrade(String examGrade) {
		examGrade = examGrade;
	}

	public String getTeacherName() {
		return TeacherName;
	}

	public void setTeacherName(String TeacherName) {
		TeacherName = TeacherName;
	}

}
