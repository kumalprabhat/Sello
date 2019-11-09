package com.example.sello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ItemList extends AppCompatActivity {

    ArrayList<sello_model> list;
    ListView list1;
   // MyAdapter adapter;
    FloatingActionButton fab;
    DbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);
        list1 = findViewById(R.id.list);
        fab=findViewById(R.id.fab);
        db = new DbHandler(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),productRegistration.class);
                startActivity(i);
            }
        });

        list = db.getDetails();


        //Model return ho rha hai...
       ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);//vahi model ka object pass ho rha hai..
        //ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,{"});
        list1.setAdapter(arrayAdapter);//mere list view mein main vahi model pass krr rha hu....


    }
}
