package entities;

import java.io.Serializable;

/**
 * Class presents the messages for the manager
 * @author shaden
 *
 */
public class ManagerMessage implements Serializable  {
	String Examcode;
	String TeacherName;
	String addtime;
	String instruction;
	String Approved;
	
	/**
	 * Construction
	 * @param examcode
	 * @param teacherName
	 * @param addtime
	 * @param instruction
	 * @param Approved
	 */
	public ManagerMessage(String examcode, String teacherName, String addtime, String instruction,String Approved) {
		super();
		Examcode = examcode;
		TeacherName = teacherName;
		this.addtime = addtime;
		this.instruction = instruction;
		this.Approved=Approved;
	}

	/**
	 * @return the examcode
	 */
	public String getExamcode() {
		return Examcode;
	}

	/**
	 * @param examcode the examcode to set
	 */
	public void setExamcode(String examcode) {
		Examcode = examcode;
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

	/**
	 * @return the addtime
	 */
	public String getAddtime() {
		return addtime;
	}

	/**
	 * @param addtime the addtime to set
	 */
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	/**
	 * @return the instruction
	 */
	public String getInstruction() {
		return instruction;
	}

	/**
	 * @param instruction the instruction to set
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	/**
	 * @return the approved
	 */
	public String getApproved() {
		return Approved;
	}

	/**
	 * @param approved the approved to set
	 */
	public void setApproved(String approved) {
		Approved = approved;
	}
	
}
