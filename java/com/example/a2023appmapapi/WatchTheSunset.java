package com.example.a2023appmapapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class WatchTheSunset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_watch_the_sunset);




        String url = "https://api.sunrise-sunset.org/json?lat=37.229573&lng=-80.413939&date=today";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Handle the API response...
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject resultsObject = jsonObject.getJSONObject("results");

                    /**
                     * Strings are in universal time. We need to convert back.
                     */
                    String sunset = resultsObject.getString("sunset");

                    /**
                     * Display text.
                     */
                    TextView sunsetTextView = findViewById(R.id.data1);

                    sunsetTextView.setText(sunset);

                } catch (JSONException e) {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle network error...

            }
        });

        queue.add(request);
    }



}