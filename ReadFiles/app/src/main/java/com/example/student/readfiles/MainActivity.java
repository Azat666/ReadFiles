package com.example.student.readfiles;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);
    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    BlankFragment fragment = new BlankFragment(0);
                    return fragment;
                case 1:
                    BlankFragment fragment1 = new BlankFragment(1);
                    return fragment1;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}






