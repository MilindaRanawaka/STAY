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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Database.Payments;

public class PaymentUpdate extends AppCompatActivity {
    Payments payments;
    EditText UIDupdate ,PayNameUpdate,PayPhoneUpdate,PayAmountUpdate,PayDateUpdate;
    RadioButton RadioU,RadioU1,RadioU2;
    RadioGroup TypeGrpU;
    Button UpdatePBtn;
    DatabaseReference dbPayUpRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_update);



        /*payments= new Payments();

        UIDupdate  = findViewById(R.id.IDPayUpdt);
        PayNameUpdate = findViewById(R.id.NamePayUpdate);
        PayPhoneUpdate = findViewById(R.id.PhonePayUpdate);
        PayAmountUpdate = findViewById(R.id.UpdatePayAmount);
        PayDateUpdate = findViewById(R.id.UpdatePayDate);
        TypeGrpU = findViewById(R.id.UpdateRTgroup);
        RadioU1=findViewById(R.id.UpdatePayRd1);
        RadioU2=findViewById(R.id.UpdatePayRd2);

        UpdatePBtn=findViewById(R.id.buttonUpdate);

        Intent intent = getIntent();

        final String key = intent.getStringExtra(ViewPayment.PAY_KEY);
        final String PayId = intent.getStringExtra(ViewPayment.PAY_USERID);
        final String PayName = intent.getStringExtra(ViewPayment.PAY_NAME);
        final int PayPhone = intent.getIntExtra(ViewPayment.PAY_PHONENO,0);
        final String PayRoomType = intent.getStringExtra(ViewPayment.PAY_ROOMTYPE);
        final double PayAmount = intent.getDoubleExtra(ViewPayment.PAY_AMOUNT,0.0);
        final String PayDate = intent.getStringExtra(ViewPayment.PAY_DATE);
        final String PayStatus = intent.getStringExtra(ViewPayment.PAY_STATUS);

        UIDupdate.setText(PayId);
        PayNameUpdate.setText(PayName);
        PayPhoneUpdate.setText(Integer.toString(PayPhone));
        PayAmountUpdate.setText(Double.toString(PayAmount));
        PayDateUpdate.setText(PayDate);

        if(PayRoomType.equals("AC")){
            RadioU2.toggle();
            payments.setRoomType("AC");
        }
        else if(PayRoomType.equals("Non Ac")){
            RadioU1.toggle();
            payments.setRoomType("Non Ac");
        }


        dbPayUpRef = FirebaseDatabase.getInstance().getReference("Payments").child(key);*/

       /* UpdatePBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                payments.setKey(key);
                payments.setUserID(UIDupdate.getText().toString());
                payments.setName(PayNameUpdate.getText().toString());
                payments.setPhoneNo(Integer.parseInt(PayPhoneUpdate.getText().toString()));
                payments.setPayAmount(Double.parseDouble(PayAmountUpdate.getText().toString()));
                payments.setPayDate(PayDateUpdate.getText().toString());
                payments.setStatus("Pending");
                int selectedPid =TypeGrpU.getCheckedRadioButtonId();
                RadioU=(RadioButton) findViewById(selectedPid);
                payments.setRoomType(RadioU.getText().toString());

                dbPayUpRef.setValue(payments);

                Toast.makeText(getApplicationContext(),"Details Updated",Toast.LENGTH_SHORT).show();
            }
        });*/
    }


}
