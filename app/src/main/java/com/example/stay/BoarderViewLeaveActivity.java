package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import Database.Room;
import Database.RoomList;

public class BoarderViewLeaveActivity extends AppCompatActivity {
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("LeaveRequest");
    ListView leaveList;
    List<LeaveReq> leaveArrList;

    public static final String LEAVE_KEY = "leaveKey";
    public static final String LEAVE_NAME = "leaveNAME";
    public static final String LEAVE_ROOM = "leaveROOM";
    public static final String LEAVE_REASON = "leaveREASON";
    public static final String LEAVE_DATE = "leaveDATE";
    public static final String LEAVE_TIME = "leaveTIME";
    public static final String LEAVE_ARIVAL_DATE = "leaveArivalDate";
    public static final String LEAVE_ARIVAL_TIME = "leaveArivalTime";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_view_leave);

        leaveList = findViewById(R.id.viewLeaveListView);

        leaveArrList = new ArrayList<>();

        leaveList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                LeaveReq leaveReq = leaveArrList.get(i);
                Intent intent = new Intent(getApplicationContext(),BoarderLeaveUpdate.class);

                intent.putExtra(LEAVE_KEY,leaveReq.getKey());
                intent.putExtra(LEAVE_NAME,leaveReq.getName());
                intent.putExtra(LEAVE_ROOM,leaveReq.getRoomNo());
                intent.putExtra(LEAVE_REASON,leaveReq.getReason());
                intent.putExtra(LEAVE_DATE,leaveReq.getLeaveDate());
                intent.putExtra(LEAVE_TIME,leaveReq.getLeaveTime());
                intent.putExtra(LEAVE_ARIVAL_DATE,leaveReq.getArivalDate());
                intent.putExtra(LEAVE_ARIVAL_TIME,leaveReq.getArivalTime());

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
                LeaveList list = new LeaveList(BoarderViewLeaveActivity.this,leaveArrList);
                leaveList.setAdapter(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
