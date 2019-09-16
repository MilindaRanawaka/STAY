package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BoarderRequestFoodActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarder_request_food);

        listView=(ListView)findViewById(R.id.listview);

        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("Rice and Curry");
        arrayList.add("String Hoppers");
        arrayList.add("Hoppers");
        arrayList.add("Noodles");
        arrayList.add("Milk Rice");
        arrayList.add("Fried Rice");
        arrayList.add("Nasi goreng");
        arrayList.add("Special Soup");


        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

    }
}

