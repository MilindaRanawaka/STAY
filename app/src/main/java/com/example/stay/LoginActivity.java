package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import Database.Admin;
import Database.Boarder;
import Database.EncryptDecrypt;
import Database.LoginData;

public class LoginActivity extends AppCompatActivity {

    EditText tv1,tv2;
    String str1,str2;
    int num1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.unText);

        str1 = tv1.getText().toString();
    }

    public void onLogin(View view){


        tv1 = findViewById(R.id.unText);

        str1 = tv1.getText().toString();

        tv2 = findViewById(R.id.pwdText);

        str2 = tv2.getText().toString();


        Query query = FirebaseDatabase.getInstance().getReference("Admin").orderByChild("email").equalTo(str1);

        query.addListenerForSingleValueEvent(valueEventListener);

        Query query2 = FirebaseDatabase.getInstance().getReference("Boarders").orderByChild("email").equalTo(str1);
        query2.addListenerForSingleValueEvent(valueEventListener2);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Admin admin = snapshot.getValue(Admin.class);
                    findValidAdmin(admin);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    ValueEventListener valueEventListener2 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Boarder boarder = snapshot.getValue(Boarder.class);
                    findValidBoarder(boarder);
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"Check Login Credentials",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public void findValidAdmin(Admin admin){

        tv2 = findViewById(R.id.pwdText);

        str2 = tv2.getText().toString();

        if(str2.equals(EncryptDecrypt.decryptIt(admin.getPassword()))){
            LoginData.userKey = admin.getKey();
            LoginData.userName = admin.getName();
            LoginData.userEmail = admin.getEmail();
            LoginData.userPhNo = admin.getPhNo();
            LoginData.userNic = admin.getNic();
            Intent intent = new Intent(this,ManagementHomeActivity.class);
            startActivity(intent);
        }
    }

    public void findValidBoarder(Boarder boarder){
        tv2 = findViewById(R.id.pwdText);

        str2 = tv2.getText().toString();

        if(str2.equals(EncryptDecrypt.decryptIt(boarder.getPassword()))){
            LoginData.userKey = boarder.getKey();
            LoginData.userName = boarder.getName();
            LoginData.userRoomNo = boarder.getRoomNo();
            LoginData.userEmail = boarder.getEmail();
            LoginData.userPhNo = boarder.getPhNo();
            LoginData.userNic = boarder.getNic();
            Intent intent2 = new Intent(this, BoardersHomeActivity.class);
            startActivity(intent2);
        }
        else{
            Toast.makeText(getApplicationContext(),"Check Login Credentials",Toast.LENGTH_SHORT).show();
        }
    }
}