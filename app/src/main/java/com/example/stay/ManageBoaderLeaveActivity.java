package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Database.LeaveReq;

public class ManageBoaderLeaveActivity extends AppCompatActivity {

    LeaveReq leaveReq;
    TextView NameTxt,RoomTxt,ReasonTxt,LeaveDateTxt,LeaveTimeTxt,ArivalDateTxt,ArivalTimeTxt,LeaveTypeTxt;
    Button AcceptBtn,DeclineBtn;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_boader_leave);

        leaveReq = new LeaveReq();

        NameTxt = findViewById(R.id.boarderTxtMngA);
        RoomTxt = findViewById(R.id.roomNoTxtMngA);
        ReasonTxt = findViewById(R.id.reasonTxtMngA);
        LeaveDateTxt = findViewById(R.id.leaveDateTxtMngA);
        LeaveTimeTxt = findViewById(R.id.leaveTimeTxtMngA);
        ArivalDateTxt = findViewById(R.id.arivalDateTxtMngA);
        ArivalTimeTxt = findViewById(R.id.arivalTimeTxtMngA);
        AcceptBtn = findViewById(R.id.btn_accept);
        DeclineBtn = findViewById(R.id.btn_decline);
        LeaveTypeTxt = findViewById(R.id.tvLeaveType);

        Intent intent = getIntent();

        final String key = intent.getStringExtra(MngLeaveReqActivity.Mng_LEAVE_KEY);
        final String name = intent.getStringExtra(MngLeaveReqActivity.Mng_LEAVE_NAME);
        final String room = intent.getStringExtra(MngLeaveReqActivity.Mng_LEAVE_ROOM);
        final String reason = intent.getStringExtra(MngLeaveReqActivity.Mng_LEAVE_REASON);
        final String leaveDate = intent.getStringExtra(MngLeaveReqActivity.Mng_LEAVE_DATE);
        final String leaveTime = intent.getStringExtra(MngLeaveReqActivity.Mng_LEAVE_TIME);
        final String arivalDate = intent.getStringExtra(MngLeaveReqActivity.Mng_LEAVE_ARIVAL_DATE);
        final String arivaltime = intent.getStringExtra(MngLeaveReqActivity.Mng_LEAVE_ARIVAL_TIME);
        final String type=intent.getStringExtra(MngLeaveReqActivity.Mng_LEAVE_TYPE);

        NameTxt.setText(name);
        RoomTxt.setText(room);
        ReasonTxt.setText(reason);
        LeaveDateTxt.setText(leaveDate);
        LeaveTimeTxt.setText(leaveTime);
        ArivalDateTxt.setText(arivalDate);
        ArivalTimeTxt.setText(arivaltime);
        LeaveTypeTxt.setText(type);

        dbRef = FirebaseDatabase.getInstance().getReference("LeaveRequest").child(key);


        AcceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                leaveReq.setKey(key);
                leaveReq.setName(NameTxt.getText().toString());
                leaveReq.setRoomNo(RoomTxt.getText().toString());
                leaveReq.setReason(ReasonTxt.getText().toString());
                leaveReq.setLeaveDate(LeaveDateTxt.getText().toString());
                leaveReq.setLeaveTime(LeaveTimeTxt.getText().toString());
                leaveReq.setArivalDate(ArivalDateTxt.getText().toString());
                leaveReq.setArivalTime(ArivalTimeTxt.getText().toString());
                leaveReq.setLeaveType(LeaveTypeTxt.getText().toString());
                leaveReq.setStatus("Accept");


                dbRef.setValue(leaveReq);
                Toast.makeText(getApplicationContext(),"Request Accepted",Toast.LENGTH_SHORT).show();


            }
        });

        DeclineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                leaveReq.setKey(key);
                leaveReq.setName(NameTxt.getText().toString());
                leaveReq.setRoomNo(RoomTxt.getText().toString());
                leaveReq.setReason(ReasonTxt.getText().toString());
                leaveReq.setLeaveDate(LeaveDateTxt.getText().toString());
                leaveReq.setLeaveTime(LeaveTimeTxt.getText().toString());
                leaveReq.setArivalDate(ArivalDateTxt.getText().toString());
                leaveReq.setArivalTime(ArivalTimeTxt.getText().toString());
                leaveReq.setStatus("Decline");


                dbRef.setValue(leaveReq);
                Toast.makeText(getApplicationContext(),"Request Declined",Toast.LENGTH_SHORT).show();

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.itemBoxItem01A):
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                ActivityCompat.finishAffinity(this);
                startActivity(intent);
                return true;
            case(R.id.itemBoxItem02A):
                Intent intent2 = new Intent(getApplicationContext(),AdminChangePwdActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
