package com.example.sello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText name2,uid,gender,contact,email,password,cnfpwd;
    Button register;
    TextView signup_to_login;
    DbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        register = findViewById(R.id.signup);
        signup_to_login = findViewById(R.id.up_to_log);
        name2 = findViewById(R.id.name);
        uid = findViewById(R.id.uid);
        gender=findViewById(R.id.gender);
        contact=findViewById(R.id.contact);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        cnfpwd=findViewById(R.id.cnfpwd);
        db=new DbHandler(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),"Please Enter the Valid Input", Toast.LENGTH_SHORT).show();
                String name1 = name2.getText().toString();
                String uid1 = uid.getText().toString();
                String gender1 = gender.getText().toString();
                String contact1 = contact.getText().toString();
                String email1 = email.getText().toString();
                String pass = password.getText().toString();
                String cpass = cnfpwd.getText().toString();
                if((name1.isEmpty()) || (uid1.isEmpty()) || (gender1.isEmpty()) || (contact1.isEmpty()) || (email1.isEmpty()) || (pass.isEmpty()) || (cpass.isEmpty()))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter the Valid Input", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(cpass))
                    {
                        Boolean chkmail = db.chkemail(email1);
                        //for del


                        if(chkmail == true)
                        {
                            Boolean insert = db.insert(name1,uid1,gender1,contact1,email1,pass);
                            if(insert == true)
                            {
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                               // finish();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"There is problem in Registering",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Email is already Registered",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password is Not matched",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        signup_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
}
