package com.example.sydevelopers.rbc_serve;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sanhar on 2016-09-25.
 */
public class JobPosts {
    public String mCity;
    public String mService;
    public String mDescription;
    public Integer mBudget;

    JobPosts(String City,String Service,String Description,Integer Budget){
        mCity = City;
        mService = Service;
        mDescription = Description;
        mBudget = Budget;
    }
}
