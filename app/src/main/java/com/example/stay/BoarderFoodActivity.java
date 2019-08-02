package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BoarderFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_food);
    }

    public void ADDORDER(View view) {
        Intent intent = new Intent(this,BoarderRequestFoodActivity.class);
        startActivity(intent);
    }
}
