package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UpdateSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_success);
    }

    public void onBackPayment(View view) {
        Intent backPay = new Intent(this,BoarderPaymentHomeActivity.class);
        startActivity(backPay);
    }
}
