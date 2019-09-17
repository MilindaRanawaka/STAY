package com.example.stay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import Database.Payments;

public class AddPayment extends AppCompatActivity {
    EditText boarderID,boarderName,boarderPhone,payAmount,bank,payDate;
    Button addButton;
    Spinner bankSpnr;
    RadioButton roomBtn;
    RadioGroup roomGrp;
    Payments pay;
    DatabaseReference dbRefPay;
    private static final String TAG= "Main Activity";
    private DatePickerDialog.OnDateSetListener datasetListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        boarderID=findViewById(R.id.editTextpID);
        boarderName=findViewById(R.id.editTextpName);
        boarderPhone =findViewById(R.id.editTextpPhone);
        payAmount=findViewById(R.id.editTextpAmount);
        payDate=findViewById(R.id.editTextpDate);
        bankSpnr=findViewById(R.id.spinnerBank);
        roomGrp =findViewById(R.id.roomTypepRadio);
        addButton=findViewById(R.id.buttonpAdd);

        pay= new Payments();

        //setting on click listener to date input field to display date dialog
        payDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating the date dialog
                Calendar cal = Calendar.getInstance();
                int year =cal.get(Calendar.YEAR);
                int month =cal.get(Calendar.MONTH);
                int date =cal.get(Calendar.DATE);

                DatePickerDialog  dialog = new DatePickerDialog(
                        AddPayment.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        datasetListner,
                        year,month,date);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        //setting text to date input dialog
        datasetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //month= month+1;
                Log.d(TAG,"on data set: mm/dd/yyyy" + month +"/"+ day +"/"+ year);
                String date = month + "/" + day + "/" + year;
                payDate.setText(date);



            }
        };//end of date implementation

        //implementing the radio button process
        roomBtn = findViewById(roomGrp.getCheckedRadioButtonId());
        roomGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.radioAC:
                        pay.setRoomType("AC");
                        break;

                    case R.id.radioNonAC:
                        pay.setRoomType("Non Ac");
                        break;
                }
            }
        });

        //implementing the spinner
        //coding for spinner
        final List<String> BankList = Arrays.asList(getResources().getStringArray(R.array.Banks));
        //initializing a array adapter
        final ArrayAdapter<String> SpinnerArrayAdapter= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,BankList){
            @Override
            public boolean isEnabled(int position) {
                if(position ==0){
                    return false;
                }else{
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv=(TextView) view;
                if(position== 0){
                    tv.setTextColor(Color.GRAY);
                }else{
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        SpinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        bankSpnr.setAdapter(SpinnerArrayAdapter);
        //end of spinner coding

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dbRefPay= FirebaseDatabase.getInstance().getReference("Payments");
                if(TextUtils.isEmpty(boarderID.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter ID",Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this,"Please Enter ID",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(boarderName.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter Name",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(boarderPhone.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter Name",Toast.LENGTH_SHORT).show();
                }
                else if(roomGrp.getCheckedRadioButtonId()== -1){
                    Toast.makeText(getApplicationContext(),"Please Enter Room Type",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(payAmount.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter Amount",Toast.LENGTH_SHORT).show();
                }
                else if(bankSpnr.getSelectedItemPosition()==0){
                    Toast.makeText(getApplicationContext(),"Please Enter a Bank",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(payDate.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter Date",Toast.LENGTH_SHORT).show();
                }
                else{
                    pay.setKey(dbRefPay.push().getKey());
                    pay.setUserID(boarderID.getText().toString());
                    pay.setName(boarderName.getText().toString());
                    pay.setPhoneNo(Integer.parseInt(boarderPhone.getText().toString()));
                    pay.setPayAmount(Double.parseDouble(payAmount.getText().toString()));
                    pay.setBank(bankSpnr.getSelectedItem().toString());
                    pay.setPayDate(payDate.getText().toString());

                    dbRefPay.child(pay.getKey()).setValue(pay);
                    Toast.makeText(getApplicationContext(),"Data Added Succesfully",Toast.LENGTH_SHORT).show();
                }
            }
        });








    }

    public void openCompletePayment(View view) {
        Intent intent2= new Intent(this,CompletePayment.class);
        startActivity(intent2);
    }
}