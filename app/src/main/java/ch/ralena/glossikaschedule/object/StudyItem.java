package ch.ralena.glossikaschedule.object;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class StudyItem {
	String mTitle;
	boolean mIsCompleted;

	public StudyItem(String title) {
		mTitle = title;
		mIsCompleted = false;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public boolean isCompleted() {
		return mIsCompleted;
	}

	public void setCompleted(boolean completed) {
		mIsCompleted = completed;
	}
}
