package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BoarderLeaveHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_leave_home);
    }

    public void onEarlyLeaveReq(View view) {
        Intent intent = new Intent(this,BoarderLeaveRequest.class);
        startActivity(intent);
    }

    public void LeaveReqView(View view) {
        Intent intent = new Intent(this,BoarderViewLeaveActivity.class);
        startActivity(intent);
    }
}
