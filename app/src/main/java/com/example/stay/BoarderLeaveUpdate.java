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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;

import Database.LeaveReq;
import Database.LoginData;

public class BoarderLeaveUpdate extends AppCompatActivity {

    LeaveReq leaveReq;
    EditText NameTxt,RoomTxt,ReasonTxt,LeaveDateTxt,LeaveTimeTxt,ArivalDateTxt,ArivalTimeTxt;
    Button UpdateBtn,DeleteBtn;
    Spinner SpinnerType;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_leave_update);

        leaveReq = new LeaveReq();

        NameTxt = findViewById(R.id.boarderTxtMng);
        RoomTxt = findViewById(R.id.roomNoTxtMng);
        ReasonTxt = findViewById(R.id.reasonTxtMng);
        LeaveDateTxt = findViewById(R.id.leaveDateTxtMng);
        LeaveTimeTxt = findViewById(R.id.leaveTimeTxtMng);
        ArivalDateTxt = findViewById(R.id.arivalDateTxtMng);
        ArivalTimeTxt = findViewById(R.id.arivalTimeTxtMng);
        UpdateBtn = findViewById(R.id.addBtnUpdateLeave);
        DeleteBtn = findViewById(R.id.BtnDeleteLeaveZ);
        SpinnerType = findViewById(R.id.TypesSpinner);

        Intent intent = getIntent();

        final String key = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_KEY);
        final String name = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_NAME);
        final String room = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_ROOM);
        final String reason = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_REASON);
        final String leaveDate = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_DATE);
        final String leaveTime = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_TIME);
        final String arivalDate = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_ARIVAL_DATE);
        final String arivaltime = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_ARIVAL_TIME);
        final String type = intent.getStringExtra(BoarderViewLeaveActivity.LEAVE_TYPE);


        ArrayAdapter myAdap = (ArrayAdapter)SpinnerType.getAdapter();
        final int spinnerPosition = myAdap.getPosition(type);
        SpinnerType.setSelection(spinnerPosition);

        NameTxt.setText(name);
        RoomTxt.setText(room);
        ReasonTxt.setText(reason);
        LeaveDateTxt.setText(leaveDate);
        LeaveTimeTxt.setText(leaveTime);
        ArivalDateTxt.setText(arivalDate);
        ArivalTimeTxt.setText(arivaltime);

        SpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        ArivalDateTxt.setEnabled(false);
                        ArivalTimeTxt.setEnabled(false);
                        LeaveDateTxt.setEnabled(true);
                        LeaveTimeTxt.setEnabled(true);
                        break;
                    case 1:
                        ArivalDateTxt.setEnabled(true);
                        ArivalTimeTxt.setEnabled(true);
                        LeaveDateTxt.setEnabled(false);
                        LeaveTimeTxt.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference("LeaveRequest").child(key);

        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                leaveReq.setKey(key);
                leaveReq.setUserKey(LoginData.userKey);
                leaveReq.setName(NameTxt.getText().toString());
                leaveReq.setRoomNo(RoomTxt.getText().toString());
                leaveReq.setReason(ReasonTxt.getText().toString());
                leaveReq.setLeaveDate(LeaveDateTxt.getText().toString());
                leaveReq.setLeaveTime(LeaveTimeTxt.getText().toString());
                leaveReq.setArivalDate(ArivalDateTxt.getText().toString());
                leaveReq.setArivalTime(ArivalTimeTxt.getText().toString());
                leaveReq.setLeaveType(SpinnerType.getSelectedItem().toString());


                dbRef.setValue(leaveReq);
                Toast.makeText(getApplicationContext(),"Details Updated",Toast.LENGTH_SHORT).show();
            }
        });

        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(),"Delete Succesfully",Toast.LENGTH_SHORT).show();
            }
        });
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
