package com.example.shukdash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DashDataBase extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "DASHDB";
	public static final String TABLE_NAME = "data";
	private static final int VERSION = 1;
	private static final String _ID = "id";
	public static final String EDITTEXTDATA = "Edittextdata";
	public static final String CATEGORY = "Category";
	public static final String TASK = "Task";
	public static final String POINTS_NUMBER = "Points";

	private static final String createDb = " create table if not exists "
			+ TABLE_NAME + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ CATEGORY + " text, " 
			+ TASK + " text, " 
			+ POINTS_NUMBER + " text, "
			+ EDITTEXTDATA + " text );";

	public DashDataBase(Context context) {
		super(context, DATABASE_NAME, null, VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createDb);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
