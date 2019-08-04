package com.example.stay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.stay.ui.main.SectionsPagerAdapter2;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


public class MngRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mng_room);
        SectionsPagerAdapter2 sectionsPagerAdapter = new SectionsPagerAdapter2(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public void onUpdateRoom(View view){
        Intent intentupRoom = new Intent(this,UpdateRoomActivity.class);
        startActivity(intentupRoom);
    }

}