package com.example.uptoskills.Fragmentadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uptoskills.R;
import com.example.uptoskills.database.coursedatabase;

import java.util.ArrayList;
import java.util.List;

public class FragmentCourse_Adapter extends RecyclerView.Adapter<FragmentCourse_Adapter.ViewHolder> {
    private static final String TAG = "FragmentCourse_Adapter";

    private List<String> courseNames;
    private List<String> courseTypes; // New field for course types
    private Context context;

    public FragmentCourse_Adapter(Context context, List<String> courseNames, List<String> courseTypes) {
        this.context = context;
        this.courseNames = courseNames;
        this.courseTypes = courseTypes; // Initialize course types
    }

    public void setCourses(List<String> names, List<String> types) {
        this.courseNames = names;
        this.courseTypes = types; // Update the course types
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_courses, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.coursename.setText(courseNames.get(position));
        holder.courseType.setText(courseTypes.get(position)); // Set the course type

        holder.enroll.setOnClickListener(v -> {
            Toast.makeText(context, "Enroll clicked for " + courseNames.get(position), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return courseNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseType;
        TextView coursename;
        ProgressBar p1;
        TextView enroll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseType = itemView.findViewById(R.id.computerSciTextView); // Update to match your layout
            coursename = itemView.findViewById(R.id.projectTitleTextView);
            p1 = itemView.findViewById(R.id.progressCourseBar);
            enroll = itemView.findViewById(R.id.enrollNowTextView);
        }
    }
}
