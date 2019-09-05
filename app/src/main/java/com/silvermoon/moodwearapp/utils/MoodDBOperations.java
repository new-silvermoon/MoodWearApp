package com.silvermoon.moodwearapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.silvermoon.moodwearapp.model.MoodDBHelper;

public class MoodDBOperations {

    private MoodDBHelper moodDBHelper;
    private SQLiteDatabase mWriteDB;
    private Context context;

    public MoodDBOperations(Context context) {
        this.context = context;
        moodDBHelper = new MoodDBHelper(context);
        mWriteDB = moodDBHelper.getWritableDatabase();
    }

    public long insertValues(String tableName, ContentValues comicContents){
        return mWriteDB.insert(tableName,null,comicContents);
    }
}
