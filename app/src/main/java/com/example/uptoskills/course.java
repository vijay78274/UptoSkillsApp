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
    public static int vlog_position = -1;

    private TabLayout tabLayout;
    private ViewPager viewPager;

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
        coursedatabase.initializeDatabase();
        showFragment("All");
        // add fragment which use logic as to each tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                String courseType = "All";
                switch (position) {
                    case 0:
                        courseType="All";
//                        Toast.makeText(course.this,"All",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        courseType="Aerospace Engineering";
                        break;
                    case 2:
                        courseType="Agriculture";
                        break;
                    case 3:
                        courseType="Architecture";
                        break;
                    case 4:
                        courseType="Artificial Intelligence";
                        break;
                    case 5:
                        courseType="Civil Engineering";
                        break;
                    case 6:
                        courseType="Commerce";
                        break;
                    case 7:
                        courseType="Computer Science Engineering";
                        break;
                    case 8:
                        courseType="Computer Science/IT";
                        break;
                    case 9:
                        courseType="Economics";
                        break;
                    case 10:
                        courseType="Electrical/Engineering";
                        break;
                    case 11:
                        courseType="Everyone can learn";
                        break;
                    default:
                        courseType="All";
                        break;
                }
                showFragment(courseType);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private void showFragment(String courseType) {
        AllCoursesFragment fragment = AllCoursesFragment.newInstance(courseType);
        Bundle args = new Bundle();
        args.putString("coursetype", courseType);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FragmentCont, fragment);
        fragmentTransaction.commit();
    }
}