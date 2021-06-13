package entities;

import java.io.Serializable;
/**
 * This class presents the status of the exam 
 * @author shaden
 *
 */
public class StatusExam implements Serializable {
	String ExamCode;
	String NumberStartExam;
	String NumberEndExam;
	String time;
	String Date;
	
	/**
	 * Construction
	 * @param examCode
	 * @param numberStartExam
	 * @param numberEndExam
	 * @param time
	 * @param date
	 */
	public StatusExam(String examCode, String numberStartExam, String numberEndExam, String time, String date) {
		super();
		ExamCode = examCode;
		NumberStartExam = numberStartExam;
		NumberEndExam = numberEndExam;
		this.time = time;
		Date = date;
	}
	
	
	/**
	 * 
	 * @return the exam code
	 */
	public String getExamCode() {
		return ExamCode;
	}
	
	/**
	 * 
	 * @param examCode
	 */
	public void setExamCode(String examCode) {
		ExamCode = examCode;
	}
	
	/**
	 * 
	 * @return the number of the student that starts the exam 
	 */
	public String getNumberStartExam() {
		return NumberStartExam;
	}
	
	/**
	 * 
	 * @param numberStartExam
	 */
	public void setNumberStartExam(String numberStartExam) {
		NumberStartExam = numberStartExam;
	}
	
	/**
	 * 
	 * @returnthe number of the student that ends the exam 
	 */
	public String getNumberEndExam() {
		return NumberEndExam;
	}
	
	/**
	 * 
	 * @param numberEndExam
	 */
	public void setNumberEndExam(String numberEndExam) {
		NumberEndExam = numberEndExam;
	}
	
	/**
	 * 
	 * @return exam time
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * 
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
	/**
	 * 
	 * @return the exma's date 
	 */
	public String getDate() {
		return Date;
	}
	
	/**
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		Date = date;
	}
}