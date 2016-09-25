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
    private Activity activity;
    private ArrayList<JobPosts> mJobPosts;
    private static LayoutInflater inflater = null;

    public JobPostsAdapter (Activity activity, int textViewResourceId,ArrayList<JobPosts> _mJobPosts) {
        super(activity, textViewResourceId, _mJobPosts);
        try {
            this.activity = activity;
            this.mJobPosts = _mJobPosts;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return mJobPosts.size();
    }

    public JobPosts getItem(JobPosts position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView city;
        public TextView service;
        public TextView description;
        public TextView budget;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.list_v, null);
                holder = new ViewHolder();

                holder.city = (TextView) vi.findViewById(R.id.city);
                holder.service = (TextView) vi.findViewById(R.id.service);
                holder.description = (TextView) vi.findViewById(R.id.description);
                holder.budget = (TextView) vi.findViewById(R.id.budget);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

//            holder.city.setText(mJobPosts.get(position).toString());
//            holder.service.setText(mJobPosts.get(position).toString());
//            holder.description.setText(mJobPosts.get(position).toString());
//            holder.budget.setText(mJobPosts.get(position).toString());
            holder.city.setText("1");
            holder.service.setText("2");
            holder.description.setText("3");
            holder.budget.setText("4");

        } catch (Exception e) {


        }
        return vi;
    }
}
