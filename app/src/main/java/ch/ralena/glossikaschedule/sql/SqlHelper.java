package ch.ralena.glossikaschedule.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
		if (oldVersion < 2) {
			// todo: switch over to realm data
		}
	}
}
