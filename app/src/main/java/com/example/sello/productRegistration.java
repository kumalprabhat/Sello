package com.example.sello;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class productRegistration extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE_ID = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    Button upload, uploadimage;
    EditText pname,price,oprice,about;
    ImageView image;
    byte[] id_image;
    String picpath_profile;
    DbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_registration);
        pname=findViewById(R.id.pname);
        price=findViewById(R.id.nprice);
        oprice=findViewById(R.id.oprice);
        about=findViewById(R.id.about);
        image=findViewById(R.id.photo);
        db=new DbHandler(this);
        uploadimage=findViewById(R.id.uploadimage);
        upload=findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productname1=pname.getText().toString();
                String newprice1=price.getText().toString();
                String oldprice1=oprice.getText().toString();
                String about1=about.getText().toString();
                byte[]image1=imageViewToByte(image);

                boolean x=db.insert(productname1,newprice1,oldprice1,about1,image1);
                if (x){
                    Toast.makeText(getApplicationContext(),"Data uploaded Successfully",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),ItemList.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Data upload failed",Toast.LENGTH_LONG).show();
                }
            }
        });
        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission())
                {
                    Intent id_button = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(id_button,RESULT_LOAD_IMAGE_ID);
                }
                else
                {
                    requestPermission();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE_ID && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
            String[] path = {MediaStore.Images.Media.DATA};
            assert selectedImage != null;
            Cursor cursor = getContentResolver().query(selectedImage,path,null,null,null);
            assert cursor != null;
            cursor.moveToFirst();
            int columeIndex = cursor.getColumnIndex(path[0]);
            picpath_profile = cursor.getString(columeIndex);
            cursor.close();
            image.setImageBitmap(BitmapFactory.decodeFile(picpath_profile));
        }
    }

    private byte[] imageViewToByte(ImageView image)
    {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,10,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(productRegistration.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission()
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(productRegistration.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(getApplicationContext(), "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(productRegistration.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(productRegistration.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
}
