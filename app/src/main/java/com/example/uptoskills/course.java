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
import com.example.uptoskills.CourseFragments.AIFragment;
import com.example.uptoskills.CourseFragments.AerospaceFragment;
import com.example.uptoskills.CourseFragments.Agriculture;
import com.example.uptoskills.CourseFragments.AllCoursesFragment;
import com.example.uptoskills.CourseFragments.ArchitectureFragment;
import com.example.uptoskills.CourseFragments.CivilFragment;
import com.example.uptoskills.database.coursedatabase;
import com.google.android.material.tabs.TabLayout;

public class course extends AppCompatActivity {
    static RecyclerView rv ;
    CustomAdapter2 cax;
    public static int vlog_position = -1;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
//        rv = findViewById(R.id.list_Mode);
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
        showFragment(new AllCoursesFragment());
        // add fragment which use logic as to each tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        showFragment(new AllCoursesFragment());
                        Toast.makeText(course.this,"All",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        showFragment(new AerospaceFragment());
                        Toast.makeText(course.this,"Aerospace",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(course.this,"Agriculture",Toast.LENGTH_SHORT).show();
                        showFragment(new Agriculture());
                        break;
                    case 3:
                        showFragment(new ArchitectureFragment());
                        Toast.makeText(course.this,"Architecture",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        showFragment(new AIFragment());
                        Toast.makeText(course.this,"AI",Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        showFragment(new CivilFragment());
                        Toast.makeText(course.this,"civil Engineering",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        showFragment(new AllCoursesFragment());
                        break;
                   //add more
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
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
