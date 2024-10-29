package com.example.uptoskills.CourseFragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private FragmentCourse_Adapter fragmentAdpater;
    private static final String TAG = "AllCoursesFragment";

    public AllCoursesFragment() {

    }

    public static AllCoursesFragment newInstance(String param1, String param2) {
        AllCoursesFragment fragment = new AllCoursesFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coursedatabase.name.clear();
        coursedatabase.typeofprograme.clear();
        coursedatabase.name.add("Visual communication design for digital media");
        coursedatabase.name.add("Urban land use and transport palnning");
        coursedatabase.name.add("Environmental impact assessment");
        coursedatabase.name.add("Architectural Acoustics");
        coursedatabase.name.add("Dairy and Food Process and products technology");
        coursedatabase.name.add("Soil and Water conserving Engineering");
        coursedatabase.name.add("Thermal Processing of Food");
        coursedatabase.name.add("Organic Farming");

        coursedatabase.typeofprograme.add("Architecture");
        coursedatabase.typeofprograme.add("Architecture");
        coursedatabase.typeofprograme.add("Architecture");
        coursedatabase.typeofprograme.add("Architecture");
        coursedatabase.typeofprograme.add("Agriculture");
        coursedatabase.typeofprograme.add("Agriculture");
        coursedatabase.typeofprograme.add("Agriculture");
        coursedatabase.typeofprograme.add("Agriculture");
        Map<String,List<String>> map = new HashMap<String, List<String>>() {};
        for (int i = 0; i < coursedatabase.name.size(); i++) {
            String programType = coursedatabase.typeofprograme.get(i);
            String courseName = coursedatabase.name.get(i);
            map.putIfAbsent(programType, new ArrayList<>());

            map.get(programType).add(courseName);
        }
        Log.d("map:","initialized");
        String len = String.valueOf(map.get("Agriculture"));
        for(Map.Entry<String,List<String>> entry : map.entrySet()){
            String key = entry.getKey();
            String value= entry.getValue().toString();
            Log.d(key,value);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_courses, container, false);
        recyclerView = view.findViewById(R.id.list);
        if (recyclerView != null) {
            Log.d(TAG, "RecyclerView initialized successfully.");
        } else {
            Log.e(TAG, "RecyclerView is null!");
        }

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        fragmentAdpater = new FragmentCourse_Adapter(getContext());
        if (fragmentAdpater != null) {
            Log.d(TAG, "Adapter initialized and set.");
        }
        recyclerView.setAdapter(fragmentAdpater);

        return view;
    }
}
