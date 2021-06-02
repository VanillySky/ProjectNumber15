package entities;

public class GradesStatics {
	private Range gradeRange;
	private int StudentPercent;
	private int numberOfStudent;
	public GradesStatics(Range gradeRange, int studentPercent, int numberOfStudent) {
		this.gradeRange = gradeRange;
		StudentPercent = studentPercent;
		this.numberOfStudent = numberOfStudent;
	}
	public Range getGradeRange() {
		return gradeRange;
	}
	public void setGradeRange(Range gradeRange) {
		this.gradeRange = gradeRange;
	}
	public int getStudentPercent() {
		return StudentPercent;
	}
	public void setStudentPercent(int studentPercent) {
		StudentPercent = studentPercent;
	}
	public int getNumberOfStudent() {
		return numberOfStudent;
	}
	public void setNumberOfStudent(int numberOfStudent) {
		this.numberOfStudent = numberOfStudent;
	}
	
}
