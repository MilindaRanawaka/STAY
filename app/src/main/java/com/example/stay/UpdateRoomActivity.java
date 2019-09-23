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

import com.example.stay.ui.main.Frag4;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Database.Room;

public class UpdateRoomActivity extends AppCompatActivity {

    EditText roomNo,capacity,price;
    RadioButton acBtn,acBtn1;
    RadioButton acBtn2;
    RadioGroup acGrp;
    Button updateBtn,deleteBtn;
    DatabaseReference dbRef;
    Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_room);

        room = new Room();
        roomNo = findViewById(R.id.roomNoTxt1);
        capacity = findViewById(R.id.capacityTxt2);
        price = findViewById(R.id.priceTxt2);
        acGrp = findViewById(R.id.acRadioGrp);
        acBtn1 = findViewById(R.id.radioBtnAC2);
        acBtn2 = findViewById(R.id.radioBtnNoAC2);

        updateBtn = findViewById(R.id.roomUpdateBtn2);
        deleteBtn = findViewById(R.id.roomDeleteBtn);

        Intent intent = getIntent();

        final int capacityV = intent.getIntExtra(Frag4.ROOM_CAP,0);

        Bundle b = getIntent().getExtras();
        final double priceV = b.getDouble(Frag4.ROOM_PRICE);

        final String roomnoStr = intent.getStringExtra(Frag4.Room_ID);
        roomNo.setText(roomnoStr);
        capacity.setText(Integer.toString(capacityV));
        price.setText(Double.toString(priceV));

        final String key = intent.getStringExtra(Frag4.ROOM_KEY);

        final String acType = intent.getStringExtra(Frag4.ROOM_AC_TYPE);
        if(acType.equals("AC")) {
            acBtn1.toggle();
            room.setAcType("AC");
        }
        else if(acType.equals("NonAC")) {
            acBtn2.toggle();
            room.setAcType("NonAC");
        }

        dbRef =  FirebaseDatabase.getInstance().getReference("Room").child(key);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                room.setKey(key);
                room.setRoomID(roomNo.getText().toString());
                room.setCapacity(Integer.parseInt(capacity.getText().toString()));
                room.setPrice(Double.parseDouble(price.getText().toString()));

                int selectedId = acGrp.getCheckedRadioButtonId();
                acBtn = (RadioButton) findViewById(selectedId);

                room.setAcType(acBtn.getText().toString());

                dbRef.setValue(room);
                Toast.makeText(getApplicationContext(),"Room No: "+room.getRoomID()+" Updated",Toast.LENGTH_SHORT).show();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(),"Room Deleted",Toast.LENGTH_SHORT).show();
                clear();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.itemBoxItem01A):
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                ActivityCompat.finishAffinity(this);
                startActivity(intent);
                return true;
            case(R.id.itemBoxItem02A):
                Intent intent2 = new Intent(getApplicationContext(),AdminChangePwdActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void clear(){
        roomNo.setText("");
        capacity.setText("");
        price.setText("");
        acGrp.clearCheck();
    }

}
