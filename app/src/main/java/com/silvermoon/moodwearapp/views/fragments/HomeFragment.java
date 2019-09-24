package com.silvermoon.moodwearapp.views.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.wearable.activity.ConfirmationActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.silvermoon.moodwearapp.R;
import com.silvermoon.moodwearapp.model.MoodContract;
import com.silvermoon.moodwearapp.services.DBIntentService;

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
                DBIntentService.startInsertion(v.getContext(), MoodContract.Distraction.TABLE_NAME,null);

                Intent intent = new Intent(v.getContext(), ConfirmationActivity.class);
                intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                        ConfirmationActivity.SUCCESS_ANIMATION);
                intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                        "Distracted");
                v.getContext().startActivity(intent);
                break;
        }
    }

}
