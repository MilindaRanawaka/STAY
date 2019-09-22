package com.example.stay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.LoginData;
import Database.Order;
import Database.OrderList;

public class BoarderRequestFoodActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    EditText edName;
    EditText edRNo;
    TextView tvShowTime;
    Button btnOrder,btnTime;
    Spinner spinnerGenres;
    DatabaseReference dbRef;
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_request_food);

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
        btnOrder = findViewById(R.id.btnOrder22);
        spinnerGenres = findViewById(R.id.spinner2);
        btnTime=findViewById(R.id.btnTime);
        tvShowTime=findViewById(R.id.tvShowTime);

        edName.setText(LoginData.userName);
        edRNo.setText(LoginData.userRoomNo);
        edName.setEnabled(false);
        edRNo.setEnabled(false);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
            }
        });

        }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        TextView tvShowTime=(TextView)findViewById(R.id.tvShowTime);
        tvShowTime.setText("Hour:"+hourOfDay+"   "+"Minute:"+minute);
    }


    private void addOrder() {
        String name=edName.getText().toString().trim();
        String RoomNo=edRNo.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();
        String time=tvShowTime.getText().toString().trim();

        if(!TextUtils.isEmpty(time)){
            String  id=dbRef.push().getKey();
            order = new Order(id,name,RoomNo,genre,"Pending", LoginData.userKey,time);

            dbRef.child(id).setValue(order);
            Toast.makeText(getApplicationContext(),"Adding Success",Toast.LENGTH_LONG).show();
            cleanData();
        }else{
            Toast.makeText(getApplicationContext(),"Enter requested Time",Toast.LENGTH_LONG).show();
        }
    }

    public void cleanData(){
        edName.setText("");
        edRNo.setText("");
        tvShowTime.setText("");

    }

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

