package com.example.sello;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText email,pass;
    Button login;
    TextView log_to_sign;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);

        login = findViewById(R.id.login);
        log_to_sign = findViewById(R.id.log_to_signup);
        db=new DbHandler(this);

        log_to_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String pass1 = pass.getText().toString();

                if(email1.isEmpty() || pass1.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter the Valid Input", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean chk = db.emailpass(email1,pass1);
                    if(chk == true)
                    {
                        Toast.makeText(getApplicationContext(),"Login Successfuly",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, ItemList.class);
                        startActivity(intent);
                        //finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"No such Details are found please register yourself",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}