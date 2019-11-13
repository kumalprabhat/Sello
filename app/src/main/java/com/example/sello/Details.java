package com.example.sello;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    ImageView image,call,whatsapp;
    TextView nprice,oprice,name,contact;
    EditText details;
    int image_len;
    Bitmap image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        image = findViewById(R.id.photo);
        nprice = findViewById(R.id.nprice);
        oprice = findViewById(R.id.oprice);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        details = findViewById(R.id.about);


        sello_model Renti_model = (sello_model) getIntent().getExtras().getSerializable("sello");
        nprice.setText(Renti_model.getProduct_price());
        oprice.setText(Renti_model.getProduct_og_price());
        image1 = BitmapFactory.decodeByteArray(Renti_model.getImage(),0,Renti_model.getImage().length);
        image.setImageBitmap(image1);


    }
}
