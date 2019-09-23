package com.example.stay;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Database.LeaveReq;
import Database.LoginData;

public class BoarderLeaveRequest extends AppCompatActivity {

    EditText boarderName,roomNo,reason,leaveDate,leaveTime,arrivalDate,arivalTime;
    Button addBtn;
    Spinner spinnerTypes;
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
        spinnerTypes = findViewById(R.id.spinnerTypes);
        leave = new LeaveReq();

        boarderName.setText(LoginData.userName);
        roomNo.setText(LoginData.userRoomNo);

        boarderName.setEnabled(false);
        roomNo.setEnabled(false);


        spinnerTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        arrivalDate.setEnabled(false);
                        arivalTime.setEnabled(false);
                        leaveDate.setEnabled(true);
                        leaveTime.setEnabled(true);
                        break;
                    case 1:
                        arrivalDate.setEnabled(true);
                        arivalTime.setEnabled(true);
                        leaveDate.setEnabled(false);
                        leaveTime.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference("LeaveRequest");

                if(TextUtils.isEmpty(boarderName.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter Boarder Name",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(roomNo.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter Room No",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(reason.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter Reason",Toast.LENGTH_SHORT).show();
                }


                else {
                    leave.setKey(dbRef.push().getKey());
                    leave.setName(boarderName.getText().toString());
                    leave.setRoomNo(roomNo.getText().toString());
                    leave.setReason(reason.getText().toString());
                    leave.setLeaveDate(leaveDate.getText().toString());
                    leave.setLeaveTime(leaveTime.getText().toString());
                    leave.setArivalDate(arrivalDate.getText().toString());
                    leave.setArivalTime(arivalTime.getText().toString());
                    leave.setLeaveType(spinnerTypes.getSelectedItem().toString());
                    leave.setStatus("Pending");

                    leave.setUserKey(LoginData.userKey);

                    dbRef.child(leave.getKey()).setValue(leave);

                    Toast.makeText(BoarderLeaveRequest.this, "Data Added Succesfully", Toast.LENGTH_SHORT).show();

                    Clear();
                }

            }
        });



    }

    public void Clear(){

        boarderName.setText("");
        roomNo.setText("");
        reason.setText("");
        leaveDate.setText("");
        leaveTime.setText("");
        arrivalDate.setText("");
        arivalTime.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.boarder_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.itemBoxItem01B):
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                ActivityCompat.finishAffinity(this);
                startActivity(intent);
                return true;
            case(R.id.itemBoxItem02B):
                Intent intent2 = new Intent(getApplicationContext(),BoarderUpdatePwdActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
