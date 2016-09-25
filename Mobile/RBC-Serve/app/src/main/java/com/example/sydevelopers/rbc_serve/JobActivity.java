package com.example.sydevelopers.rbc_serve;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JobActivity extends AppCompatActivity {
    ArrayList<JobPosts> mJobPosts;
    JobPostsAdapter mJobPostsAdapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        mJobPosts = new ArrayList<JobPosts>();
        lv = (ListView) findViewById(R.id.job_list);
        String url = "http://5cacf9ed.ngrok.io/api/get_posts_android";
        getJSArr(url);
    }

    private void getJSArr(String url) {
        JsonArrayRequest jsArrRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("response", response.toString());
                        fillList(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                        fillList(null);
                    }

                });
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrRequest);
    }

    private void fillList(JSONArray response) {
        if (response != null) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsObject = response.getJSONObject(i);
                    JobPosts jobpost = new JobPosts(jsObject.getString("city"),jsObject.getString("service"),
                            jsObject.getString("description"),jsObject.getInt("budget"));
                    mJobPosts.add(jobpost);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            populateUI();
        }
    }

    private void populateUI(){
        mJobPostsAdapter= new JobPostsAdapter (JobActivity.this, mJobPosts);
        lv.setAdapter(mJobPostsAdapter);
    }
}
