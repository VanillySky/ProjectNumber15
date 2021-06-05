package entities;

public class GradesStatics {
	private String CodeExam;
	private int numberOfStudent;
	private int Avergae;
	private int Median;
	private int MaxGrade;
	private int MinGrade;
	private Range gradeRange;
	
	public GradesStatics(String codeExam, int numberOfStudent, int avergae, int median, int maxGrade, int minGrade,
			Range gradeRange) {
		super();
		CodeExam = codeExam;
		this.numberOfStudent = numberOfStudent;
		Avergae = avergae;
		Median = median;
		MaxGrade = maxGrade;
		MinGrade = minGrade;
		this.gradeRange = gradeRange;
	}

	public String getCodeExam() {
		return CodeExam;
	}

	public void setCodeExam(String codeExam) {
		CodeExam = codeExam;
	}

	public int getNumberOfStudent() {
		return numberOfStudent;
	}

	public void setNumberOfStudent(int numberOfStudent) {
		this.numberOfStudent = numberOfStudent;
	}

	public int getAvergae() {
		return Avergae;
	}

	public void setAvergae(int avergae) {
		Avergae = avergae;
	}

	public int getMedian() {
		return Median;
	}

	public void setMedian(int median) {
		Median = median;
	}

	public int getMaxGrade() {
		return MaxGrade;
	}

	public void setMaxGrade(int maxGrade) {
		MaxGrade = maxGrade;
	}

	public int getMinGrade() {
		return MinGrade;
	}

	public void setMinGrade(int minGrade) {
		MinGrade = minGrade;
	}

	public Range getGradeRange() {
		return gradeRange;
	}

	public void setGradeRange(Range gradeRange) {
		this.gradeRange = gradeRange;
	}
	
	
}
