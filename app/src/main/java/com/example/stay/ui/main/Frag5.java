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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.stay.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Database.Admin;
import Database.Boarder;
import Database.EncryptDecrypt;

public class Frag5 extends Fragment {

    EditText name,dob,address,nic,phNO,email;
    RadioButton acBtn;
    RadioGroup acGrp;
    Button addBtn;
    DatabaseReference dbRef;
    Admin admin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_admin_layout,container,false);

        name = view.findViewById(R.id.nameTxtAddA);
        dob = view.findViewById(R.id.enterDOBADDA);
        address = view.findViewById(R.id.addressTxtAddA);
        nic = view.findViewById(R.id.nicTxtAddA);
        phNO = view.findViewById(R.id.phNoTxtAddA);
        email = view.findViewById(R.id.emailTxtAddA);
        addBtn = view.findViewById(R.id.addMemBtnAddA);
        acGrp = view.findViewById(R.id.radioGroupAddA);

        admin = new Admin();

        acBtn = view.findViewById(acGrp.getCheckedRadioButtonId());
        acGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.maleRdioBtnAddA:
                        admin.setGender("Male");
                        break;
                    case R.id.feMaleRdioBtnAddA:
                        admin.setGender("Female");
                        break;
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference("Admin");

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
                    admin.setKey(dbRef.push().getKey());
                    admin.setName(name.getText().toString());
                    admin.setDob(dob.getText().toString());
                    admin.setAddress(address.getText().toString());
                    admin.setNic(nic.getText().toString());
                    admin.setPhNo(Long.parseLong(phNO.getText().toString()));
                    admin.setEmail(email.getText().toString());
                    admin.setPassword(EncryptDecrypt.encryptIt(nic.getText().toString()));

                    dbRef.child(admin.getKey()).setValue(admin);

                    Toast.makeText(getActivity(),"Data Added Successfully",Toast.LENGTH_SHORT).show();

                    Clear();
                }
            }
        });

        return view;
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
