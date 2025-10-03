package com.example.small_project.ui.history.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.small_project.R;
import com.example.small_project.data.entity.Transaction;
import com.example.small_project.databinding.ItemTransactionBinding;
import com.example.small_project.ui.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.TransactionViewHolder> {

    private List<Transaction> transactions = new ArrayList<>();

    public void submitItems(List<Transaction> transactions) {
        this.transactions = transactions != null ? transactions : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTransactionBinding binding = ItemTransactionBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new TransactionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        holder.bind(transactions.get(position), position);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }


    static class TransactionViewHolder extends RecyclerView.ViewHolder {

        ItemTransactionBinding binding;

        public TransactionViewHolder(ItemTransactionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Transaction transaction, int position){
            binding.tvTransactionId.setText(transaction.getTransactionId().toString());
            binding.tvTransactionAmount.setText(Utils.formatAmountWithSpace(transaction.getAmount()));
            binding.tvTransactionTime.setText(transaction.getDate());
            binding.status.setText(transaction.getStatusTransactionEnum().toString());
        }
    }


}
