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
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.LoginData;
import Database.Order;
import Database.OrderList;

public class BoarderViewFoodActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_boarder_view_food);

        listViewOrder=(ListView) findViewById(R.id.listViewNewRef);

        orderList = new ArrayList<>();

        Query query = FirebaseDatabase.getInstance().getReference("Order").orderByChild("userKey").equalTo(LoginData.userKey);
        query.addListenerForSingleValueEvent(valueEventListener);

        listViewOrder.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Order order=orderList.get(i);
                Intent intent=new Intent(getApplicationContext(),BoarderUpdateOrder.class);

                intent.putExtra(ORDER_ID,order.getOrderID());
                intent.putExtra(ORDER_NAME,order.getName());
                intent.putExtra(ORDER_ROOMNO,order.getRNo());
                intent.putExtra(ORDER_GENRE,order.getBgenre());
                intent.putExtra(ORDER_TIME,order.getTime());

                startActivity(intent);
            }
        });

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            orderList.clear();
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
    };

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.boarder_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.itemBoxItem01B):
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                ActivityCompat.finishAffinity(this);
                startActivity(intent);
                return true;
            case(R.id.itemBoxItem02B):
                Intent intent2 = new Intent(getApplicationContext(),BoarderUpdatePwdActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


}