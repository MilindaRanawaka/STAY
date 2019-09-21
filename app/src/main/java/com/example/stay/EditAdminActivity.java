package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.stay.ui.main.Frag2;
import com.example.stay.ui.main.Frag6;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Database.Admin;
import Database.Boarder;

public class EditAdminActivity extends AppCompatActivity {

    Admin admin;
    EditText nameTxt,dobTxt,addressTxt,nicTxt,phNoTxt,emailTxt;
    RadioGroup genRadioGrp;
    RadioButton femaleRadioBtm,maleRadioBtn,radioBtn;
    Button updateBtn,deleteBtn;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin);

        admin = new Admin();

        nameTxt = findViewById(R.id.nameTxtEditA);
        dobTxt = findViewById(R.id.enterDOBEditA);
        addressTxt = findViewById(R.id.addressTxtEditA);
        nicTxt = findViewById(R.id.nicTxtEditA);
        phNoTxt = findViewById(R.id.phNoTxtEditA);
        emailTxt = findViewById(R.id.emailTxtEditA);
        maleRadioBtn = findViewById(R.id.maleRdioBtnEditA);
        femaleRadioBtm = findViewById(R.id.feMaleRdioBtnEditA);
        genRadioGrp = findViewById(R.id.radioGroupEditA);
        updateBtn = findViewById(R.id.memUpBtnEditA);
        deleteBtn = findViewById(R.id.memDelBtnEditA);

        Intent intent = getIntent();

        final String key = intent.getStringExtra(Frag6.ADMIN_KEY);
        final String name = intent.getStringExtra(Frag6.ADMIN_NAME);
        final String dob = intent.getStringExtra(Frag6.ADMIN_DOB);
        final String address = intent.getStringExtra(Frag6.ADMIN_ADDRESS);
        final String email = intent.getStringExtra(Frag6.ADMIN_EMAIL);
        final String gender = intent.getStringExtra(Frag6.ADMIN_GENDER);
        final String nic = intent.getStringExtra(Frag6.ADMIN_NIC);
        final Long phno = intent.getLongExtra(Frag6.ADMIN_PHNO,0);

        nameTxt.setText(name);
        dobTxt.setText(dob);
        addressTxt.setText(address);
        nicTxt.setText(nic);
        emailTxt.setText(email);
        phNoTxt.setText(Long.toString(phno));


        if (gender.equals("Male")){
            maleRadioBtn.toggle();
            admin.setGender("Male");
        }
        else if (gender.equals("Female")){
            femaleRadioBtm.toggle();
            admin.setGender("Female");
        }


        dbRef = FirebaseDatabase.getInstance().getReference("Admin").child(key);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admin.setKey(key);
                admin.setName(nameTxt.getText().toString());
                admin.setDob(dobTxt.getText().toString());
                admin.setAddress(addressTxt.getText().toString());
                admin.setEmail(emailTxt.getText().toString());
                admin.setNic(nicTxt.getText().toString());
                admin.setPhNo(Long.parseLong(phNoTxt.getText().toString()));

                dbRef.setValue(admin);
                Toast.makeText(getApplicationContext(),"Admin Name : "+admin.getName()+"'s Details Updated",Toast.LENGTH_SHORT).show();
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
}
