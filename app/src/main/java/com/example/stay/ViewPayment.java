package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.LeaveList;
import Database.LeaveReq;
import Database.LoginData;
import Database.Payments;
import Database.PaymentsList;

public class ViewPayment extends AppCompatActivity {
    DatabaseReference dbpRef = FirebaseDatabase.getInstance().getReference("Payments");
    ListView payList;
    List<Payments> payArrayList;

    public static final String PAY_KEY = "paymentKey";
    public static final String PAY_USERID = "paymentUId";
    public static final String PAY_NAME = "paymentUName";
    public static final String PAY_PHONENO = "paymentPhoneNo";
    public static final String PAY_ROOMTYPE = "paymentRType";
    public static final String PAY_AMOUNT = "paymentAmount";
    public static final String PAY_BANK = "paymentBank";
    public static final String PAY_DATE = "paymentDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payment);

        payList=findViewById(R.id.PaymentListv);

        payArrayList= new ArrayList<>();

       payList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Payments payView = payArrayList.get(i);
                Intent intentpay = new Intent(getApplicationContext(),PaymentUpdate.class);

                intentpay.putExtra(PAY_KEY,payView.getKey());
                intentpay.putExtra(PAY_USERID,payView.getUserID());
                intentpay.putExtra(PAY_NAME,payView.getName());
                intentpay.putExtra(PAY_PHONENO,payView.getPhoneNo());
                intentpay.putExtra(PAY_ROOMTYPE,payView.getRoomType());
                intentpay.putExtra(PAY_AMOUNT,payView.getPayAmount());
                intentpay.putExtra(PAY_BANK,payView.getBank());
                intentpay.putExtra(PAY_DATE,payView.getPayDate());

                startActivity(intentpay);

            }
        });

        Query query = FirebaseDatabase.getInstance().getReference("Payments").orderByChild("userID").equalTo(LoginData.userKey);
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            payArrayList.clear();
            for (DataSnapshot PaymentSnapShot : dataSnapshot.getChildren()){
                Payments payments1 = PaymentSnapShot.getValue(Payments.class);
                payArrayList.add(payments1);
            }
            PaymentsList list = new PaymentsList(ViewPayment.this,payArrayList);
            payList.setAdapter(list);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}

