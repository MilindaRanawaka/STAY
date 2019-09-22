package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class BoarderManageActivity extends AppCompatActivity {

    Boarder boarder;
    EditText nameTxt,dobTxt,addressTxt,nicTxt,phNoTxt,emailTxt;
    RadioGroup genRadioGrp;
    RadioButton femaleRadioBtm,maleRadioBtn,radioBtn;
    Button updateBtn,deleteBtn;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_manage);

        boarder = new Boarder();

        nameTxt = findViewById(R.id.boarderNameBB);
        dobTxt = findViewById(R.id.boarderDOBBB);
        addressTxt = findViewById(R.id.boarderAddressBB);
        nicTxt = findViewById(R.id.boarderNicBB);
        phNoTxt = findViewById(R.id.boarderPhNoBB);
        emailTxt = findViewById(R.id.boarderEmailBB);
        maleRadioBtn = findViewById(R.id.maleBtnBB);
        femaleRadioBtm = findViewById(R.id.femaleBtnBB);
        genRadioGrp = findViewById(R.id.radioGroupBB);

        updateBtn = findViewById(R.id.UpdateBtnBB);

        dbRef = FirebaseDatabase.getInstance().getReference("Boarders").child(LoginData.userKey);

        Query query = FirebaseDatabase.getInstance().getReference("Boarders").orderByChild("key").equalTo(LoginData.userKey);
        query.addListenerForSingleValueEvent(valueEventListener);

        nameTxt.setText(boarder.getName());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boarder.setKey(LoginData.userKey);
                boarder.setName(nameTxt.getText().toString());
                boarder.setDob(dobTxt.getText().toString());
                boarder.setAddress(addressTxt.getText().toString());
                boarder.setEmail(emailTxt.getText().toString());
                boarder.setNic(nicTxt.getText().toString());
                boarder.setPhNo(Long.parseLong(phNoTxt.getText().toString()));

                int selectedId = genRadioGrp.getCheckedRadioButtonId();
                radioBtn = (RadioButton) findViewById(selectedId);
                boarder.setGender(radioBtn.getText().toString());

                boarder.setPassword(EncryptDecrypt.encryptIt(EncryptDecrypt.decryptIt(boarder.getPassword())));

                dbRef.setValue(boarder);
                Toast.makeText(getApplicationContext(),"Boarder Name: "+boarder.getName()+"'s Details Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    boarder = snapshot.getValue(Boarder.class);
                    nameTxt.setText(boarder.getName());
                    dobTxt.setText(boarder.getDob());
                    addressTxt.setText(boarder.getAddress());
                    nicTxt.setText(boarder.getNic());
                    phNoTxt.setText(boarder.getPhNo().toString());
                    emailTxt.setText(boarder.getEmail());


                    if (boarder.getGender().equals("Male")){
                        maleRadioBtn.toggle();
                        boarder.setGender("Male");
                    }
                    else if (boarder.getGender().equals("Female")){
                        femaleRadioBtm.toggle();
                        boarder.setGender("Female");
                    }
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
