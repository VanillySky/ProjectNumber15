package entities;

import java.io.Serializable;

public class StatusExam implements Serializable {
	String ExamCode;
	String NumberStartExam;
	String NumberEndExam;
	String time;
	String Date;
	
	
	public StatusExam(String examCode, String numberStartExam, String numberEndExam, String time, String date) {
		super();
		ExamCode = examCode;
		NumberStartExam = numberStartExam;
		NumberEndExam = numberEndExam;
		this.time = time;
		Date = date;
	}
	
	
	
	public String getExamCode() {
		return ExamCode;
	}
	public void setExamCode(String examCode) {
		ExamCode = examCode;
	}
	public String getNumberStartExam() {
		return NumberStartExam;
	}
	public void setNumberStartExam(String numberStartExam) {
		NumberStartExam = numberStartExam;
	}
	public String getNumberEndExam() {
		return NumberEndExam;
	}
	public void setNumberEndExam(String numberEndExam) {
		NumberEndExam = numberEndExam;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	

}
