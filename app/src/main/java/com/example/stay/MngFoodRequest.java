package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.Order;
import Database.OrderList;

public class MngFoodRequest extends AppCompatActivity {

    DatabaseReference dbRef;
    List<Order> orderList;
    ListView listViewOrder;
    Order order;

    public static final String ORDER_ID="orderID";
    public static final String ORDER_NAME="orderName";
    public static final String ORDER_ROOMNO="orderRoomNo";
    public static final String ORDER_GENRE="orderGenre";
    public static final String ORDER_TIME="orderTime";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mng_food_request);

        listViewOrder=(ListView) findViewById(R.id.listView2);

        dbRef = FirebaseDatabase.getInstance().getReference("Order");
        orderList = new ArrayList<>();

        listViewOrder.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Order order=orderList.get(i);
                Intent intent=new Intent(getApplicationContext(),OrderFoodApproval.class);

                intent.putExtra(ORDER_ID,order.getOrderID());
                intent.putExtra(ORDER_NAME,order.getName());
                intent.putExtra(ORDER_ROOMNO,order.getRNo());
                intent.putExtra(ORDER_GENRE,order.getBgenre());
                intent.putExtra(ORDER_TIME,order.getTime());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderList.clear();
                for(DataSnapshot orderSnapshot : dataSnapshot.getChildren()){
                    Order order=orderSnapshot.getValue(Order.class);

                    orderList.add(order);
                }

                OrderList adapter=new OrderList(MngFoodRequest.this,orderList);
                listViewOrder.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}