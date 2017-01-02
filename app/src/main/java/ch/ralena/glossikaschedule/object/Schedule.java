package ch.ralena.glossikaschedule.object;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.data.LanguageData;
import ch.ralena.glossikaschedule.data.LanguageType;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class Schedule {
	long mId;
	String mTitle;	// later switch to Schedule type?
	String mLanguage;
	ArrayList<Day> mSchedule;

	public Schedule(String title, String language) {
		mTitle = title;
		mLanguage = language;
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

	public String getLanguage() {
		return mLanguage;
	}

	public LanguageType getLanguageType() {
		LanguageType languageType = null;
		for (LanguageType language : LanguageData.Languages) {
			if (mLanguage.equals(language.getName())) {
				return language;
			}
		}
		return languageType;
	}

	public long getId() {
		return mId;
	}

	public void setId(long id) {
		mId = id;
	}
}
