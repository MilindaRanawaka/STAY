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
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.LeaveList;
import Database.LeaveReq;

public class MngLeaveReqActivity extends AppCompatActivity {

    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("LeaveRequest");
    ListView leaveList;
    List<LeaveReq> leaveArrList;

    public static final String Mng_LEAVE_KEY = "MngleaveKey";
    public static final String Mng_LEAVE_NAME = "MngleaveNAME";
    public static final String Mng_LEAVE_ROOM = "MngleaveROOM";
    public static final String Mng_LEAVE_REASON = "MngleaveREASON";
    public static final String Mng_LEAVE_DATE = "MngleaveDATE";
    public static final String Mng_LEAVE_TIME = "MngleaveTIME";
    public static final String Mng_LEAVE_ARIVAL_DATE = "MngleaveArivalDate";
    public static final String Mng_LEAVE_ARIVAL_TIME = "MngleaveArivalTime";
    public static final String Mng_LEAVE_TYPE = "MngleaveType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mng_leave_req);

        leaveList = findViewById(R.id.ManageViewLeaveListView);

        leaveArrList = new ArrayList<>();

        leaveList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                LeaveReq leaveReq = leaveArrList.get(i);
                Intent intent = new Intent(getApplicationContext(),ManageBoaderLeaveActivity.class);

                intent.putExtra(Mng_LEAVE_KEY,leaveReq.getKey());
                intent.putExtra(Mng_LEAVE_NAME,leaveReq.getName());
                intent.putExtra(Mng_LEAVE_ROOM,leaveReq.getRoomNo());
                intent.putExtra(Mng_LEAVE_REASON,leaveReq.getReason());
                intent.putExtra(Mng_LEAVE_DATE,leaveReq.getLeaveDate());
                intent.putExtra(Mng_LEAVE_TIME,leaveReq.getLeaveTime());
                intent.putExtra(Mng_LEAVE_ARIVAL_DATE,leaveReq.getArivalDate());
                intent.putExtra(Mng_LEAVE_ARIVAL_TIME,leaveReq.getArivalTime());
                intent.putExtra(Mng_LEAVE_TYPE,leaveReq.getLeaveType());

                startActivity(intent);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                leaveArrList.clear();
                for (DataSnapshot studentSnapShot : dataSnapshot.getChildren()){
                    LeaveReq student = studentSnapShot.getValue(LeaveReq.class);

                    leaveArrList.add(student);
                }
                LeaveList list = new LeaveList(MngLeaveReqActivity.this,leaveArrList);
                leaveList.setAdapter(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
