package entities;

import java.io.Serializable;

public class ExamTime implements Serializable {
	private String hours, minutes;

	/**
	 * @param hours
	 * @param minutes
	 */
	public ExamTime(String hours, String minutes) {
		super();
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
