package com.app.carrentalapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.carrentalapp.Adapters.RecyclerAvailCarAdapter;
import com.app.carrentalapp.Models.CarModel;
import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.R;

import java.util.ArrayList;

/**

 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    int[] images= {R.drawable.image5, R.drawable.brabus_800_mercedes_benz_g65_2014, R.drawable.car,
            R.drawable.land_rover,R.drawable.prado,R.drawable.baseline_2014_toyota_highlander_hybrid_platinum,
            R.drawable.toyota_land_cruiser_200_grey, R.drawable.arden_range_rover2006,
            R.drawable.bmw,R.drawable.image5, R.drawable.brabus_800_mercedes_benz_g65_2014, R.drawable.car};
    ArrayList<CarModel> carList;


    RecyclerView recyclerView;
    RecyclerAvailCarAdapter adapter;
    ImageView searchIcon;
    EditText inputSearch;
    // RecyclerAvailCarAdapter adapter;


    public SearchFragment() {
        // Required empty public constructor
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
      //  return inflater.inflate(R.layout.fragment_search, container, false);

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.searchRecycler);
        inputSearch = view.findViewById(R.id.inputSearch);
        searchIcon = view.findViewById(R.id.searchIcon);

            MySQLDB database = new MySQLDB(getActivity());
            carList = database.fetchAvaialbleCars();
            database.close();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //      carList = getAvailCars();
        adapter = new RecyclerAvailCarAdapter(getActivity(),carList);
        recyclerView.setAdapter(adapter);


        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchItem = inputSearch.getText().toString();

                MySQLDB db = new MySQLDB(getActivity());
                ArrayList<CarModel> searchCarList = db.searchInCars(searchItem);
                db.close();
                adapter.updateData(searchCarList);
               adapter = new RecyclerAvailCarAdapter(getActivity(), searchCarList);
                recyclerView.setAdapter(adapter);
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                  String query = s.toString();
                  MySQLDB db = new MySQLDB(getActivity());
                  carList  = db.searchInCars(query);
                  db.close();
                  adapter.updateData(carList);
            }

            @Override
            public void afterTextChanged(Editable s) {
             //       searchIcon.hasFocus();
            }
        });

        return view;

    }
}