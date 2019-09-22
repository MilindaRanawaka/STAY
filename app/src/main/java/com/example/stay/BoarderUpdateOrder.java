package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

import Database.LoginData;
import Database.Order;

public class BoarderUpdateOrder extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    Order order;
    EditText edName;
    EditText edRNo;
    TextView tvShowTime;
    Button btnUpdate;
    Button btnDelete;
    Spinner spinnerGenres;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_update_order);

        Button btnTime=(Button) findViewById(R.id.btnTime) ;
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker=new TimePicker();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference("Order");

        edName= findViewById(R.id.edName);
        edRNo= findViewById(R.id.edRNo);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnUpdate);
        spinnerGenres = findViewById(R.id.spinner2);
        tvShowTime=findViewById(R.id.tvShowTime);

        Intent intent = getIntent();
        order=new Order();

        final String OrderID = intent.getStringExtra(BoarderViewFoodActivity.ORDER_ID);
        final String orderName=intent.getStringExtra(BoarderViewFoodActivity.ORDER_NAME);
        final String orderRoomNo=intent.getStringExtra(BoarderViewFoodActivity.ORDER_ROOMNO);
        final String orderGenre=intent.getStringExtra(BoarderViewFoodActivity.ORDER_GENRE);
        final String orderTime=intent.getStringExtra(BoarderViewFoodActivity.ORDER_TIME);


        ArrayAdapter arrayAdapter=(ArrayAdapter)spinnerGenres.getAdapter();
        final int spinnerPosition=arrayAdapter.getPosition(orderGenre);
        spinnerGenres.setSelection(spinnerPosition);

        edName.setText(orderName);
        edRNo.setText(orderRoomNo);
        tvShowTime.setText(orderTime);

        dbRef = FirebaseDatabase.getInstance().getReference("Order").child(OrderID);

        edName.setText(LoginData.userName);
        edRNo.setText(LoginData.userRoomNo);
        edName.setEnabled(false);
        edRNo.setEnabled(false);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setOrderID(OrderID);
                order.setName(edName.getText().toString());
                order.setRNo(edRNo.getText().toString());
                order.setBgenre(spinnerGenres.getSelectedItem().toString());
                order.setApprovalState("Pending");
                order.setUserKey(LoginData.userKey);
                order.setTime(tvShowTime.getText().toString());


                dbRef.setValue(order);
                Toast.makeText(getApplicationContext(),"Details Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onDelete(View view){
        dbRef.removeValue();
        Toast.makeText(getApplicationContext(),"Details Delete",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        TextView tvShowTime=(TextView)findViewById(R.id.tvShowTime);
        tvShowTime.setText("Hour:"+hourOfDay+"   "+"Minute:"+minute);
    }
}
