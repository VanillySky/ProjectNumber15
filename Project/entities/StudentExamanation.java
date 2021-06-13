package entities;

/**
 * 
 * @author Viktoria
 *
 */
public class StudentExamanation {
	private String StudentUserName, ExamCode, ExamHourse,ExamMinutes;

	/**
	 * @param studentUserName
	 * @param examCode
	 * @param examHourse
	 * @param examMinutes
	 */
	public StudentExamanation(String studentUserName, String examCode, String examHourse, String examMinutes) {
		super();
		StudentUserName = studentUserName;
		ExamCode = examCode;
		ExamHourse = examHourse;
		ExamMinutes = examMinutes;
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
	 * @return the examHourse
	 */
	public String getExamHourse() {
		return ExamHourse;
	}

	/**
	 * @param examHourse the examHourse to set
	 */
	public void setExamHourse(String examHourse) {
		ExamHourse = examHourse;
	}

	/**
	 * @return the examMinutes
	 */
	public String getExamMinutes() {
		return ExamMinutes;
	}

	/**
	 * @param examMinutes the examMinutes to set
	 */
	public void setExamMinutes(String examMinutes) {
		ExamMinutes = examMinutes;
	}
	
}
