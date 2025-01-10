package com.app.carrentalapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent iHome = new Intent(SplashActivity.this, LoginActivity.class);
        new Handler().postDelayed(new Runnable() {
            public void run(){
                startActivity(iHome);
                finish();
            }
        }, 4000);
        initializeCustomers();
    }
    public  void initializeCustomers(){
        MySQLDB database = new MySQLDB(this);
        database.insertIntoCustomers("cu001","Ali","Khan","03010101010","ali@gmail.com","Street xyz,City ABC");
        database.insertIntoCustomers("cu002","Umar","Ayaz","03020202020","umar@gmail.com","Street dfg,City YUI");
        database.insertIntoCustomers("cu003","Usman","Ghani","03345678910","usman@gmail.com","Street dfg,City DFG");
        database.insertIntoCustomers("cu004","Faiz","Ahmad","03456443356","faiz@gmail.com","Street fgh,City ZXC");
        database.insertIntoCustomers("cu005","Huziafa","Khan","03045769922","huzaifa@gmail.com","Street lkj,City QWE");
        database.insertIntoCustomers("cu006","Hassan","Ali","03457676666","hassan@gmail.com","Street jkl,City DCV");
        database.insertIntoCustomers("cu007","Bilal","Khan","03066667777","bilal@gmail.com","Street bvn,City HJK");
        database.close();
    }

}