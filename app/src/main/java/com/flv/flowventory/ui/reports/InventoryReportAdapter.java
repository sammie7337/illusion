package com.flv.flowventory.ui.reports;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.flv.flowventory.R;
import java.util.ArrayList;
import java.util.List;

public class InventoryReportAdapter extends RecyclerView.Adapter<InventoryReportAdapter.ReportViewHolder> {
    private List<ReportsViewModel.ReportData> data = new ArrayList<>();

    public void updateData(List<ReportsViewModel.ReportData> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_report_data, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        ReportsViewModel.ReportData item = data.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ReportViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLabel;
        private TextView tvCount;
        private TextView tvValue;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLabel = itemView.findViewById(R.id.tv_label);
            tvCount = itemView.findViewById(R.id.tv_count);
            tvValue = itemView.findViewById(R.id.tv_value);
        }

        public void bind(ReportsViewModel.ReportData item) {
            tvLabel.setText(item.getLabel());
            tvCount.setText(String.valueOf(item.getCount()));
            tvValue.setText(String.format("$%.2f", item.getValue()));
        }
    }
}
