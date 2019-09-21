package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.stay.ui.main.Frag2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.Boarder;

public class EditBoardersActivity extends AppCompatActivity {

    Boarder boarder;
    EditText nameTxt,dobTxt,addressTxt,nicTxt,phNoTxt,emailTxt;
    Spinner roomNoTxt;
    RadioGroup genRadioGrp;
    RadioButton femaleRadioBtm,maleRadioBtn,radioBtn;
    Button updateBtn,deleteBtn;
    DatabaseReference dbRef;
    DatabaseReference dbRefRoom = FirebaseDatabase.getInstance().getReference("Room");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_boarders);

        boarder = new Boarder();

        nameTxt = findViewById(R.id.nameTxtEditB);
        dobTxt = findViewById(R.id.enterDOBEditB);
        addressTxt = findViewById(R.id.addressTxtEditB);
        nicTxt = findViewById(R.id.nicTxtEditB);
        phNoTxt = findViewById(R.id.phNoTxtEditB);
        emailTxt = findViewById(R.id.emailTxtEditB);
        roomNoTxt = findViewById(R.id.roomNoTxtEditB);
        maleRadioBtn = findViewById(R.id.maleRdioBtnEditB);
        femaleRadioBtm = findViewById(R.id.feMaleRdioBtnEditB);
        genRadioGrp = findViewById(R.id.radioGroupEditB);
        updateBtn = findViewById(R.id.memUpBtnEditB);
        deleteBtn = findViewById(R.id.memDelBtnEditB);

        Intent intent = getIntent();

        final String key = intent.getStringExtra(Frag2.BOARDER_KEY);
        final String name = intent.getStringExtra(Frag2.BOARDER_NAME);
        final String dob = intent.getStringExtra(Frag2.BOARDER_DOB);
        final String address = intent.getStringExtra(Frag2.BOARDER_ADDRESS);
        final String email = intent.getStringExtra(Frag2.BOARDER_EMAIL);
        final String gender = intent.getStringExtra(Frag2.BOARDER_GENDER);
        final String nic = intent.getStringExtra(Frag2.BOARDER_NIC);
        final Long phno = intent.getLongExtra(Frag2.BOARDER_PHNO,0);
        final String roomNo = intent.getStringExtra(Frag2.BOARDER_ROOMNO);

        nameTxt.setText(name);
        dobTxt.setText(dob);
        addressTxt.setText(address);
        nicTxt.setText(nic);
        emailTxt.setText(email);
        phNoTxt.setText(Long.toString(phno));
        //roomNoTxt.setText(roomNo);

        if (gender.equals("Male")){
            maleRadioBtn.toggle();
            boarder.setGender("Male");
        }
        else if (gender.equals("Female")){
            femaleRadioBtm.toggle();
            boarder.setGender("Female");
        }

        this.fillSpinner();



        dbRef = FirebaseDatabase.getInstance().getReference("Boarders").child(key);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boarder.setKey(key);
                boarder.setName(nameTxt.getText().toString());
                boarder.setDob(dobTxt.getText().toString());
                boarder.setAddress(addressTxt.getText().toString());
                boarder.setEmail(emailTxt.getText().toString());
                boarder.setNic(nicTxt.getText().toString());
                boarder.setPhNo(Long.parseLong(phNoTxt.getText().toString()));
                //boarder.setRoomNo(roomNoTxt.getText().toString());

                int selectedId = genRadioGrp.getCheckedRadioButtonId();
                radioBtn = (RadioButton) findViewById(selectedId);
                boarder.setGender(radioBtn.getText().toString());

                dbRef.setValue(boarder);
                Toast.makeText(getApplicationContext(),"Admin Name: "+boarder.getName()+"'s Details Updated",Toast.LENGTH_SHORT).show();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(),"Admin Details Deleted",Toast.LENGTH_SHORT).show();
            }
        });
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
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, room);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                roomNoTxt.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
