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

import com.example.stay.EditAdminActivity;
import com.example.stay.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.Admin;
import Database.AdminList;
import Database.Boarder;
import Database.BoarderList;
import Database.EncryptDecrypt;

public class Frag6 extends Fragment {

    ListView adminListView;
    List<Admin> adminArrList;
    DatabaseReference dbAdmin = FirebaseDatabase.getInstance().getReference("Admin");

    public static final String ADMIN_NAME = "adminName";
    public static final String ADMIN_DOB = "adminDOB";
    public static final String ADMIN_ADDRESS = "adminAddress";
    public static final String ADMIN_NIC = "adminNIC";
    public static final String ADMIN_GENDER = "adminGENDER";
    public static final String ADMIN_PHNO = "adminPHNO";
    public static final String ADMIN_EMAIL = "adminEMAIL";
    public static final String ADMIN_KEY = "adminKey";
    public static final String ADMIN_PASSWORD = "adminPassword";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_admin_layout,container,false);

        adminListView = view.findViewById(R.id.viewAdminListView);
        adminArrList = new ArrayList<>();

        adminListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Admin boarder = adminArrList.get(i);
                Intent intent = new Intent(getContext(), EditAdminActivity.class);

                intent.putExtra(ADMIN_NAME,boarder.getName());
                intent.putExtra(ADMIN_DOB,boarder.getDob());
                intent.putExtra(ADMIN_ADDRESS,boarder.getAddress());
                intent.putExtra(ADMIN_GENDER,boarder.getGender());
                intent.putExtra(ADMIN_NIC,boarder.getNic());
                intent.putExtra(ADMIN_PHNO,boarder.getPhNo());
                intent.putExtra(ADMIN_EMAIL,boarder.getEmail());
                intent.putExtra(ADMIN_KEY,boarder.getKey());
                intent.putExtra(ADMIN_PASSWORD, EncryptDecrypt.decryptIt(boarder.getPassword()));


                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        dbAdmin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminArrList.clear();
                for (DataSnapshot studentSnapShot : dataSnapshot.getChildren()){
                    Admin admin = studentSnapShot.getValue(Admin.class);

                    adminArrList.add(admin);
                }
                AdminList list = new AdminList(getActivity(),adminArrList);
                adminListView.setAdapter(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
