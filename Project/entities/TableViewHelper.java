package entities;



public class TableViewHelper {
	
	String ExamCode;
	String ExamNumber;
	String ExamSubject;
	String ExamCourse;
	Float ExamTime;
	String TeacherName;
	String StudentInstructions;
	String TeacherInstructions;
	
	public TableViewHelper(String ExamCode,String ExamNumber,String ExamSubject,String ExamCourse,Float  ExamTime,
	String TeacherName,String StudentInstructions,String TeacherInstructions) {
		super();
		this.ExamCode=ExamCode;
		this.ExamNumber=ExamNumber;
		this.ExamSubject=ExamSubject;
		this.ExamCourse=ExamCourse;
		this.ExamTime=ExamTime;
		this.TeacherName=TeacherName;
		this.StudentInstructions=StudentInstructions;
		this.TeacherInstructions=TeacherInstructions;}

	public String getExamCode() {
		return ExamCode;
	}

	public void setExamCode(String examCode) {
		ExamCode = examCode;
	}

	public String getExamNumber() {
		return ExamNumber;
	}

	public void setExamNumber(String examNumber) {
		ExamNumber = examNumber;
	}

	public String getExamSubject() {
		return ExamSubject;
	}

	public void setExamSubject(String examSubject) {
		ExamSubject = examSubject;
	}

	public String getExamCourse() {
		return ExamCourse;
	}

	public void setExamCourse(String examCourse) {
		ExamCourse = examCourse;
	}

	public Float getExamTime() {
		return ExamTime;
	}

	public void setExamTime(Float examTime) {
		ExamTime = examTime;
	}

	public String getTeacherName() {
		return TeacherName;
	}

	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}

	public String getStudentInstructions() {
		return StudentInstructions;
	}

	public void setStudentInstructions(String studentInstructions) {
		StudentInstructions = studentInstructions;
	}

	public String getTeacherInstructions() {
		return TeacherInstructions;
	}

	public void setTeacherInstructions(String teacherInstructions) {
		TeacherInstructions = teacherInstructions;
	}
	
	

}
