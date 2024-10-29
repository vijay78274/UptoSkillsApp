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
    List<String> courses;
    private RecyclerView recyclerView;
    private FragmentCourse_Adapter fragmentAdapter;
    List<String> courseTypes;
    private String currentCourseType = "ALL"; // Default to "ALL"

    public AllCoursesFragment() {}

    public static AllCoursesFragment newInstance(String currentCourseType) {
        return new AllCoursesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_courses, container, false);
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        coursedatabase.initializeDatabase();
        courseTypes=new ArrayList<>();
        for(int i=0;i<courses.size();i++){
            courseTypes.add(currentCourseType);
        }
        // Initialize adapter with an empty list initially
        fragmentAdapter = new FragmentCourse_Adapter(getContext(),courses,courseTypes);
        recyclerView.setAdapter(fragmentAdapter);
        // Load data for the default course type or any specific type passed
        loadCourses(currentCourseType);

        return view;
    }

    public void updateCourses(String courseType) {
        // Update the current course type and reload courses
        currentCourseType = courseType;
        loadCourses(courseType);
    }

    private void loadCourses(String courseType) {
        // Fetch all courses or filtered by course type
        if ("ALL".equals(courseType)) {
            courses = coursedatabase.name;
        } else {
            // Retrieve course list for the type or default to an empty list if not found
            courses = coursedatabase.map.getOrDefault(courseType, new ArrayList<>());
        }

        // Update adapter with the new list
        fragmentAdapter.setCourses(courses,courseTypes);
        fragmentAdapter.notifyDataSetChanged();
    }
}
