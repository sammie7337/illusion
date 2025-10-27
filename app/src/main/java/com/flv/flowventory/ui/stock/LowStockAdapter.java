package com.flv.flowventory.ui.stock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.flv.flowventory.R;
import com.flv.flowventory.data.model.Product;
import java.util.ArrayList;
import java.util.List;

public class LowStockAdapter extends RecyclerView.Adapter<LowStockAdapter.LowStockViewHolder> {
    private List<Product> products = new ArrayList<>();

    public void updateProducts(List<Product> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LowStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_low_stock, parent, false);
        return new LowStockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LowStockViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class LowStockViewHolder extends RecyclerView.ViewHolder {
        private TextView tvProductName;
        private TextView tvCurrentStock;
        private TextView tvMinStock;
        private TextView tvCategory;

        public LowStockViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvCurrentStock = itemView.findViewById(R.id.tv_current_stock);
            tvMinStock = itemView.findViewById(R.id.tv_min_stock);
            tvCategory = itemView.findViewById(R.id.tv_category);
        }

        public void bind(Product product) {
            tvProductName.setText(product.getName());
            tvCurrentStock.setText(String.valueOf(product.getCurrentStock()));
            tvMinStock.setText(String.valueOf(product.getMinStockLevel()));
            tvCategory.setText(product.getCategory());
        }
    }
}
