package entities;

import java.io.Serializable;
/**
 * This class presents the time of the exam by hours and minutes and seconds
 * @author shaden
 *
 */
public class ExamTime implements Serializable {
	private String hours, minutes;

	/**
	 * Construction
	 * @param hours
	 * @param minutes
	 */
	public ExamTime(String hours, String minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}

	/**
	 * @return the hours
	 */
	public String getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(String hours) {
		this.hours = hours;
	}

	/**
	 * @return the minutes
	 */
	public String getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
}
