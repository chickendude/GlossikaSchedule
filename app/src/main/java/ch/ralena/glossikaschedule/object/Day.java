package ch.ralena.glossikaschedule.object;

import java.util.ArrayList;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class Day {
	ArrayList<StudyItem> mStudyItems;
	int mDayNumber;

	public Day(ArrayList<StudyItem> studyItems, int dayNumber) {
		mStudyItems = studyItems;
		mDayNumber = dayNumber;
	}

	public ArrayList<StudyItem> getStudyItems() {
		return mStudyItems;
	}

	public void setStudyItems(ArrayList<StudyItem> studyItems) {
		mStudyItems = studyItems;
	}

	public int getDayNumber() {
		return mDayNumber;
	}

	public void setDayNumber(int dayNumber) {
		mDayNumber = dayNumber;
	}
}
