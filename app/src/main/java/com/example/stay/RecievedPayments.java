package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.stay.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.PaymentManagerList;
import Database.Payments;
import Database.PaymentsList;

public class RecievedPayments extends AppCompatActivity {

    ListView listViewRecPayList;
    List<Payments>  recievedPayList;
    Query dbrefRpay;

    public static final String DEL_PAY_KEY="key";
    public static final String DEL_PAY_UID="userID";
    public static final String DEL_PAY_NAME="name";
    public static final String DEL_PAY_PHN="phoneNo";
    public static final String DEL_PAY_RT="roomType";
    public static  final String DEL_PAY_DATE="paydate";
    public static final String DEL_PAY_AMT="amount";
    public static final String DEL_PAY_BNK="bank";
    public static final String DEL_PAY_STATUS="status";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieved_payments);

        listViewRecPayList= findViewById(R.id.RecievedPayList);
        dbrefRpay= FirebaseDatabase.getInstance().getReference("Payments").orderByChild("status").equalTo("Pending");

        recievedPayList= new ArrayList<>();

        listViewRecPayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Payments payManageArr= recievedPayList.get(i);
                Intent intentPm = new Intent(getApplicationContext(),Delete_PaymentActivity.class);

                intentPm.putExtra(DEL_PAY_KEY,payManageArr.getKey());
                intentPm.putExtra(DEL_PAY_UID,payManageArr.getUserID());
                intentPm.putExtra(DEL_PAY_NAME,payManageArr.getName());
                intentPm.putExtra(DEL_PAY_PHN,payManageArr.getPhoneNo());
                intentPm.putExtra(DEL_PAY_RT,payManageArr.getRoomType());
                intentPm.putExtra(DEL_PAY_DATE, payManageArr.getPayDate());
                Bundle b = new Bundle();
                b.putDouble(DEL_PAY_AMT,payManageArr.getPayAmount());
                intentPm.putExtras(b);
                intentPm.putExtra(DEL_PAY_BNK,payManageArr.getBank());
                intentPm.putExtra(DEL_PAY_STATUS,payManageArr.getStatus());

                startActivity(intentPm);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbrefRpay.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                recievedPayList.clear();
               for(DataSnapshot recievedPaySnapShot : dataSnapshot.getChildren()){
                   Payments payManage = recievedPaySnapShot.getValue(Payments.class);
                   recievedPayList.add(payManage);
               }

                PaymentManagerList adapter1= new PaymentManagerList(RecievedPayments.this,recievedPayList);
                listViewRecPayList.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
