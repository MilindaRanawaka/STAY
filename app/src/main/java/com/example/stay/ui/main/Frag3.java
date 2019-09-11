package com.example.stay.ui.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.stay.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import Database.Room;

public class Frag3 extends Fragment {

    EditText roomNo,capacity,price;
    RadioButton acBtn;
    RadioGroup acGrp;
    Button addBtn;
    DatabaseReference dbRef;
    Room room;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_room_layout,container,false);

        roomNo = v.findViewById(R.id.roomNoTxt);
        capacity = v.findViewById(R.id.capacityTxt);
        price = v.findViewById(R.id.priceTxt);
        acGrp = v.findViewById(R.id.acRadioGrp);

        addBtn = v.findViewById(R.id.addBtn);

        int selectedId = acGrp.getCheckedRadioButtonId();
        acBtn = v.findViewById(selectedId);

        room = new Room();

        acBtn = v.findViewById(acGrp.getCheckedRadioButtonId());
        acGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioBtnAC:
                        room.setAcType("AC");
                        break;
                    case R.id.radioBtnNoAC:
                        room.setAcType("NonAC");
                        break;
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference("Room");

                if(TextUtils.isEmpty(roomNo.getText().toString())){
                    Toast.makeText(getActivity(),"Please Enter Room No",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(capacity.getText().toString())){
                    Toast.makeText(getActivity(),"Please Enter Capacity",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(price.getText().toString())){
                    Toast.makeText(getActivity(),"Please Enter Address",Toast.LENGTH_SHORT).show();
                }
                else if(acGrp.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getActivity(),"Please Choose AC Type",Toast.LENGTH_SHORT).show();
                }
                else {
                    room.setKey(dbRef.push().getKey());
                    room.setRoomID(roomNo.getText().toString());
                    room.setCapacity(Integer.parseInt(capacity.getText().toString()));
                    room.setPrice(Double.parseDouble(price.getText().toString()));
                    //room.setAcType(acBtn.getText().toString());


                    dbRef.child(room.getKey()).setValue(room);

                    Toast.makeText(getActivity(),"Data Added Succesfully",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }
}
