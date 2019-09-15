package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;

import Database.LeaveReq;

public class BoarderLeaveUpdate extends AppCompatActivity {

    LeaveReq leaveReq;
    EditText NameTxt,RoomTxt,ReasonTxt,LeaveDateTxt,LeaveTimeTxt,ArivalDateTxt,ArivalTimeTxt;
    Button UpdateBtn,DeleteBtn;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_leave_update);

        leaveReq = new LeaveReq();

        NameTxt = findViewById(R.id.boarderTxtUpdate);
        RoomTxt = findViewById(R.id.roomNoTxtUpdate);
        ReasonTxt = findViewById(R.id.reasonTxtUpdate);
        LeaveDateTxt = findViewById(R.id.leaveDateTxtUpdate);
        LeaveTimeTxt = findViewById(R.id.leaveTimeTxtUpdate);
        ArivalDateTxt = findViewById(R.id.arivalDateTxtUpdate);
        ArivalTimeTxt = findViewById(R.id.arivalTimeTxtUpdate);
        UpdateBtn = findViewById(R.id.addBtnUpdateLeave);
        DeleteBtn = findViewById(R.id.BtnDeleteLeaveZ);

        Intent intent = getIntent();

        final String key = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_KEY);
        final String name = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_NAME);
        final String room = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_ROOM);
        final String reason = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_REASON);
        final String leaveDate = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_DATE);
        final String leaveTime = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_TIME);
        final String arivalDate = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_ARIVAL_DATE);
        final String arivaltime = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_ARIVAL_TIME);


        NameTxt.setText(name);
        RoomTxt.setText(room);
        ReasonTxt.setText(reason);
        LeaveDateTxt.setText(leaveDate);
        LeaveTimeTxt.setText(leaveTime);
        ArivalDateTxt.setText(arivalDate);
        ArivalTimeTxt.setText(arivaltime);

        dbRef = FirebaseDatabase.getInstance().getReference("LeaveRequest").child(key);

        UpdateBtn.setOnClickListener(new View.OnClickListener() {
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

                dbRef.setValue(leaveReq);
                Toast.makeText(getApplicationContext(),"Details Updated",Toast.LENGTH_SHORT).show();
            }
        });

        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.removeValue();
            }
        });
    }
}
