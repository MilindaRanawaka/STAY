package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import Database.Admin;
import Database.Boarder;
import Database.EncryptDecrypt;
import Database.LoginData;

public class AdminChangePwdActivity extends AppCompatActivity {

    EditText name,pwd,confirmPwd;
    Button updatePwd;
    DatabaseReference dbRef;
    Admin admin;
    String pwdStr;
    String confirmPwdStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_pwd);

        name=findViewById(R.id.adminNameUpdatePwd);
        pwd=findViewById(R.id.newPwdTxtAdmin);
        confirmPwd=findViewById(R.id.confirmPwdTxtAdmin);
        updatePwd=findViewById(R.id.pwdUpdateBtnAdmin);

        name.setText(LoginData.userName);
        name.setEnabled(false);


        dbRef = FirebaseDatabase.getInstance().getReference("Admin").child(LoginData.userKey);

        Query query = FirebaseDatabase.getInstance().getReference("Admin").orderByChild("key").equalTo(LoginData.userKey);
        query.addListenerForSingleValueEvent(valueEventListener);

        updatePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwdStr = pwd.getText().toString();
                confirmPwdStr = confirmPwd.getText().toString();
                if(pwdStr.equals(confirmPwdStr)){
                    admin.setPassword(EncryptDecrypt.encryptIt(pwdStr));
                    dbRef.setValue(admin);
                    Toast.makeText(getApplicationContext(),"Password Update",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Please Check Passwords",Toast.LENGTH_SHORT).show();
                    pwd.setText("");
                    confirmPwd.setText("");
                }
            }
        });

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    admin = snapshot.getValue(Admin.class);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
