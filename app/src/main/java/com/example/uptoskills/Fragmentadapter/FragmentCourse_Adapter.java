package com.example.uptoskills.Fragmentadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.uptoskills.CourseModel;
import com.example.uptoskills.R;
import com.example.uptoskills.courseview;

import java.util.ArrayList;
import java.util.List;

public class FragmentCourse_Adapter extends RecyclerView.Adapter<FragmentCourse_Adapter.ViewHolder> {
    private static final String TAG = "FragmentCourse_Adapter";

    private final Context context;
//    private List<String> namelist=new ArrayList<>();
    private List<CourseModel> models = new ArrayList<>();
    private List<String> typeList=new ArrayList<>();
    public FragmentCourse_Adapter(Context context, List<CourseModel> models, List<String> typeList) {
        this.context = context;
//        this.namelist=nameList;
        this.models=models;
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
        CourseModel model = models.get(position);
        holder.coursename.setText(model.getName());
        holder.enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, courseview.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });

//        holder.enroll.setOnClickListener(v -> {
//            Toast.makeText(context, "Enroll clicked for " + namelist.get(position), Toast.LENGTH_SHORT).show();
//        });

        // Show the ProgressBar before loading the image
//        holder.p1.setVisibility(View.VISIBLE);

        // Load image with Glide and control ProgressBar visibility
        Glide.with(context)
                .load(model.getImage_url())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.d("error",e.getMessage());
                        holder.p1.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.p1.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount called. Item count: " + models.size());
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseType;
        TextView coursename;
        ImageView image;
//        ImageView img;
        ProgressBar p1;
       TextView enroll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseType = itemView.findViewById(R.id.computerSciTextView);
            coursename = itemView.findViewById(R.id.projectTitleTextView);
            image = itemView.findViewById(R.id.imageView);
            p1 = itemView.findViewById(R.id.progressCourseBar);
            enroll =itemView.findViewById(R.id.enrollNowTextView);

        }
    }
}
