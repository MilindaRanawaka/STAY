package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CompletePayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_payment);
    }

    public void onUpdateDetails(View view) {
        Intent intentUpdateDetils = new Intent(this,PaymentUpdate.class);
        startActivity(intentUpdateDetils);
    }
}
