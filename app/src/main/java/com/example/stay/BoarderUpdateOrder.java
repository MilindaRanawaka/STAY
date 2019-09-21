package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

import Database.Order;

public class BoarderUpdateOrder extends AppCompatActivity {

    Order order;
    EditText edName;
    EditText edRNo;
    Button btnUpdate;
    Button btnDelete;
    Spinner spinnerGenres;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_update_order);


        dbRef = FirebaseDatabase.getInstance().getReference("Order");

        edName= findViewById(R.id.edName);
        edRNo= findViewById(R.id.edRNo);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnUpdate);
        spinnerGenres = findViewById(R.id.spinner2);

        Intent intent = getIntent();
        order=new Order();

        final String OrderID = intent.getStringExtra(BoarderViewFoodActivity.ORDER_ID);
        final String orderName=intent.getStringExtra(BoarderViewFoodActivity.ORDER_NAME);
        final String orderRoomNo=intent.getStringExtra(BoarderViewFoodActivity.ORDER_ROOMNO);
        final String orderGenre=intent.getStringExtra(BoarderViewFoodActivity.ORDER_GENRE);

        edName.setText(orderName);
        edRNo.setText(orderRoomNo);


        dbRef = FirebaseDatabase.getInstance().getReference("Order").child(OrderID);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setOrderID(OrderID);
                order.setName(edName.getText().toString());
                order.setRNo(edRNo.getText().toString());
                order.setBgenre(spinnerGenres.getSelectedItem().toString());
                order.setApprovalState("Pending");

                dbRef.setValue(order);
                Toast.makeText(getApplicationContext(),"Details Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onDelete(View view){
        dbRef.removeValue();
        Toast.makeText(getApplicationContext(),"Details Delete",Toast.LENGTH_SHORT).show();
    }
}
