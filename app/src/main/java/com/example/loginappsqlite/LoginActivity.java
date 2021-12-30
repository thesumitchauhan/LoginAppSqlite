package com.example.loginappsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBHelper myDB=new DBHelper(this);

        usernameEditText=findViewById(R.id.usernameEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        loginButton=findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=usernameEditText.getText().toString();
                String password=passwordEditText.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(), "fill all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    if(myDB.checkUsernamePassword(username,password))
                    {
                        Intent intent=new Intent(getApplicationContext(),WelcomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Invalid Credential", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}