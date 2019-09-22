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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Database.Order;

public class OrderFoodApproval extends AppCompatActivity {

    Order order;
    TextView tvName2,tvShowTime2;
    TextView tvRNo2;
    Button btnAccept;
    Button btnReject;
    TextView tvOrder;
    DatabaseReference dbRef;

    public static final String ORDER_ID="orderID";
    public static final String ORDER_NAME="orderName";
    public static final String ORDER_ROOMNO="orderRoomNo";
    public static final String ORDER_GENRE="orderGenre";
    public static final String ORDER_TIME="orderTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_approval);

        dbRef = FirebaseDatabase.getInstance().getReference("Order");

        tvName2= findViewById(R.id.tvName2);
        tvRNo2= findViewById(R.id.tvRNo2);
        btnAccept = findViewById(R.id.btnAccept);
        btnReject=findViewById(R.id.btnReject);
        tvOrder = findViewById(R.id.tvOrder);
        tvShowTime2=findViewById(R.id.tvShowTime2);

        order = new Order();

        Intent intent = getIntent();

        final String OrderID = intent.getStringExtra(OrderFoodApproval.ORDER_ID);
        final String orderName=intent.getStringExtra(OrderFoodApproval.ORDER_NAME);
        final String orderRoomNo=intent.getStringExtra(OrderFoodApproval.ORDER_ROOMNO);
        final String orderGenre=intent.getStringExtra(OrderFoodApproval.ORDER_GENRE);
        final  String orderTime=intent.getStringExtra(OrderFoodApproval.ORDER_TIME);

        tvName2.setText(orderName);
        tvRNo2.setText(orderRoomNo);
        tvOrder.setText(orderGenre);
        tvShowTime2.setText(orderTime);

        dbRef = FirebaseDatabase.getInstance().getReference("Order").child(OrderID);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setOrderID(OrderID);
                order.setName(tvName2.getText().toString());
                order.setRNo(tvRNo2.getText().toString());
                order.setBgenre(tvOrder.getText().toString());
                order.setTime(tvShowTime2.getText().toString());
                order.setApprovalState("Accpet");

                dbRef.setValue(order);
                Toast.makeText(getApplicationContext(),"Order Accepted",Toast.LENGTH_SHORT).show();


            }
        });

        btnReject.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                order.setOrderID(OrderID);
                order.setName(tvName2.getText().toString());
                order.setRNo(tvRNo2.getText().toString());
                order.setBgenre(tvOrder.getText().toString());
                order.setTime(tvShowTime2.getText().toString());
                order.setApprovalState("Reject");

                dbRef.setValue(order);
                Toast.makeText(getApplicationContext(),"Order Rejected",Toast.LENGTH_SHORT).show();
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
