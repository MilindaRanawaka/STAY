package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.stay.ui.main.Frag4;

public class ManagementHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_home);
    }

    public void onBorder(View view){
        Intent intent = new Intent(this,MngBorderActivity.class);
        startActivity(intent);
    }

    public void onRoom(View view){
        Intent intent = new Intent(this,MngRoomActivity.class);
        startActivity(intent);
    }

    public void onPayment(View view){
        Intent intent = new Intent(this,RecievedPayments.class);
        startActivity(intent);
    }
    public void onAddRoom(View view){
        Intent addIntent = new Intent(this, Frag4.class);
        startActivity(addIntent);
    }
}
