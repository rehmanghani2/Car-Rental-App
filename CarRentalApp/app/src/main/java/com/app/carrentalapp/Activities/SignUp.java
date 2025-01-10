package com.app.carrentalapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.R;

public class SignUp extends AppCompatActivity {
Button signUp;
TextView login;
EditText inputName, inputUsername, inputPassword, inputCPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signUp = findViewById(R.id.signupBtn);
        login = findViewById(R.id.loginText);
        inputName = findViewById(R.id.signName);
        inputUsername = findViewById(R.id.signUn);
        inputPassword = findViewById(R.id.signPass);
        inputCPassword = findViewById(R.id.signCPass);




        Intent iMain = new Intent(SignUp.this, MainActivity.class);
        Intent iLogin = new Intent(SignUp.this, LoginActivity.class);


        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final MySQLDB database = new MySQLDB(SignUp.this);
                final String name = inputName.getText().toString();
                final String username = inputUsername.getText().toString();
                final String password = inputPassword.getText().toString();
                final String cpassword = inputCPassword.getText().toString();

                if(!(password.equals(cpassword))){
                    Toast.makeText(SignUp.this,"Confirm Password does not match Password",Toast.LENGTH_LONG).show();
                } else if(((name.isEmpty() || username.isEmpty()) || (password.isEmpty() || cpassword.isEmpty()))){
                    Toast.makeText(SignUp.this,"Please fill all fields",Toast.LENGTH_LONG).show();
                }
                else {
                    if(database.registerUser(name, username, cpassword)!=-1){ // not equal to null or -1
                        Toast.makeText(SignUp.this,"User is registered ",Toast.LENGTH_SHORT).show();
                    }
                    database.close();
                    startActivity(iLogin);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(iLogin);
            }
        });

    }
}