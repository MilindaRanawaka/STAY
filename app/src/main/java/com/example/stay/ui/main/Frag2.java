package com.example.stay.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.stay.EditBoardersActivity;
import com.example.stay.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.Boarder;
import Database.BoarderList;
import Database.Room;
import Database.RoomList;

public class Frag2 extends Fragment {

    ListView boarderListView;
    List<Boarder> boarderArrList;
    DatabaseReference dbBoarder = FirebaseDatabase.getInstance().getReference("Boarders");

    public static final String BOARDER_NAME = "boarderName";
    public static final String BOARDER_DOB = "boarderDOB";
    public static final String BOARDER_ADDRESS = "boarderAddress";
    public static final String BOARDER_NIC = "boarderNIC";
    public static final String BOARDER_GENDER = "boarderGENDER";
    public static final String BOARDER_PHNO = "boarderPHNO";
    public static final String BOARDER_EMAIL = "boarderEMAIL";
    public static final String BOARDER_ROOMNO = "boarderROOMNO";
    public static final String BOARDER_KEY = "boarderKey";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.view_boarder_layout,container,false);

        boarderListView = view.findViewById(R.id.viewBarderListView);

        boarderArrList = new ArrayList<>();

        boarderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Boarder boarder = boarderArrList.get(i);
                Intent intent = new Intent(getContext(), EditBoardersActivity.class);

                intent.putExtra(BOARDER_NAME,boarder.getName());
                intent.putExtra(BOARDER_DOB,boarder.getDob());
                intent.putExtra(BOARDER_ADDRESS,boarder.getAddress());
                intent.putExtra(BOARDER_GENDER,boarder.getGender());
                intent.putExtra(BOARDER_NIC,boarder.getNic());
                intent.putExtra(BOARDER_PHNO,boarder.getPhNo());
                intent.putExtra(BOARDER_EMAIL,boarder.getEmail());
                intent.putExtra(BOARDER_ROOMNO,boarder.getRoomNo());
                intent.putExtra(BOARDER_KEY,boarder.getKey());


                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        dbBoarder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boarderArrList.clear();
                for (DataSnapshot studentSnapShot : dataSnapshot.getChildren()){
                    Boarder boarder = studentSnapShot.getValue(Boarder.class);

                    boarderArrList.add(boarder);
                }
                BoarderList list = new BoarderList(getActivity(),boarderArrList);
                boarderListView.setAdapter(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
