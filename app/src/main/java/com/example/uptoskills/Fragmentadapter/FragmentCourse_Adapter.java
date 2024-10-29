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

import java.util.ArrayList;
import java.util.List;

public class FragmentCourse_Adapter extends RecyclerView.Adapter<FragmentCourse_Adapter.ViewHolder> {
    private static final String TAG = "FragmentCourse_Adapter";

    private final Context context;
    private List<String> namelist=new ArrayList<>();
    private List<String> typeList=new ArrayList<>();
    public FragmentCourse_Adapter(Context context, List<String> nameList, List<String> typeList) {
        this.context = context;
        this.namelist=nameList;
        this.typeList=typeList;
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
        holder.courseType.setText(typeList.get(position));
        holder.coursename.setText((namelist.get(position)));

        holder.enroll.setOnClickListener(v -> {
            Toast.makeText(context, "Enroll clicked for " + namelist.get(position), Toast.LENGTH_SHORT).show();
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
        Log.d(TAG, "getItemCount called. Item count: " + namelist.size());
        return namelist.size();
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
