package entities;

public class InExam {
	String ExamCode;
	String userName;
	String userId;
	public InExam(String examCode, String userName, String userId) {
		super();
		ExamCode = examCode;
		this.userName = userName;
		this.userId = userId;
	}
	
	
	
	public String getExamCode() {
		return ExamCode;
	}
	public void setExamCode(String examCode) {
		ExamCode = examCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	

}
