package ch.ralena.glossikaschedule.object;

import java.util.ArrayList;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class Schedule {
	String mTitle;	// later switch to Schedule type?
	ArrayList<Day> mSchedule;

	public Schedule(String title) {
		mTitle = title;
		mSchedule = new ArrayList<>();
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public ArrayList<Day> getSchedule() {
		return mSchedule;
	}

	public void setSchedule(ArrayList<Day> schedule) {
		mSchedule = schedule;
	}

	public void addDay(Day day) {
		mSchedule.add(day);
	}
}
