package ch.ralena.glossikaschedule.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.HashMap;
import java.util.UUID;

import ch.ralena.glossikaschedule.object.Day;
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.object.StudyItem;
import io.realm.Realm;

public class SqlHelper extends SQLiteOpenHelper {
	private static final int DB_VERSION = 2;
	private static final String DB_NAME = "glossikaschedule.db";

	public SqlHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
		// convert sql database into realm database
		if (oldVersion < 2) {
			Realm realm = Realm.getDefaultInstance();
			realm.executeTransaction(r -> {
				HashMap<Long, Schedule> scheduleMap = new HashMap<>();
				HashMap<Long, Day> dayMap = new HashMap<>();

				// get all schedules
				String scheduleQuery = "SELECT * FROM SCHEDULE";
				Cursor scheduleCursor = sqLiteDatabase.rawQuery(scheduleQuery, null);
				while (scheduleCursor.moveToNext()) {
					Schedule schedule = r.createObject(Schedule.class, UUID.randomUUID().toString());
					long id = scheduleCursor.getLong(scheduleCursor.getColumnIndex(BaseColumns._ID));
					String title = scheduleCursor.getString(scheduleCursor.getColumnIndex("TITLE"));
					String language = scheduleCursor.getString(scheduleCursor.getColumnIndex("LANGUAGE"));
					schedule.setTitle(title);
					schedule.setLanguage(language);
					scheduleMap.put(id, schedule);
				}
				scheduleCursor.close();

				// get all days
				String dayQuery = "SELECT * FROM DAY";
				Cursor dayCursor = sqLiteDatabase.rawQuery(dayQuery, null);
				while (dayCursor.moveToNext()) {
					Day day = realm.createObject(Day.class, UUID.randomUUID().toString());
					long id = dayCursor.getLong(dayCursor.getColumnIndex(BaseColumns._ID));
					long scheduleId = dayCursor.getLong(dayCursor.getColumnIndex("SCHEDULE_ID"));
					int dayNumber = dayCursor.getInt(dayCursor.getColumnIndex("DAY"));
					day.setDayNumber(dayNumber);

					// add day to mapping of days
					dayMap.put(id, day);

					// add day to its corresponding schedule
					Schedule schedule = scheduleMap.get(scheduleId);
					schedule.getSchedule().add(day);
				}
				dayCursor.close();

				// get all study items
				String studyItemQuery = "SELECT * FROM STUDY_ITEM";
				Cursor studyItemCursor = sqLiteDatabase.rawQuery(studyItemQuery, null);
				while (studyItemCursor.moveToNext()) {
					// create realm object
					StudyItem studyItem = realm.createObject(StudyItem.class, UUID.randomUUID().toString());

					// pull data from database
					long id = studyItemCursor.getLong(studyItemCursor.getColumnIndex(BaseColumns._ID));
					long dayId = studyItemCursor.getLong(studyItemCursor.getColumnIndex("DAY_ID"));
					String description = studyItemCursor.getString(studyItemCursor.getColumnIndex("DESCRIPTION"));
					boolean isCompleted = studyItemCursor.getInt(studyItemCursor.getColumnIndex("COMPLETED")) > 0;

					// update study item with db data
					studyItem.setTitle(description);
					studyItem.setCompleted(isCompleted);

					// add study item to the corresponding day
					Day day = dayMap.get(dayId);
					day.getStudyItems().add(studyItem);
				}
				studyItemCursor.close();

				// go through study items for each day and check if they've been completed so we can set iscompleted/datecompleted
				for (Day day : dayMap.values()) {
					boolean isCompleted = true;
					for (StudyItem studyItem : day.getStudyItems()) {
						isCompleted &= studyItem.isCompleted();
					}
					day.setCompleted(isCompleted);
					if (isCompleted) {
						// set to one to show that it was from a previous version
						day.setDateCompleted(1);
					}
				}

				// drop all tables
				sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SCHEDULE");
				sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DAY");
				sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDY_ITEM");
			}); // realm transaction
		} // if oldversion < 2
	} // onUpgrade
}
