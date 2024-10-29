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

    //test purpose
    private final List<String> name;
    private final List<String> image_url;
    private final List<String> content;
    private final List<String> duration;
    private final List<String> count_students;
    private final List<String> rating;
    private final List<String> price_rendered;
    private final List<String> origin_price_rendered;
    private final List<String> typeofprograme;

    private final Context context;

    public FragmentCourse_Adapter(Context context) {
        this.context = context;
        this.name = new ArrayList<>();
        this.image_url = new ArrayList<>();
        this.duration = new ArrayList<>();
        this.count_students = new ArrayList<>();
        this.rating = new ArrayList<>();
        this.content = new ArrayList<>();
        this.price_rendered = new ArrayList<>();
        this.origin_price_rendered = new ArrayList<>();
        this.typeofprograme = new ArrayList<>();
        for (int i = 0; i < coursedatabase.name.size(); i++) {
            name.add(coursedatabase.name.get(i));
//            image_url.add(coursedatabase.image_url.get(i));
//            duration.add(coursedatabase.duration.get(i));
//            count_students.add(coursedatabase.count_students.get(i));
//            rating.add(coursedatabase.rating.get(i));
//            content.add(coursedatabase.content.get(i));
//            price_rendered.add(coursedatabase.price_rendered.get(i));
//            origin_price_rendered.add(coursedatabase.origin_price_rendered.get(i));
            typeofprograme.add(coursedatabase.typeofprograme.get(i));
            Log.d(TAG, "Adapter initialized with data size: " + name.size());
        }
    }

    @NonNull
    @Override
    public FragmentCourse_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_courses, parent, false);
        Log.d(TAG, "onCreateViewHolder called.");

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder called for position: " + position);
        holder.courseType.setText(typeofprograme.get(position));
        holder.coursename.setText((name.get(position)));

        holder.enroll.setOnClickListener(v -> {
            //do  or send to website or open new activity to enroll screen
            Toast.makeText(context, "Enroll clicked for " + name.get(position), Toast.LENGTH_SHORT).show();
        });

        // Show the ProgressBar before loading the image
//        holder.p1.setVisibility(View.VISIBLE);

        // Load image with Glide and control ProgressBar visibility
//        Glide.with(context)
//                .load(image_url.get(position))
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        // Hide the ProgressBar when the load fails
//                        holder.p1.setVisibility(View.GONE);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        // Hide the ProgressBar when the load is successful
//                        holder.p1.setVisibility(View.GONE);
//                        return false;
//                    }
//                })
//                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount called. Item count: " + name.size());
        return coursedatabase.name.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseType;
        TextView coursename;
//        ImageView img;
        ProgressBar p1;
       TextView enroll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseType = itemView.findViewById(R.id.computerSciTextView);
            coursename = itemView.findViewById(R.id.projectTitleTextView);
//            img = itemView.findViewById(R.id.courseImage);
            p1 = itemView.findViewById(R.id.progressCourseBar);
            enroll =itemView.findViewById(R.id.enrollNowTextView);

        }
    }
}
