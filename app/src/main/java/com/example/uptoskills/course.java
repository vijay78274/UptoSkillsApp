package com.example.uptoskills;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.uptoskills.Adapters.CustomAdapter2;
import com.example.uptoskills.CourseFragments.AllCoursesFragment;
import com.example.uptoskills.database.coursedatabase;
import com.google.android.material.tabs.TabLayout;

public class course extends AppCompatActivity {
    static RecyclerView rv ;
    public static int vlog_position = -1;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AllCoursesFragment allCoursesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(course.this , Main2Activity.class));

            }
        });

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        // Add tabs manually based on the provided image
        String[] tabTitles = {
                "ALL", "AEROSPACE ENGINEERING", "AGRICULTURE", "ARCHITECTURE",
                "ARTIFICIAL INTELLIGENCE (AI)", "CIVIL ENGINEERING", "COMMERCE",
                "COMPUTER SCIENCE ENGINEERING", "COMPUTER SCIENCE/IT", "ECONOMICS",
                "ELECTRICAL/ELECTRONICS", "EVERYONE CAN LEARN", "HEALTH AND BEAUTY",
                "LAW", "MANAGEMENT", "MARKETING", "MECHANICAL ENGINEERING",
                "OCEAN ENGINEERING", "PHILOSOPHY", "PSYCHOLOGY",
                "SKILL DEVELOPMENT TRAINING", "TEXTILE ENGINEERING",
                "WEBSITE & APP DEVELOPMENT"
        };

        // Add the tabs dynamically
        for (String title : tabTitles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        // Initialize the database
        coursedatabase.initializeDatabase();

        // Show the initial fragment
        showFragment(AllCoursesFragment.newInstance());

        // Set the listener for tab selection
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String selectedType = tab.getText().toString();
                showFragment(AllCoursesFragment.newInstance(selectedType));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void showFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FragmentCont, fragment);
        fragmentTransaction.commit();
    }
}



//        rv.setLayoutManager(new GridLayoutManager(this,2));
//        rv.addOnItemTouchListener(
//                new RecyclerItemClickListener(course.this, rv ,new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        vlog_position = position;
//                        // do whatever
//                        startActivity(new Intent(course.this , courseview.class));
//                    }
//
//                    @Override
//                    public void onLongItemClick(View view, int position) {
//                    }
//                })
//        );
//
//
//
//
//        cax = new CustomAdapter2(course.this);
//        rv.setAdapter(cax);
