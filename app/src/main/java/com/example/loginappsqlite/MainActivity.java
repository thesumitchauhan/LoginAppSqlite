package com.example.loginappsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText repassword;
    Button btnSignUp;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnSignIn=findViewById(R.id.btnSignIn);

        DBHelper myDB=new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if(user.equals("'") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please Fill all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repass)){
                        if(myDB.checkUsername(user))
                        {
                            Toast.makeText(getApplicationContext(), "User is already present", Toast.LENGTH_SHORT).show();

                        }else{
                            myDB.insertData(user,pass);
                            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password is not matched", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}