package com.silvermoon.moodwearapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoodDBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;

    public MoodDBHelper(Context context) {
        super(context, MoodContract.DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String createMoodQuery = "CREATE TABLE " + MoodContract.Mood.TABLE_NAME + " ("
                + MoodContract.Mood._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MoodContract.Mood.MOOD_NAME + " TEXT, "
                + MoodContract.Mood.INSRTD_TSMP + " INTEGER DEFAULT 0 "
                + ")";

        final String createDistractQuery = "CREATE TABLE " + MoodContract.Distraction.TABLE_NAME + " ("
                + MoodContract.Distraction._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MoodContract.Distraction.INSRTD_TSMP + " INTEGER DEFAULT 0 "
                + ")";

        db.execSQL(createMoodQuery);
        db.execSQL(createDistractQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
