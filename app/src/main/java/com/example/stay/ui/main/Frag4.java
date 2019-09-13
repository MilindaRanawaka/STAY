package com.example.stay.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.stay.R;
import com.example.stay.UpdateRoomActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.Room;
import Database.RoomList;

public class Frag4 extends Fragment {

    public static final String ROOM_KEY = "roomKey";
    public static final String Room_ID = "roomId";
    public static final String ROOM_CAP = "roomCapacity";
    public static final String ROOM_PRICE = "roomPrice";
    public static final String ROOM_AC_TYPE = "roomAcType";

    DatabaseReference dbStu = FirebaseDatabase.getInstance().getReference("Room");
    ListView roomList;
    List<Room> roomArrList;
    CardView cardView;
    Button updateBtn,deleteBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.view_room_layout,container,false);

        roomList = v.findViewById(R.id.viewRoom);

        roomArrList = new ArrayList<>();

        cardView = (CardView) roomList.findViewById(R.id.cardView5);

        roomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Room room = roomArrList.get(i);
                Intent intent = new Intent(getContext(), UpdateRoomActivity.class);

                intent.putExtra(ROOM_KEY,room.getKey());
                intent.putExtra(Room_ID,room.getRoomID());
                intent.putExtra(ROOM_CAP,room.getCapacity());

                Bundle b = new Bundle();
                b.putDouble(ROOM_PRICE, room.getPrice());
                intent.putExtras(b);

                intent.putExtra(ROOM_AC_TYPE,room.getAcType());

                startActivity(intent);


            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        dbStu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                roomArrList.clear();
                for (DataSnapshot studentSnapShot : dataSnapshot.getChildren()){
                    Room student = studentSnapShot.getValue(Room.class);

                    roomArrList.add(student);
                }
                RoomList list = new RoomList(getActivity(),roomArrList);
                roomList.setAdapter(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
