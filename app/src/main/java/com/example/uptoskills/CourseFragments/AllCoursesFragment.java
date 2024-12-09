package com.example.uptoskills.CourseFragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uptoskills.CourseModel;
import com.example.uptoskills.Fragmentadapter.FragmentCourse_Adapter;
import com.example.uptoskills.R;
import com.example.uptoskills.database.coursedatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AllCoursesFragment extends Fragment {

    private RecyclerView recyclerView;
    List<CourseModel> models = new ArrayList<>();
    List<String> typeList = new ArrayList<>();
    private FragmentCourse_Adapter fragmentAdpater;
    private static final String TAG = "AllCoursesFragment";
    public AllCoursesFragment() {

    }

    public static AllCoursesFragment newInstance(String programType) {
        AllCoursesFragment fragment = new AllCoursesFragment();
        Bundle args = new Bundle();
        args.putString("coursetype", programType); // Passing program type
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("map:","initialized");
//        String len = String.valueOf(coursedatabase.map.get("Agriculture"));
        for(Map.Entry<String,List<CourseModel>> entry : coursedatabase.map.entrySet()){
            String key = entry.getKey();
            String value= entry.getValue().toString();
            if(key.equals("Agriculture"))
                Log.d(key+" ",value);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        coursedatabase.initializeDatabase();
//        String courseType = getArguments() != null ? getArguments().getString("coursetype", "ALL") : "ALL";
        String courseType = "All";
        if (getArguments() != null) {
            courseType = getArguments().getString("coursetype");
        }
        View view = inflater.inflate(R.layout.fragment_all_courses, container, false);
        recyclerView = view.findViewById(R.id.list);
        if (recyclerView != null) {
            Log.d(TAG, "RecyclerView initialized successfully.");
        } else {
            Log.e(TAG, "RecyclerView is null!");
        }

//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentAdpater = new FragmentCourse_Adapter(getContext(),models,typeList);
        if (fragmentAdpater != null) {
            Log.d(TAG, "Adapter initialized and set.");
        }
        recyclerView.setAdapter(fragmentAdpater);
        loadData(courseType);
        fragmentAdpater.notifyDataSetChanged();
        return view;
    }
    public void loadData(String courseType){
        Log.d("loadData", "Inside loadData with courseType: " + courseType);
//        nameList.clear();
//        typeList.clear();
        if(!coursedatabase.map.isEmpty())
            Log.d("loadData: ","map is not Empty");
        if (courseType.equalsIgnoreCase("All")) {
            for (Map.Entry<String, List<CourseModel>> entry : coursedatabase.map.entrySet()) {
                List<CourseModel> valueList = entry.getValue(); // Get the associated list of courses
                models.addAll(valueList); // Add all courses to nameList
                // Populate typeList with the corresponding key for each course
                for (CourseModel course : valueList) {
                    typeList.add(entry.getKey()); // Add the course type
                }
            }
        } else {
            // If courseType is not "All," add specific values
            for (Map.Entry<String, List<CourseModel>> entry : coursedatabase.map.entrySet()) {
                String key = entry.getKey(); // Get the key (course type)
                List<CourseModel> valueList = entry.getValue(); // Get the associated list of courses

//                Log.d("Comparing", "Key: " + key + ", CourseType: " + courseType);

                // Check if the key matches the courseType
                if (key.equalsIgnoreCase(courseType.trim())) {
                    // Add all items from the valueList to nameList
                    models.addAll(valueList);
                    Log.d("Matched", "Key: " + key + " Values: " + valueList);

                    // Populate typeList based on the number of courses added to nameList
                    for (CourseModel course : valueList) {
                        typeList.add(courseType); // Add the specific course type
                    }
                }
            }
        }
        if (models.isEmpty()) {
            Log.d("NameList", "is Empty");
        } else {
            Log.d("NameList", "Loaded items: " + models);
        }
    }
}
