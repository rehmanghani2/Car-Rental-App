package com.app.carrentalapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.carrentalapp.Adapters.RecyclerCustomerAdapter;
import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.Models.CustomerModel;
import com.app.carrentalapp.R;

import java.util.ArrayList;


public class CustomersFragment extends Fragment {

ArrayList<CustomerModel> arrCustomers = new ArrayList<>();
    public CustomersFragment() {
        // Required empty public constructor
    }

    public static CustomersFragment newInstance(String param1, String param2) {
        CustomersFragment fragment = new CustomersFragment();
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
        View view = inflater.inflate(R.layout.fragment_customers, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.customerInfoRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MySQLDB database = new MySQLDB(getActivity());
        //  database.initializeCustomers();
        arrCustomers = database.fetchCustomers();
        database.close();

        System.out.println(arrCustomers);
        RecyclerCustomerAdapter adapter = new RecyclerCustomerAdapter(getActivity(), arrCustomers);
        recyclerView.setAdapter(adapter);
        return view;
    }
}