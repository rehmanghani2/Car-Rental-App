package com.app.carrentalapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.carrentalapp.R;
import com.app.carrentalapp.Models.TransactionModel;

import java.util.ArrayList;

public class RecyclerTransactionAdapter extends RecyclerView.Adapter<RecyclerTransactionAdapter.ViewHolder> {
    Context context;
    ArrayList<TransactionModel> arrTransaction;
    public RecyclerTransactionAdapter(Context context, ArrayList<TransactionModel> arrTransaction){
        this.context = context;
        this.arrTransaction = arrTransaction;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transactions_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.txtTransID.setText(arrTransaction.get(position).transactionID);
       holder.txtCarID.setText(arrTransaction.get(position).carID);
       holder.txtCustomerID.setText(arrTransaction.get(position).customerID);
       holder.txtStartDate.setText(String.valueOf(arrTransaction.get(position).startDate));
       holder.txtEndDate.setText(String.valueOf(arrTransaction.get(position).endDate));
       holder.txtCost.setText(String.valueOf(arrTransaction.get(position).totalCost));
       holder.txtStatus.setText(arrTransaction.get(position).paymentStatus);
    }

    @Override
    public int getItemCount() {
        return arrTransaction.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTransID, txtCarID, txtCustomerID, txtStartDate,txtEndDate,txtCost, txtStatus;
        public ViewHolder(View itemView){
            super(itemView);
            txtTransID = itemView.findViewById(R.id.transRowTID);
            txtCarID = itemView.findViewById(R.id.transRowCarID);
            txtCustomerID = itemView.findViewById(R.id.transRowCustomID);
            txtStartDate = itemView.findViewById(R.id.transRowSdate);
            txtEndDate = itemView.findViewById(R.id.transRowEdate);
            txtCost = itemView.findViewById(R.id.transRowCost);
            txtStatus = itemView.findViewById(R.id.transRowStatus);
        }
    }
}
