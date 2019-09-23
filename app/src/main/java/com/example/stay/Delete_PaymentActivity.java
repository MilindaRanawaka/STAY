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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Database.Payments;

public class Delete_PaymentActivity extends AppCompatActivity {

    Payments paydelete;
    TextView payKeydel,payNamedel,payUIDdel,payDate,payAmount,payBank,payStatus;
    Button delPayButton,updateStatButton;
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
        updateStatButton= findViewById(R.id.buttonupdatestat);
        payDate= findViewById(R.id.textViewDPayDate);
        payAmount=findViewById(R.id.textViewDPayAmt);
        payBank=findViewById(R.id.textViewDPayBnkk);
        payStatus=findViewById(R.id.textViewDPayStat);


        Intent intentDel = getIntent();

        final String keyPd = intentDel.getStringExtra(RecievedPayments.DEL_PAY_KEY);
        final String uidPd = intentDel.getStringExtra(RecievedPayments.DEL_PAY_UID);
        final String namePd = intentDel.getStringExtra(RecievedPayments.DEL_PAY_NAME);
        final int phonePd=intentDel.getIntExtra(RecievedPayments.DEL_PAY_PHN,0);
        final String roomtyPd = intentDel.getStringExtra(RecievedPayments.DEL_PAY_RT);
        final String datePd = intentDel.getStringExtra(RecievedPayments.DEL_PAY_DATE);
        Bundle b =getIntent().getExtras();
        final Double amountPd = b.getDouble(RecievedPayments.DEL_PAY_AMT);
        final String bankPd = intentDel.getStringExtra(RecievedPayments.DEL_PAY_BNK);
        final String statPd = intentDel.getStringExtra(RecievedPayments.DEL_PAY_STATUS);

        payKeydel.setText(keyPd);
        payUIDdel.setText(uidPd);
        payNamedel.setText(namePd);
        payDate.setText(datePd);
        payAmount.setText(Double.toString(amountPd));
        payBank.setText(bankPd);
        payStatus.setText(statPd);

        dbrefDel = FirebaseDatabase.getInstance().getReference("Payments").child(keyPd);

        delPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(paydelete.getStatus()=="Accepted"){
                    Toast.makeText(getApplicationContext(),"Can't delete Accepted Payment",Toast.LENGTH_SHORT).show();
                }
                else {
                    dbrefDel.removeValue();
                    Toast.makeText(getApplicationContext(), "Payment Record Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateStatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                paydelete.setKey(keyPd);
                paydelete.setUserID(uidPd);
                paydelete.setName(namePd);
                paydelete.setPhoneNo(phonePd);
                paydelete.setRoomType(roomtyPd);
                paydelete.setPayAmount(amountPd);
                paydelete.setBank(bankPd);
                paydelete.setPayDate(datePd);
                paydelete.setStatus("Accepted");

                payStatus.setText("Accepted");

                dbrefDel.setValue(paydelete);
                Toast.makeText(getApplicationContext(),"Payment Status Updated",Toast.LENGTH_SHORT).show();

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

}
