package com.example.sydevelopers.rbc_serve;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sanhar on 2016-09-25.
 */
public class JobPostsAdapter extends ArrayAdapter<JobPosts> {
    public JobPostsAdapter(Context context, ArrayList<JobPosts> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        JobPosts mJobPosts = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_v, parent, false);
        }
        // Lookup view for data population
        TextView city = (TextView) convertView.findViewById(R.id.city);
        TextView service = (TextView) convertView.findViewById(R.id.service);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView budget = (TextView) convertView.findViewById(R.id.budget);

        // Populate the data into the template view using the data object
        city.setText(mJobPosts.mCity);
        service.setText(mJobPosts.mService);
        description.setText(mJobPosts.mDescription);
        budget.setText(Integer.toString(mJobPosts.mBudget));
        // Return the completed view to render on screen
        return convertView;
    }
}
