package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class BoarderViewFoodActivity extends AppCompatActivity {

    DatabaseReference dbRef;
    List<Order> orderList;
    ListView listViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_view_food);

        listViewOrder=(ListView) findViewById(R.id.listViewNewRef);

        dbRef = FirebaseDatabase.getInstance().getReference("Order");
        orderList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot orderSnapshot : dataSnapshot.getChildren()){
                    Order order=orderSnapshot.getValue(Order.class);

                    orderList.add(order);
                }

                OrderList adapter=new OrderList(BoarderViewFoodActivity.this,orderList);
                listViewOrder.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}