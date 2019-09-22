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
import android.widget.Toast;

public class BoardersHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarders_home);
    }

    public void MyProfileClick(View view){
        Intent intent = new Intent(this,BoarderManageActivity.class);
        startActivity(intent);
    }

    public void onRequFood(View view){
        Intent foodIntent = new Intent(this,BoarderFoodActivity.class);
        startActivity(foodIntent);
    }

    public void onPaymentClick(View view){
        Intent payIntent = new Intent(this,BoarderPaymentHomeActivity.class);
        startActivity(payIntent);
    }

    public void OnClickLeave(View view) {
        Intent LeaveClick = new Intent(this,BoarderLeaveHomeActivity.class);
        startActivity(LeaveClick);
    }

    @Override
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
