package com.silvermoon.moodwearapp.views.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.silvermoon.moodwearapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    private ImageButton ibMood,ibDistract;

    public interface HomeButtonClickListerner{
        public void onMoodButtonClick();
        public void onDistractButtonClick();
    }

    public HomeButtonClickListerner homeFragCallBack;

    public void setCallBackListener(HomeButtonClickListerner homeFragCallBack){
        this.homeFragCallBack = homeFragCallBack;
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        ibMood = v.findViewById(R.id.ibMood);
        ibDistract = v.findViewById(R.id.ibDistract);

        ibMood.setOnClickListener(this);
        ibDistract.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.ibMood:
                homeFragCallBack.onMoodButtonClick();
                break;
            case R.id.ibDistract:
                break;
        }
    }

}
