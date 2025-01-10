package com.app.carrentalapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.carrentalapp.Models.CustomerModel;
import com.app.carrentalapp.R;

import java.util.ArrayList;

public class RecyclerCustomerAdapter extends RecyclerView.Adapter<RecyclerCustomerAdapter.ViewHolder> {
    Context context;
    ArrayList<CustomerModel> arrCustomers;
    public RecyclerCustomerAdapter(Context context, ArrayList<CustomerModel> arrCustomers){
        this.context = context;
        this.arrCustomers = arrCustomers;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_info_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgCust.setImageResource(arrCustomers.get(position).img);
        holder.txtId.setText(arrCustomers.get(position).customerID);
        holder.txtFn.setText(arrCustomers.get(position).firstName);
        holder.txtLN.setText(arrCustomers.get(position).lastName);
        holder.txtPhn.setText(arrCustomers.get(position).phone);
        holder.txtEmail.setText(arrCustomers.get(position).email);
        holder.txtAdd.setText(arrCustomers.get(position).address);
    }


    @Override
    public int getItemCount() {
        return arrCustomers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCust;
        TextView txtId, txtFn, txtLN, txtPhn, txtEmail, txtAdd;
        public ViewHolder(View itemView){
            super(itemView);
            imgCust = itemView.findViewById(R.id.rowCustImg);
            txtId = itemView.findViewById(R.id.rowCustId);
            txtFn = itemView.findViewById(R.id.rowCustFN);
            txtLN = itemView.findViewById(R.id.rowCustLN);
            txtPhn = itemView.findViewById(R.id.rowCustPhn);
            txtEmail = itemView.findViewById(R.id.rowCustEmail);
            txtAdd = itemView.findViewById(R.id.rowCustAdd);

        }
    }
}
