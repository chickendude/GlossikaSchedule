package ch.ralena.glossikaschedule.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.object.Day;
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.object.StudyItem;

/**
 * Created by crater-windoze on 12/28/2016.
 */

public class SqlManager {
	SqlHelper mSqlHelper;

	public SqlManager(Context context) {
		mSqlHelper = new SqlHelper(context);
	}

	public ArrayList<Schedule> getSchedule() {
		SQLiteDatabase database = mSqlHelper.getWritableDatabase();

		ArrayList<Schedule> schedules = new ArrayList<>();

		String table = SqlHelper.TABLE_SCHEDULE;
		String col_language = SqlHelper.COL_SCHEDULE_LANGUAGE;
		String col_title = SqlHelper.COL_SCHEDULE_TITLE;

		Cursor cursor = database.query(
				table,
				new String[]{col_language, col_title, BaseColumns._ID},
				null,
				null,
				null,
				null,
				null);
		if (cursor.moveToFirst()) {
			do {
				long id = getLong(cursor, BaseColumns._ID);
				String title = getString(cursor, SqlHelper.COL_SCHEDULE_TITLE);
				String language = getString(cursor, SqlHelper.COL_SCHEDULE_LANGUAGE);
				Schedule schedule = new Schedule(title, language);
				schedule.setSchedule(getDays(database, id));
				schedule.setId(id);
				schedules.add(schedule);
			} while (cursor.moveToNext());
		}

		cursor.close();
		database.close();
		return schedules;
	}

	private ArrayList<Day> getDays(SQLiteDatabase database, long scheduleId) {
		ArrayList<Day> days = new ArrayList<>();
		Cursor dayCursor = database.rawQuery(
				"SELECT * FROM " + SqlHelper.TABLE_DAY +
						" WHERE " + SqlHelper.COL_DAY_FOREIGNKEY_SCHEDULE + " = " + scheduleId,
				null);
		if (dayCursor.moveToFirst()) {
			do {
				long id = getLong(dayCursor, BaseColumns._ID);
				int dayNumber = getInt(dayCursor, SqlHelper.COL_DAY_DAY);
				Day day = new Day(getStudyItems(database, id), dayNumber);
				days.add(day);
			} while (dayCursor.moveToNext());
		}
		dayCursor.close();
		return days;
	}

	private ArrayList<StudyItem> getStudyItems(SQLiteDatabase database, long dayId) {
		Cursor studyCursor = database.rawQuery("SELECT * FROM " + SqlHelper.TABLE_STUDY_ITEM +
				" WHERE " + SqlHelper.COL_STUDY_FOREIGNKEY_DAY + " = " + dayId, null);
		ArrayList<StudyItem> studyItems = new ArrayList<>();
		if (studyCursor.moveToFirst()) {
			do {
				long id = getLong(studyCursor, BaseColumns._ID);
				String description = getString(studyCursor, SqlHelper.COL_STUDY_DESCRIPTION);
				boolean completed = getInt(studyCursor, SqlHelper.COL_STUDY_COMPLETED) > 0;
				studyItems.add(new StudyItem(description, completed, id));
			} while (studyCursor.moveToNext());
		}

		studyCursor.close();
		return studyItems;
	}

	private long getLong(Cursor cursor, String columnName) {
		int index = cursor.getColumnIndex(columnName);
		return cursor.getLong(index);
	}

	private int getInt(Cursor cursor, String columnName) {
		int index = cursor.getColumnIndex(columnName);
		return cursor.getInt(index);
	}

	private String getString(Cursor cursor, String columnName) {
		int index = cursor.getColumnIndex(columnName);
		return cursor.getString(index);
	}

	public long createSchedule(Schedule schedule) {
		SQLiteDatabase database = mSqlHelper.getWritableDatabase();
		database.beginTransaction();

		ContentValues scheduleValues = new ContentValues();
		scheduleValues.put(SqlHelper.COL_SCHEDULE_TITLE, schedule.getTitle());
		scheduleValues.put(SqlHelper.COL_SCHEDULE_LANGUAGE, schedule.getLanguage());
		long scheduleId = database.insert(SqlHelper.TABLE_SCHEDULE, null, scheduleValues);

		for (Day day : schedule.getSchedule()) {
			ContentValues dayValues = new ContentValues();
			dayValues.put(SqlHelper.COL_DAY_DAY, day.getDayNumber());
			dayValues.put(SqlHelper.COL_DAY_FOREIGNKEY_SCHEDULE, scheduleId);
			long dayId = database.insert(SqlHelper.TABLE_DAY, null, dayValues);
			for (StudyItem studyItem : day.getStudyItems()) {
				ContentValues studyValues = new ContentValues();
				studyValues.put(SqlHelper.COL_STUDY_DESCRIPTION, studyItem.getTitle());
				studyValues.put(SqlHelper.COL_STUDY_COMPLETED, studyItem.isCompleted());
				studyValues.put(SqlHelper.COL_STUDY_FOREIGNKEY_DAY, dayId);
				long studyId = database.insert(SqlHelper.TABLE_STUDY_ITEM, null, studyValues);
				studyItem.setId(studyId);
			}
		}

		database.setTransactionSuccessful();
		database.endTransaction();
		database.close();

		return scheduleId;
	}

	public void updateDay(Day day) {
		SQLiteDatabase database = mSqlHelper.getWritableDatabase();
		database.beginTransaction();

		for (StudyItem studyItem : day.getStudyItems()) {
			ContentValues studyValues = new ContentValues();
			studyValues.put(SqlHelper.COL_STUDY_COMPLETED, studyItem.isCompleted());

			String whereClause = BaseColumns._ID + "=?";
			String[] whereArgs = new String[]{String.valueOf(studyItem.getId())};
			database.update(SqlHelper.TABLE_STUDY_ITEM, studyValues, whereClause, whereArgs);
		}
		// close
		database.setTransactionSuccessful();
		database.endTransaction();
		database.close();
	}
}
