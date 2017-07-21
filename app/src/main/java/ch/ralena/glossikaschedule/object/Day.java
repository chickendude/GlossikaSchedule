package ch.ralena.glossikaschedule.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Day implements Parcelable {
	ArrayList<StudyItem> studyItems;
	int dayNumber;
	boolean isCompleted;
	Calendar dateCompleted;

	public Day(ArrayList<StudyItem> studyItems, int dayNumber) {
		this.studyItems = studyItems;
		this.dayNumber = dayNumber;
		isCompleted = true;
		for (StudyItem studyItem : studyItems) {
			isCompleted = isCompleted & studyItem.isCompleted();
		}
	}

	public ArrayList<StudyItem> getStudyItems() {
		return studyItems;
	}

	public void setStudyItems(ArrayList<StudyItem> studyItems) {
		this.studyItems = studyItems;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean completed) {
		isCompleted = completed;
	}

	public Calendar getDateCompleted() {
		return dateCompleted;
	}

	public String getFormattedDateCompleted() {
		if (dateCompleted != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM. yyyy");
			return dateFormat.format(dateCompleted.getTimeInMillis());
		} else {
			return "";
		}
	}

	public void updateDateCompleted() {
		if (dateCompleted == null) {
			// check if all study items have been completed
			boolean allCompleted = true;
			for (StudyItem item : studyItems) {
				allCompleted = allCompleted && item.isCompleted();
			}
			// if so, save today's date as the new completed date
			if (allCompleted) {
				dateCompleted = Calendar.getInstance();
			}
		}
	}

	public boolean wasCompletedToday() {
		if (dateCompleted != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM. yyyy");
			String today = dateFormat.format(Calendar.getInstance().getTimeInMillis());
			return dateFormat.format(dateCompleted.getTime()).equals(today);
		} else {
			return false;
		}
	}

	// parcelable implementation
	protected Day(Parcel in) {
		studyItems = in.createTypedArrayList(StudyItem.CREATOR);
		dayNumber = in.readInt();
		isCompleted = in.readByte() != 0;
		dateCompleted = (Calendar) in.readSerializable();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeTypedList(studyItems);
		parcel.writeInt(dayNumber);
		parcel.writeByte((byte) (isCompleted ? 1 : 0));
		parcel.writeSerializable(dateCompleted);
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
