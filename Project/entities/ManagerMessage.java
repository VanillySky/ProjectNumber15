package entities;

import java.io.Serializable;

public class ManagerMessage implements Serializable  {
	String Examcode;
	String TeacherName;
	String addtime;
	String instruction;
	
	public ManagerMessage(String examcode, String teacherName, String addtime, String instruction) {
		super();
		Examcode = examcode;
		TeacherName = teacherName;
		this.addtime = addtime;
		this.instruction = instruction;
	}

	public String getExamcode() {
		return Examcode;
	}

	public void setExamcode(String examcode) {
		Examcode = examcode;
	}

	public String getTeacherName() {
		return TeacherName;
	}

	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	

}
