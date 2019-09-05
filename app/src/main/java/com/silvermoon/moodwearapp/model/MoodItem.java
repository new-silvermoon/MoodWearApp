package com.silvermoon.moodwearapp.model;

import java.io.Serializable;

public class MoodItem implements Serializable {

    private int moodImageId;
    private String moodName;
    private long insertTime;

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }

    public MoodItem(int moodImageId, String moodName) {
        this.moodImageId = moodImageId;
        this.moodName = moodName;
    }

    public MoodItem(String moodName, long insertTime) {
        this.moodName = moodName;
        this.insertTime = insertTime;
    }

    public int getMoodImageId() {
        return moodImageId;
    }

    public void setMoodImageId(int moodImageId) {
        this.moodImageId = moodImageId;
    }

    public String getMoodName() {
        return moodName;
    }

    public void setMoodName(String moodName) {
        this.moodName = moodName;
    }


}
