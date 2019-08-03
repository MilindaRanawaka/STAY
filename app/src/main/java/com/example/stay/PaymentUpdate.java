package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PaymentUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_update);
    }

    public void onUpdateBtn(View view) {
        Intent updateBtnIntent = new Intent(this,UpdateSuccess.class);
        startActivity(updateBtnIntent);
    }
}
