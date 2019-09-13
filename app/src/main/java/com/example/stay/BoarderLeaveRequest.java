package com.example.stay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Database.LeaveReq;

public class BoarderLeaveRequest extends AppCompatActivity {

    EditText boarderName,roomNo,reason,leaveDate,leaveTime,arrivalDate,arivalTime;
    Button addBtn;
    DatabaseReference dbRef;
    LeaveReq leave;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_leave_request);

        boarderName = findViewById(R.id.boarderTxt);
        roomNo = findViewById(R.id.roomNoTxt);
        reason = findViewById(R.id.reasonTxt);
        leaveDate = findViewById(R.id.leaveDateTxt);
        leaveTime = findViewById(R.id.leaveTimeTxt);
        arrivalDate = findViewById(R.id.arivalDateTxt);
        arivalTime = findViewById(R.id.arivalTimeTxt);

        addBtn = findViewById(R.id.addBtnAddLeave);
        leave = new LeaveReq();


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference("LeaveRequest");
                leave.setKey(dbRef.push().getKey());
                leave.setName(boarderName.getText().toString());
                leave.setRoomNo(roomNo.getText().toString());
                leave.setReason(reason.getText().toString());
                leave.setLeaveDate(leaveDate.getText().toString());
                leave.setLeaveTime(leaveTime.getText().toString());
                leave.setArivalDate(arrivalDate.getText().toString());
                leave.setArivalTime(arivalTime.getText().toString());

                dbRef.child(leave.getKey()).setValue(leave);

                Toast.makeText(BoarderLeaveRequest.this,"Data Added Succesfully",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
