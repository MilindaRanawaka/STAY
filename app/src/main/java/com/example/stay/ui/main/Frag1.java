package com.example.stay.ui.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.stay.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import Database.Boarder;
import Database.EncryptDecrypt;

public class Frag1 extends Fragment {

    private static String cryptoPass = "sup3rS3xy";
    EditText name,dob,address,nic,phNO,email;
    Spinner roomNo;
    RadioButton acBtn;
    RadioGroup acGrp;
    Button addBtn;
    DatabaseReference dbRef;
    DatabaseReference dbRefRoom = FirebaseDatabase.getInstance().getReference("Room");
    Boarder boarder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_boarder_layout, container, false);

        name = view.findViewById(R.id.nameTxtAddB);
        dob = view.findViewById(R.id.enterDOBADDB);
        address = view.findViewById(R.id.addressTxtAddB);
        nic = view.findViewById(R.id.nicTxtAddB);
        phNO = view.findViewById(R.id.phNoTxtAddB);
        email = view.findViewById(R.id.emailTxtAddB);
        addBtn = view.findViewById(R.id.addMemBtnAddB);
        roomNo = view.findViewById(R.id.roomNoSpinnerAddB);
        acGrp = view.findViewById(R.id.radioGroupAddB);

        fillSpinner();

        boarder = new Boarder();

        acBtn = view.findViewById(acGrp.getCheckedRadioButtonId());
        acGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.maleRdioBtnAddB:
                        boarder.setGender("Male");
                        break;
                    case R.id.feMaleRdioBtnAddB:
                       boarder.setGender("Female");
                        break;
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference("Boarders");

                if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(getActivity(),"Please Enter Name",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(dob.getText().toString())){
                    Toast.makeText(getActivity(),"Please Enter Birth Date",Toast.LENGTH_SHORT).show();
                }
                else if(acGrp.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getActivity(),"Please Choose Gender",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(address.getText().toString())){
                    Toast.makeText(getActivity(),"Please Enter Address",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(nic.getText().toString())){
                    Toast.makeText(getActivity(),"Please Enter NIC",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(phNO.getText().toString())){
                    Toast.makeText(getActivity(),"Please Enter Phone No",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(getActivity(),"Please Enter Email",Toast.LENGTH_SHORT).show();
                }
                else {
                    boarder.setKey(dbRef.push().getKey());
                    boarder.setName(name.getText().toString());
                    boarder.setDob(dob.getText().toString());
                    boarder.setAddress(address.getText().toString());
                    boarder.setNic(nic.getText().toString());
                    boarder.setPhNo(Long.parseLong(phNO.getText().toString()));
                    boarder.setEmail(email.getText().toString());
                    boarder.setRoomNo(roomNo.getSelectedItem().toString());
                    boarder.setPassword(EncryptDecrypt.encryptIt(nic.getText().toString()));

                    Toast.makeText(getContext(),""+EncryptDecrypt.encryptIt(nic.getText().toString())+"",Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(),""+EncryptDecrypt.decryptIt(EncryptDecrypt.encryptIt(nic.getText().toString()))+"",Toast.LENGTH_LONG).show();

                    dbRef.child(boarder.getKey()).setValue(boarder);

                    Toast.makeText(getActivity(),"Data Added Successfully",Toast.LENGTH_SHORT).show();

                    Clear();
                }
            }
        });

        return view;
    }


    public void fillSpinner(){
        dbRefRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> room = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String roomID = areaSnapshot.child("roomID").getValue(String.class);
                    room.add(roomID);
                }
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, room);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                roomNo.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void Clear(){
        name.setText("");
        dob.setText("");
        address.setText("");
        nic.setText("");
        phNO.setText("");
        email.setText("");
        addBtn.setText("");
        acGrp.clearCheck();

    }
}
