package ch.ralena.glossikaschedule.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class Day implements Parcelable {
	ArrayList<StudyItem> mStudyItems;
	int mDayNumber;
	boolean mIsCompleted;

	public Day(ArrayList<StudyItem> studyItems, int dayNumber) {
		mStudyItems = studyItems;
		mDayNumber = dayNumber;
		mIsCompleted = true;
		for (StudyItem studyItem : studyItems) {
			mIsCompleted = mIsCompleted & studyItem.isCompleted();
		}
	}

	protected Day(Parcel in) {
		mStudyItems = in.createTypedArrayList(StudyItem.CREATOR);
		mDayNumber = in.readInt();
		mIsCompleted = in.readByte() != 0;
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

	public boolean isCompleted() {
		return mIsCompleted;
	}

	public void setCompleted(boolean completed) {
		mIsCompleted = completed;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeTypedList(mStudyItems);
		parcel.writeInt(mDayNumber);
		parcel.writeByte((byte) (mIsCompleted ? 1 : 0));
	}

	public static final Creator<Day> CREATOR = new Creator<Day>() {
		@Override
		public Day createFromParcel(Parcel in) {
			return new Day(in);
		}

		@Override
		public Day[] newArray(int size) {
			return new Day[size];
		}
	};
}
