package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText tv1;
    String str1;
    int num1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view){

        tv1 = findViewById(R.id.unText);

        str1 = tv1.getText().toString();
        num1 = Integer.parseInt(str1);


        if(num1 == 1){
            Intent intent = new Intent(this,ManagementHomeActivity.class);
            startActivity(intent);
        } else if(num1 == 2){
            Intent intent2 = new Intent(this, BoardersHomeActivity.class);
            startActivity(intent2);
        }
    }
}