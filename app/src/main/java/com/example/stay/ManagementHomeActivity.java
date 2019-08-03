package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ManagementHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_home);
    }

    public void onBorder(View view){
        Intent intent = new Intent(this,MngBorderActivity.class);
        startActivity(intent);
    }

    public void onRoom(View view){
        Intent intent = new Intent(this,MngRoomActivity.class);
        startActivity(intent);
    }
    //55
}
