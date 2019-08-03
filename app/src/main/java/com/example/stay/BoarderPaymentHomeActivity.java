package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BoarderPaymentHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_payment_home);
    }

    public void openAddPayment(View view) {
        Intent intent1= new Intent(this,AddPayment.class);
        startActivity(intent1);
    }

    public void openViewPayment(View view) {
        Intent intent4=new Intent(this,ViewPayment.class);
        startActivity(intent4);
    }
}
