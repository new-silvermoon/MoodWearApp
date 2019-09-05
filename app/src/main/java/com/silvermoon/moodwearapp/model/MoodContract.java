package com.silvermoon.moodwearapp.model;

import android.net.Uri;
import android.provider.BaseColumns;

import com.silvermoon.moodwearapp.BaseApplication;

public class MoodContract {

    public static final String DB_NAME = (BaseApplication.getappContext().getPackageName()).toUpperCase()+".DB";
    public static final String MAIN_COVER_URI = "mainpagecover";
    public final static String PAGE_URI = "comicpage";

    public static abstract class Mood implements BaseColumns {
        public static final String TABLE_NAME = "mood";

        public static final String MOOD_NAME = "mood_name";
        public static final String INSRTD_TSMP = "insrtd_tsmp";


        public static final String[] moodColumns = {
                Mood._ID,
                Mood.MOOD_NAME,
                Mood.INSRTD_TSMP

        };



    }

    public static abstract class Distraction implements BaseColumns {
        public static final String TABLE_NAME = "distraction";

        public static final String INSRTD_TSMP = "insrtd_tsmp";


        public static final String[] distractColumns = {
                Mood._ID,
                Mood.INSRTD_TSMP

        };



    }




}
