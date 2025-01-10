package com.app.carrentalapp.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.carrentalapp.Adapters.RecyclerTransactionAdapter;
import com.app.carrentalapp.Models.TransactionModel;
import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.R;

import java.util.ArrayList;

public class Transactions extends AppCompatActivity {
ArrayList<TransactionModel> arrTransactions = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transactions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerTransaction);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        getSupportActionBar().setTitle("Tranacstions");
        MySQLDB database = new MySQLDB(Transactions.this);
        arrTransactions = database.fetchTransactions();
        database.close();

        RecyclerTransactionAdapter adapter = new RecyclerTransactionAdapter(this, arrTransactions);
        recyclerView.setAdapter(adapter);
    }
}