package com.silvermoon.moodwearapp.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.silvermoon.moodwearapp.BaseApplication;
import com.silvermoon.moodwearapp.model.DistractItem;
import com.silvermoon.moodwearapp.model.MoodContract;
import com.silvermoon.moodwearapp.model.MoodItem;
import com.silvermoon.moodwearapp.utils.Constants;
import com.silvermoon.moodwearapp.utils.MoodDBOperations;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DBIntentService extends IntentService {

    private static final String ACTION_INSERT = (BaseApplication.getappContext().getPackageName()).toLowerCase()+"action.insert";


    private static final String CONTENT_PARAM = (BaseApplication.getappContext().getPackageName()).toLowerCase()+"extra.content";;
    private static final String TABLE_PARAM = (BaseApplication.getappContext().getPackageName()).toLowerCase()+"extra.tablename";;

    private static final String TAG = DBIntentService.class.getSimpleName();

    public DBIntentService() {
        super("DBIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startInsertion(Context context, String tableName, Bundle bundle) {
        Intent intent = new Intent(context, DBIntentService.class);
        intent.setAction(ACTION_INSERT);
        intent.putExtra(TABLE_PARAM, tableName);
        intent.putExtra(CONTENT_PARAM, bundle);
        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INSERT.equals(action)) {
                final String tableName = intent.getStringExtra(TABLE_PARAM);
                final Bundle bundle = intent.getBundleExtra(CONTENT_PARAM);

                ContentValues cv = new ContentValues();

                switch(tableName){
                    case MoodContract.Mood.TABLE_NAME:
                        MoodItem item = (MoodItem) bundle.getSerializable(Constants.CONTENT_VALUE_BUNDLE_KEY);
                        cv.put(MoodContract.Mood.MOOD_NAME,item.getMoodName());
                        cv.put(MoodContract.Mood.INSRTD_TSMP,item.getInsertTime());
                        break;
                    case MoodContract.Distraction.TABLE_NAME:
                        DistractItem ditem = (DistractItem) bundle.getSerializable(Constants.CONTENT_VALUE_BUNDLE_KEY);
                        cv.put(MoodContract.Distraction.INSRTD_TSMP,ditem.getIsnertTime());
                        break;

                }


                insertToDB(tableName, cv);
            } 
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void insertToDB(String tableName, ContentValues cv) {
        MoodDBOperations dbOperations = new MoodDBOperations(this);
        long result = dbOperations.insertValues(tableName,cv);

        Log.i(TAG, "insertToDB: result "+Long.toString(result));
    }


}
