package com.example.uptoskills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uptoskills.database.coursedatabase;

import org.json.JSONArray;
import org.json.JSONObject;

public class splash extends AppCompatActivity {
    public static String title;
    public static String content;
    public static String date;
    String urls = "";
    SharedPreferences s;

    String jobtype = "";

    public static String urli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        Log.d("Splash", "started");
        if (CheckNetwork.isInternetAvailable(splash.this)) {
            vlogs(); // Fetch blog/vlog data
            job(); // Fetch job data
            course(); //courses
        } else {
            Toast.makeText(this, "Check Your Internet", Toast.LENGTH_SHORT).show();
            System.exit(0);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                String check = "0";
                s = getSharedPreferences("db1", MODE_PRIVATE);
                check = s.getString("login", "");
                if (check.equals("1")) {
                    finish();
                    startActivity(new Intent(splash.this, Main2Activity.class));
                } else {
                    startActivity(new Intent(splash.this, login.class));
                }
            }
        }, 3000);
    }

    void vlogs() {
        RequestQueue queue = Volley.newRequestQueue(this);
        for (int i = 1; i <= 27; i++) {
            String url = "https://uptoskills.com/wp-json/wp/v2/posts?page=" + i;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        try {
                            JSONArray data = new JSONArray(response);
                            for (int j = 0; j < data.length(); j++) {
                                Log.d("Vlog data: ", data + "");
                                JSONObject obj = data.getJSONObject(j);
                                JSONObject obj2 = obj.getJSONObject("yoast_head_json");
                                JSONObject cont = obj.getJSONObject("content");
                                JSONArray image = obj2.getJSONArray("og_image");
                                JSONObject imagedata = image.getJSONObject(0);

                                String title = obj2.getString("title");
                                String date = obj.getString("date");
                                String content = cont.getString("rendered");
                                String urlImage = imagedata.getString("url");

                                blogdatabase.title1.add(title);
                                blogdatabase.url1.add(urlImage);
                                blogdatabase.description1.add(content);
                                blogdatabase.date1.add(date);
                            }
                        } catch (Exception e) {
                            Log.e("VlogsError", "Parsing error: " + e.getMessage());
                        }
                    }, error -> Log.e("VlogsError", "Request failed: " + error.getMessage())
            );

            queue.add(stringRequest);
        }
    }

    void job() {
        Log.d("job section: ", "started");

        // Check network status
        if (CheckNetwork.isInternetAvailable(splash.this)) {
            String url = "https://uptoskills.com/rest-api-jobs/";
            RequestQueue queue = Volley.newRequestQueue(splash.this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                            Log.d("Job Response", response);
                            try {
                                JSONArray data = new JSONArray(response);
//                                Log.d("Job Data", data.toString());
                                for (int i = 0; i < data.length(); i++) {
                                    Log.d("Job Data", data+""); // Log each job entry

                                    JSONObject obj = data.getJSONObject(i);
//                                    JSONObject obj2 = obj.getJSONObject("jobtitle");
//                                    JSONObject obj3 = obj.getJSONObject("content");
//                                    JSONObject obj4 = obj.getJSONObject("meta");
//                                    JSONObject links = obj.getJSONObject("_links");
//                                    JSONArray term = links.getJSONArray("wp:term");
//                                    JSONObject link = term.getJSONObject(0);
//                                    String date = obj.getString("date");
//                                    String title = obj2.getString("rendered");
                                    String title = obj.getString("jobtitle");
                                    String companyName = obj.getString("companyname");
                                    String jobType = obj.getString("jobtype");
                                    String jobDescription = obj.getString("jobdesc");
                                    String workLocation = obj.getString("worklocation");
                                    String minEducation = obj.getString("mineducation");
//                                    String innerdata = obj3.getString("rendered");
//                                    String outerdata = obj4.getString("_company_tagline");
//                                    String location = obj4.getString("_job_location");
//                                    String salary = obj4.getString("_job_salary");

//                                    jobdatabase.date.add(date);
                                    jobdatabase.title.add(title);
                                    jobdatabase.companyName.add(companyName);
                                    jobdatabase.jobType.add(jobType);
                                    jobdatabase.description.add(jobDescription);
                                    jobdatabase.location.add(workLocation);
                                    jobdatabase.education.add(minEducation);
//                                    urls = link.getString("href");
                                }
//                                rest();
                                Log.d("Job database: ",jobdatabase.title.size()+"");
                            } catch (Exception e) {
                                Log.e("Job Parsing Error", "Error parsing the job data: " + e.getMessage());
                            }
                        }
                    }, error -> {
                Log.e("Job Request Error", "Request failed: " + error.getMessage());
                // Log network error details
                if (error.networkResponse != null) {
                    Log.e("Job Response Error", "Status code: " + error.networkResponse.statusCode);
                }
                Toast.makeText(splash.this, "Error fetching job data", Toast.LENGTH_SHORT).show();
            });

            queue.add(stringRequest);
        } else {
            Log.e("Network Error", "No internet connection");
        }
    }


//    void rest() {
//        if (urls.isEmpty()) {
//            Log.e("RestAPIError", "URL is empty");
//            return;
//        }
//
//        String url1 = urls;
//        RequestQueue queue1 = Volley.newRequestQueue(splash.this);
//        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONArray data = new JSONArray(response);
//                            JSONObject obj = data.getJSONObject(0);
//                            jobtype = obj.getString("name");
//                            jobdatabase.jobtype.add(jobtype);
//                            Log.d("JobType", jobtype);
//                        } catch (Exception e) {
//                            Log.e("RestAPIError", "Parsing error: " + e.getMessage());
//                        }
//                    }
//                }, error -> Log.e("RestAPIError", "Request failed: " + error.getMessage())
//        );
//
//        queue1.add(stringRequest1);
//    }

    void course() {
        String url = "https://uptoskills.com/wp-json/learnpress/v1/courses";
        RequestQueue queue = Volley.newRequestQueue(splash.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray data = new JSONArray(response);
                            Log.d("courses", data + "");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject obj = data.getJSONObject(i);
                                String name = obj.getString("name");
                                String imageurl = obj.getString("image");
                                String duration = obj.getString("duration");
                                String rating = obj.getString("rating");
                                String price_rendered = obj.getString("price_rendered");
                                String origin_price_rendered = obj.getString("origin_price_rendered");
                                JSONArray cat = obj.getJSONArray("categories");
                                String typeofprograme = cat.getJSONObject(0).getString("name");

                                coursedatabase.name.add(name);
                                coursedatabase.image_url.add(imageurl);
                                coursedatabase.duration.add(duration);
                                coursedatabase.rating.add(rating);
                                coursedatabase.price_rendered.add(price_rendered);
                                coursedatabase.origin_price_rendered.add(origin_price_rendered);
                                coursedatabase.typeofprograme.add(typeofprograme);
                            }

                            // Log course data
                            for (int i = 0; i < coursedatabase.name.size(); i++) {
                                Log.d("coursesName: ", coursedatabase.name.get(i));
                                Log.d("programType: ", coursedatabase.typeofprograme.get(i));
                                Log.d("courseImage: ", coursedatabase.image_url.get(i));
                            }
                        } catch (Exception e) {
                            Log.e("CourseAPIError", "Error in fetching course data: " + e.getMessage());
                        }
                    }
                }, error -> Log.e("CourseAPIError", "Request failed: " + error.getMessage())
        );

        queue.add(stringRequest);
    }

}