package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.Order;
import Database.OrderList;

public class BoarderRequestFoodActivity extends AppCompatActivity {

    EditText edName;
    EditText edRNo;
    Button btnOrder;
    Spinner spinnerGenres;
    DatabaseReference dbRef;
    Order order;

    ListView listViewOrder;
    List<Order> orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_request_food);


        dbRef = FirebaseDatabase.getInstance().getReference("Order");

        edName= findViewById(R.id.edName);
        edRNo= findViewById(R.id.edRNo);
        btnOrder = findViewById(R.id.btnOrder22);
        spinnerGenres = findViewById(R.id.spinner2);

        listViewOrder= findViewById(R.id.lvOrder);
        orderList = new ArrayList<>();

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
            }
        });

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

                OrderList adapter=new OrderList(BoarderRequestFoodActivity.this,orderList);
                listViewOrder.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addOrder() {
        String name=edName.getText().toString().trim();
        String RoomNo=edRNo.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
            String  id=dbRef.push().getKey();
            order = new Order(id,name,RoomNo,genre);

            dbRef.child(id).setValue(order);
            Toast.makeText(getApplicationContext(),"Adding Success",Toast.LENGTH_LONG).show();
            cleanData();
        }else{
            Toast.makeText(getApplicationContext(),"You should Enter a Name",Toast.LENGTH_LONG).show();
        }
    }

    public void cleanData(){
        edName.setText("");
        edRNo.setText("");

    }
}

