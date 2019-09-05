package com.silvermoon.moodwearapp.views.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.wear.widget.WearableLinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silvermoon.moodwearapp.MainActivity;
import com.silvermoon.moodwearapp.R;
import com.silvermoon.moodwearapp.adapters.MoodAdapter;
import com.silvermoon.moodwearapp.model.MoodItem;
import android.support.wear.widget.WearableRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoodFragment extends Fragment {


    WearableRecyclerView rvmoods;

    public MoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mood, container, false);

        List<MoodItem> moodsList = createDummyData();

        MoodAdapter moodAdapter = new MoodAdapter(getContext(),moodsList);

        rvmoods = view.findViewById(R.id.rvMoods);
        rvmoods.setLayoutManager(new WearableLinearLayoutManager(getContext()));
        rvmoods.setCircularScrollingGestureEnabled(true);
        rvmoods.setBezelFraction(0.5f);
        rvmoods.setScrollDegreesPerScreen(90);
        rvmoods.setAdapter(moodAdapter);

        return view;
    }

    private List<MoodItem> createDummyData() {

        List<MoodItem> moodItemList = new ArrayList<>();

        MoodItem m1 = new MoodItem(R.drawable.excited,"Excited");
        MoodItem m2 = new MoodItem(R.drawable.happy,"Happy");
        MoodItem m3 = new MoodItem(R.drawable.neutral,"Neutral");
        MoodItem m4 = new MoodItem(R.drawable.sad,"Sad");
        MoodItem m5 = new MoodItem(R.drawable.low,"Low");

        moodItemList.add(m1);
        moodItemList.add(m2);
        moodItemList.add(m3);
        moodItemList.add(m4);
        moodItemList.add(m5);

        return moodItemList;

    }

    class CustomScrollingLayoutCallback extends WearableLinearLayoutManager.LayoutCallback {
        /** How much should we scale the icon at most. */
        private static final float MAX_ICON_PROGRESS = 0.65f;

        private float progressToCenter;

        @Override
        public void onLayoutFinished(View child, RecyclerView parent) {

            // Figure out % progress from top to bottom
            float centerOffset = ((float) child.getHeight() / 2.0f) / (float) parent.getHeight();
            float yRelativeToCenterOffset = (child.getY() / parent.getHeight()) + centerOffset;

            // Normalize for center
            progressToCenter = Math.abs(0.5f - yRelativeToCenterOffset);
            // Adjust to the maximum scale
            progressToCenter = Math.min(progressToCenter, MAX_ICON_PROGRESS);

            child.setScaleX(1 - progressToCenter);
            child.setScaleY(1 - progressToCenter);
        }
    }

}
