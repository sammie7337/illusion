package com.flv.flowventory.ui.stock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.flv.flowventory.R;
import com.flv.flowventory.data.model.StockTransaction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StockTransactionAdapter extends RecyclerView.Adapter<StockTransactionAdapter.TransactionViewHolder> {
    private List<StockTransaction> transactions = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());

    public void updateTransactions(List<StockTransaction> newTransactions) {
        this.transactions = newTransactions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stock_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        StockTransaction transaction = transactions.get(position);
        holder.bind(transaction);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTransactionType;
        private TextView tvQuantity;
        private TextView tvUnitPrice;
        private TextView tvTotalValue;
        private TextView tvNotes;
        private TextView tvDate;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTransactionType = itemView.findViewById(R.id.tv_transaction_type);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            tvUnitPrice = itemView.findViewById(R.id.tv_unit_price);
            tvTotalValue = itemView.findViewById(R.id.tv_total_value);
            tvNotes = itemView.findViewById(R.id.tv_notes);
            tvDate = itemView.findViewById(R.id.tv_date);
        }

        public void bind(StockTransaction transaction) {
            tvTransactionType.setText(transaction.getTransactionType());
            tvQuantity.setText(String.valueOf(transaction.getQuantity()));
            tvUnitPrice.setText(String.format("$%.2f", transaction.getUnitPrice()));
            tvTotalValue.setText(String.format("$%.2f", transaction.getTotalValue()));
            tvNotes.setText(transaction.getNotes());
            
            String dateString = dateFormat.format(new Date(transaction.getTransactionDate()));
            tvDate.setText(dateString);
        }
    }
}
