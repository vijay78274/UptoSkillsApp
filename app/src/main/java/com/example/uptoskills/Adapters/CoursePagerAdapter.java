package com.example.uptoskills.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.uptoskills.CourseFragments.AllCoursesFragment;

public class CoursePagerAdapter extends FragmentPagerAdapter {

    public CoursePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Return the corresponding fragment for each tab
        switch(position) {
            case 0:
                return new AllCoursesFragment(); // Fragment for Tab 1
            default:
                return new AllCoursesFragment(); // Default fragment
        }
    }

    @Override
    public int getCount() {
        return 23; // Total number of tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Return tab title here based on position
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
        return tabTitles[position];
    }
}
