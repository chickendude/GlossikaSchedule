package ch.ralena.glossikaschedule.object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class StudyItem implements Parcelable {
	String mTitle;
	boolean mIsCompleted;

	public StudyItem(String title) {
		mTitle = title;
		mIsCompleted = false;
	}

	protected StudyItem(Parcel in) {
		mTitle = in.readString();
		mIsCompleted = in.readByte() != 0;
	}

	public static final Creator<StudyItem> CREATOR = new Creator<StudyItem>() {
		@Override
		public StudyItem createFromParcel(Parcel in) {
			return new StudyItem(in);
		}

		@Override
		public StudyItem[] newArray(int size) {
			return new StudyItem[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(mTitle);
		parcel.writeByte((byte) (mIsCompleted ? 1 : 0));
	}
}
