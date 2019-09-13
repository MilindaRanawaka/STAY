package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    RadioButton acBtn1;
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

                acGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i){
                            case R.id.radioBtnAC2:
                                room.setAcType("AC");
                                break;
                            case R.id.radioBtnNoAC2:
                                room.setAcType("NonAC");
                                break;
                        }
                    }
                });

                dbRef.setValue(room);
                Toast.makeText(getApplicationContext(),"Room No: "+room.getRoomID()+" Updated",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),""+room.getRoomID()+"",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),""+room.getCapacity()+"",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),""+room.getPrice()+"",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),""+room.getKey()+"",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),""+room.getAcType()+"",Toast.LENGTH_SHORT).show();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(),"Room No: "+room.getRoomID()+" Deleted",Toast.LENGTH_SHORT).show();
            }
        });

    }
}