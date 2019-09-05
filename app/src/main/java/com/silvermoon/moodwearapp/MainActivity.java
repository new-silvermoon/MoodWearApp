package com.silvermoon.moodwearapp;


import android.app.Fragment;
import android.os.Bundle;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.silvermoon.moodwearapp.views.fragments.HomeFragment;
import com.silvermoon.moodwearapp.views.fragments.MoodFragment;

public class MainActivity extends WearableActivity implements HomeFragment.HomeButtonClickListerner {



    private FragmentManager fm;
    private FragmentTransaction ft;
    private MoodFragment moodFragment;
    private HomeFragment homeFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enables Always-on
        setAmbientEnabled();


        fm = getFragmentManager();
        ft = fm.beginTransaction();
        moodFragment = new MoodFragment();
        homeFragment = new HomeFragment();

        ft.replace(R.id.flMain,homeFragment).commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {

        if(fragment instanceof HomeFragment){

            homeFragment.setCallBackListener(this);

        }
    }

    @Override
    public void onMoodButtonClick() {

        ft = fm.beginTransaction();
        ft.replace(R.id.flMain,moodFragment).commit();

    }

    @Override
    public void onDistractButtonClick() {

    }
}
