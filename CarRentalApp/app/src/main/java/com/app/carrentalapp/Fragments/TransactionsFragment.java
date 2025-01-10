package com.app.carrentalapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.carrentalapp.Adapters.RecyclerTransactionAdapter;
import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.Models.TransactionModel;
import com.app.carrentalapp.R;

import java.util.ArrayList;



public class TransactionsFragment extends Fragment {
    ArrayList<TransactionModel> arrTransactions = new ArrayList<>();
    RecyclerView recyclerView;

    public TransactionsFragment() {
        // Required empty public constructor
    }

    public static TransactionsFragment newInstance(String param1, String param2) {
        TransactionsFragment fragment = new TransactionsFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);
        recyclerView = view.findViewById(R.id.transactionInfoRecyler);

        MySQLDB database = new MySQLDB(getActivity());
        arrTransactions = database.fetchTransactions();
        database.close();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerTransactionAdapter adapter = new RecyclerTransactionAdapter(getActivity(),arrTransactions);
        recyclerView.setAdapter(adapter);
        return view;
    }
}