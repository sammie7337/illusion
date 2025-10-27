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

public class OutOfStockAdapter extends RecyclerView.Adapter<OutOfStockAdapter.OutOfStockViewHolder> {
    private List<Product> products = new ArrayList<>();

    public void updateProducts(List<Product> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OutOfStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_out_of_stock, parent, false);
        return new OutOfStockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutOfStockViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class OutOfStockViewHolder extends RecyclerView.ViewHolder {
        private TextView tvProductName;
        private TextView tvCategory;
        private TextView tvMinStock;

        public OutOfStockViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvMinStock = itemView.findViewById(R.id.tv_min_stock);
        }

        public void bind(Product product) {
            tvProductName.setText(product.getName());
            tvCategory.setText(product.getCategory());
            tvMinStock.setText("Min: " + product.getMinStockLevel());
        }
    }
}
