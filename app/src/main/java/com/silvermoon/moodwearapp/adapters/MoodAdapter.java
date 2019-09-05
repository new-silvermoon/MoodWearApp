package com.silvermoon.moodwearapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.wearable.activity.ConfirmationActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.silvermoon.moodwearapp.R;
import com.silvermoon.moodwearapp.model.MoodContract;
import com.silvermoon.moodwearapp.model.MoodItem;
import com.silvermoon.moodwearapp.services.DBIntentService;
import com.silvermoon.moodwearapp.utils.Constants;

import java.util.List;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder>{


    private List<MoodItem> moodItemList;
    private Context context;

    public MoodAdapter(Context context, List<MoodItem> moodItemList) {
        this.context = context;
        this.moodItemList = moodItemList;

    }

    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MoodViewHolder(LayoutInflater.from(context).inflate(R.layout.mood_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder moodViewHolder, int i) {

        final MoodItem moodItem = moodItemList.get(i);

        moodViewHolder.tvMood.setText(moodItem.getMoodName());
        moodViewHolder.ivMood.setImageResource(moodItem.getMoodImageId());

        moodViewHolder.tvMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putSerializable(Constants.CONTENT_VALUE_BUNDLE_KEY,moodItem);

                DBIntentService.startInsertion(v.getContext(), MoodContract.Mood.TABLE_NAME,b);

                Intent intent = new Intent(v.getContext(), ConfirmationActivity.class);
                intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                        ConfirmationActivity.SUCCESS_ANIMATION);
                intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                        "Mood saved");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moodItemList.size();
    }

    static class MoodViewHolder extends RecyclerView.ViewHolder{

        ImageView ivMood;
        TextView tvMood;

        public MoodViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivMood = itemView.findViewById(R.id.ivMood);
            this.tvMood = itemView.findViewById(R.id.tvMoodText);
        }




    }
}
