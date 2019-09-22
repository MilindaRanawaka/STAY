package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Database.Payments;

public class Delete_PaymentActivity extends AppCompatActivity {

    Payments paydelete;
    TextView payKeydel,payNamedel,payUIDdel;
    Button delPayButton;
    DatabaseReference dbrefDel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__payment);

        paydelete = new Payments();

        payKeydel = findViewById(R.id.textViewDPayKey);
        payUIDdel= findViewById(R.id.textViewDPayID);
        payNamedel= findViewById(R.id.textViewDPayName);
        delPayButton=findViewById(R.id.buttondelpay);

        Intent intentDel = getIntent();

        final String keyPd = intentDel.getStringExtra(RecievedPayments.DEL_PAY_KEY);
        final String uidPd = intentDel.getStringExtra(RecievedPayments.DEL_PAY_UID);
        final String namePd = intentDel.getStringExtra(RecievedPayments.DEL_PAY_NAME);

        payKeydel.setText(keyPd);
        payUIDdel.setText(uidPd);
        payNamedel.setText(namePd);

        dbrefDel = FirebaseDatabase.getInstance().getReference("Payments").child(keyPd);

        delPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbrefDel.removeValue();
            }
        });
    }
}
