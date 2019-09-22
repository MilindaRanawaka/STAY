package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.LoginFilter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        name.setText(LoginData.userName);
        name.setEnabled(false);


        dbRef = FirebaseDatabase.getInstance().getReference("Boarders").child(LoginData.userKey);

        Query query = FirebaseDatabase.getInstance().getReference("Boarders").orderByChild("key").equalTo(LoginData.userKey);
        query.addListenerForSingleValueEvent(valueEventListener);

        updatePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwdStr = pwd.getText().toString();
                confirmPwdStr = confirmPwd.getText().toString();
                if(pwdStr.equals(confirmPwdStr)){
                    boarder.setPassword(EncryptDecrypt.encryptIt(pwdStr));
                    dbRef.setValue(boarder);
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
                    boarder = snapshot.getValue(Boarder.class);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.boarder_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.itemBoxItem01B):
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                ActivityCompat.finishAffinity(this);
                startActivity(intent);
                return true;
            case(R.id.itemBoxItem02B):
                Intent intent2 = new Intent(getApplicationContext(),BoarderUpdatePwdActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
