package com.example.sello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
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
        list = new ArrayList<>();
        list = db.getDetails();
        MyAdapter adapter = new MyAdapter(this,list);
        list1.setAdapter(adapter);


        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sello_model sello_model = list.get(position);
                Intent intent = new Intent(getApplicationContext(),Details.class);
                intent.putExtra("sello",sello_model);
                startActivity(intent);
            }
        });



    }
}
