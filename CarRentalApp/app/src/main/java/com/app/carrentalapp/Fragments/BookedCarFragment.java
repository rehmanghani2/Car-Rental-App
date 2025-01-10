package com.app.carrentalapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.carrentalapp.Adapters.RecyclerAvailCarAdapter;
import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.Models.CarModel;
import com.app.carrentalapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookedCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookedCarFragment extends Fragment {

    ArrayList<CarModel> carList;

    RecyclerView recyclerView;

    public BookedCarFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BookedCarFragment newInstance(String param1, String param2) {
        BookedCarFragment fragment = new BookedCarFragment();
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
      //  Fragment fragment =

        View view =  inflater.inflate(R.layout.fragment_booked_car, container, false);
        recyclerView = view.findViewById(R.id.bookedRecyler);

        MySQLDB database = new MySQLDB(getActivity());
        carList = database.fetchBookedCars();
        database.close();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //      carList = getAvailCars();

        RecyclerAvailCarAdapter adapter = new RecyclerAvailCarAdapter(getActivity(),carList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}