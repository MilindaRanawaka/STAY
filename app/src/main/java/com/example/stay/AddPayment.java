package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddPayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
    }

    public void openCompletePayment(View view) {
        Intent intent2= new Intent(this,CompletePayment.class);
        startActivity(intent2);
    }
}
//mm
//dulashni-9.11pm