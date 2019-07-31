package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView tv1,tv2;
    String str1,str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view){
        tv1 = findViewById(R.id.unText);
        tv2 = findViewById(R.id.pwdText);

        str1 = tv1.getText().toString();
        str2 = tv2.getText().toString();

        if((str1 == "1")&&(str2 == "1")){
            tv1.setText("ADMIN");
        } else if((str1 == "2")&&(str2 == "2")){
            tv1.setText("User");
        }

    }
}
