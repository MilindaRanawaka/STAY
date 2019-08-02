package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BoardersHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarders_home);
    }

    public void MyProfileClick(View view){
        Intent intent = new Intent(this,BoarderManageActivity.class);
        startActivity(intent);
    }
}
