package ch.ralena.glossikaschedule.data;

/**
 * Created by crater-windoze on 12/30/2016.
 */

public class ScheduleType {
	private String title;
	private String[][] schedule;
	private int minutesDay;
	private int totalReps;
	private String courseLength;
	private String courseLengthSmall;
	private String summary;
	private String description;

	public ScheduleType(String title, String[][] schedule, int minutesDay, int totalReps, String courseLength, String courseLengthSmall, String summary, String description) {
		this.title = title;
		this.schedule = schedule;
		this.minutesDay = minutesDay;
		this.totalReps = totalReps;
		this.courseLength = courseLength;
		this.courseLengthSmall = courseLengthSmall;
		this.summary = summary;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[][] getSchedule() {
		return schedule;
	}

	public void setSchedule(String[][] schedule) {
		this.schedule = schedule;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinutesDay() {
		return minutesDay;
	}

	public int getTotalReps() {
		return totalReps;
	}

	public String getCourseLength() {
		return courseLength;
	}

	public String getCourseLengthSmall() {
		return courseLengthSmall;
	}
}
