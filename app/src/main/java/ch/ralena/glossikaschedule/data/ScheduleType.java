package ch.ralena.glossikaschedule.data;

/**
 * Created by crater-windoze on 12/30/2016.
 */

public class ScheduleType {
	private String mTitle;
	private String[][] mSchedule;
	private int mMinutesDay;
	private int mTotalReps;
	private String mSummary;
	private String mDescription;

	public ScheduleType(String title, String[][] schedule, int minutesDay, int totalReps, String summary, String description) {
		mTitle = title;
		mSchedule = schedule;
		mMinutesDay = minutesDay;
		mTotalReps = totalReps;
		mSummary = summary;
		mDescription = description;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String[][] getSchedule() {
		return mSchedule;
	}

	public void setSchedule(String[][] schedule) {
		mSchedule = schedule;
	}

	public String getSummary() {
		return mSummary;
	}

	public void setSummary(String summary) {
		mSummary = summary;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		mDescription = description;
	}

	public int getMinutesDay() {
		return mMinutesDay;
	}

	public int getmTotalReps() {
		return mTotalReps;
	}
}
