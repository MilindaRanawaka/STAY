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

import com.example.stay.ui.main.Frag4;

public class ManagementHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_home);
    }

    public void onBorder(View view){
        Intent intent = new Intent(this,MngBorderActivity.class);
        startActivity(intent);
    }

    public void onRoom(View view){
        Intent intent = new Intent(this,MngRoomActivity.class);
        startActivity(intent);
    }

    public void onPayment(View view){
        Intent intent = new Intent(this,RecievedPayments.class);
        startActivity(intent);
    }

    public void onFoodViewOrder(View view) {
        Intent intentfood = new Intent(this,MngFoodRequest.class);
        startActivity(intentfood);
    }

    public void onManageLeave(View view) {
        Intent intent = new Intent(this,MngLeaveReqActivity.class);
        startActivity(intent);
    }

    public void onAdminClick(View view){
        Intent intent = new Intent(this,ManageAdmin.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.itemBoxItem01A):
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                ActivityCompat.finishAffinity(this);
                startActivity(intent);
                return true;
            case(R.id.itemBoxItem02A):
                Intent intent2 = new Intent(getApplicationContext(),AdminChangePwdActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
