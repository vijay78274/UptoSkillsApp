package com.example.uptoskills.CourseFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.uptoskills.Fragmentadapter.FragmentCourse_Adapter;
import com.example.uptoskills.R;
import com.example.uptoskills.database.coursedatabase;

public class Agriculture extends Fragment {
    private RecyclerView recyclerView;
    private FragmentCourse_Adapter fragmentAdpater;
    private static final String TAG = "Agriculture";
    public Agriculture(){

    }
    public static Agriculture newInstance(String param1, String param2) {
        Agriculture fragment = new Agriculture();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        coursedatabase.name.clear();
        coursedatabase.typeofprograme.clear();
            coursedatabase.name.add("Dairy and Food Process and products technology");
            coursedatabase.name.add("Soil and Water conserving Engineering");
            coursedatabase.name.add("Thermal Processing of Food");
            coursedatabase.name.add("Organic Farming");

            coursedatabase.typeofprograme.add("Agriculture");
            coursedatabase.typeofprograme.add("Agriculture");
            coursedatabase.typeofprograme.add("Agriculture");
            coursedatabase.typeofprograme.add("Agriculture");
        fragmentAdpater = new FragmentCourse_Adapter(getContext());
        if (fragmentAdpater != null) {
            Log.d(TAG, "Adapter initialized and set.");
        }
        recyclerView.setAdapter(fragmentAdpater);

        return view;
    }
}
