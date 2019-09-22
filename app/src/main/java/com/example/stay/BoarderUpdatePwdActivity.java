package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.LoginFilter;
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

import Database.Boarder;
import Database.EncryptDecrypt;
import Database.LoginData;

public class BoarderUpdatePwdActivity extends AppCompatActivity {

    EditText name,pwd,confirmPwd;
    Button updatePwd;
    DatabaseReference dbRef;
    Boarder boarder;
    String pwdStr;
    String confirmPwdStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_update_pwd);

        name=findViewById(R.id.boarderNameUpdatePwd);
        pwd=findViewById(R.id.newPwdTxt);
        confirmPwd=findViewById(R.id.confirmPwdTxt);
        updatePwd=findViewById(R.id.pwdUpdateBtn);

        pwdStr = pwd.getText().toString();
        confirmPwdStr = confirmPwd.getText().toString();
        name.setText(LoginData.userName);
        name.setEnabled(false);


        dbRef = FirebaseDatabase.getInstance().getReference("Boarder").child(LoginData.userKey);

        updatePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pwdStr.equals(confirmPwdStr)){
                    Toast.makeText(getApplicationContext(),""+confirmPwdStr+"",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),""+EncryptDecrypt.encryptIt(confirmPwdStr)+"",Toast.LENGTH_SHORT).show();
                    //boarder.setPassword(EncryptDecrypt.encryptIt(confirmPwdStr));
                    //dbRef.setValue(boarder);
                }else {
                    Toast.makeText(getApplicationContext(),"Please Check Passwords",Toast.LENGTH_SHORT).show();
                    pwd.setText("");
                    confirmPwd.setText("");
                }
            }
        });

        Query query = FirebaseDatabase.getInstance().getReference("Boarder").orderByChild("key").equalTo(LoginData.userKey);
        query.addListenerForSingleValueEvent(valueEventListener);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    boarder = snapshot.getValue(Boarder.class);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
