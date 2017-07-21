package ch.ralena.glossikaschedule.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SqlHelper extends SQLiteOpenHelper {
	private static final int DB_VERSION = 2;
	private static final String DB_NAME = "glossikaschedule.db";

	// SCHEDULE fields
	public static final String TABLE_SCHEDULE = "SCHEDULE";
	public static final String COL_SCHEDULE_TITLE = "TITLE";
	public static final String COL_SCHEDULE_LANGUAGE = "LANGUAGE";
	// DAY fields
	public static final String TABLE_DAY = "DAY";
	public static final String COL_DAY_DAY = "DAY";
	public static final String COL_DAY_FOREIGNKEY_SCHEDULE = "SCHEDULE_ID";
	// STUDY_ITEM fields
	public static final String TABLE_STUDY_ITEM = "STUDY_ITEM";
	public static final String COL_STUDY_DESCRIPTION = "DESCRIPTION";
	public static final String COL_STUDY_COMPLETED = "COMPLETED";
	public static final String COL_STUDY_DATE_COMPLETED = "DATE_COMPLETED";
	public static final String COL_STUDY_FOREIGNKEY_DAY = "DAY_ID";

	// sql statements
	private static final String CREATE_SCHEDULE =
			"CREATE TABLE " + TABLE_SCHEDULE +
					"( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COL_SCHEDULE_TITLE + " TEXT, " +
					COL_SCHEDULE_LANGUAGE + " TEXT " +
					" )";
	private static final String CREATE_DAY =
			"CREATE TABLE " + TABLE_DAY +
					"( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COL_DAY_DAY + " INTEGER, " +
					COL_DAY_FOREIGNKEY_SCHEDULE + " INTEGER, " +
					"FOREIGN KEY(" + COL_DAY_FOREIGNKEY_SCHEDULE + ") REFERENCES " + TABLE_SCHEDULE + "(_ID)" +
					" )";
	public static final String CREATE_STUDY_ITEM =
			"CREATE TABLE " + TABLE_STUDY_ITEM +
					"( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COL_STUDY_DESCRIPTION + " TEXT, " +
					COL_STUDY_COMPLETED + " INTEGER, " +
					COL_STUDY_FOREIGNKEY_DAY + " INTEGER, " +
					COL_STUDY_DATE_COMPLETED + " INTEGER, " +
					"FOREIGN KEY(" + COL_STUDY_FOREIGNKEY_DAY + ") REFERENCES " + TABLE_DAY + "(_ID)" +
					" )";

	public SqlHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(CREATE_SCHEDULE);
		sqLiteDatabase.execSQL(CREATE_DAY);
		sqLiteDatabase.execSQL(CREATE_STUDY_ITEM);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		// empty for now
	}
}
