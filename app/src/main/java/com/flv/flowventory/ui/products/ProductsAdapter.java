package com.flv.flowventory.ui.products;

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

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    private List<Product> products = new ArrayList<>();
    private OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(Product product);
        void onProductEdit(Product product);
        void onProductDelete(Product product);
    }

    public void setOnProductClickListener(OnProductClickListener listener) {
        this.listener = listener;
    }

    public void updateProducts(List<Product> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvCategory;
        private TextView tvPrice;
        private TextView tvStock;
        private TextView tvStatus;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_product_name);
            tvCategory = itemView.findViewById(R.id.tv_product_category);
            tvPrice = itemView.findViewById(R.id.tv_product_price);
            tvStock = itemView.findViewById(R.id.tv_product_stock);
            tvStatus = itemView.findViewById(R.id.tv_product_status);
        }

        public void bind(Product product) {
            tvName.setText(product.getName());
            tvCategory.setText(product.getCategory());
            tvPrice.setText(String.format("$%.2f", product.getPrice()));
            tvStock.setText(String.valueOf(product.getCurrentStock()));

            // Set status based on stock level
            if (product.getCurrentStock() == 0) {
                tvStatus.setText("Out of Stock");
                tvStatus.setTextColor(itemView.getContext().getColor(R.color.error_color));
            } else if (product.getCurrentStock() <= product.getMinStockLevel()) {
                tvStatus.setText("Low Stock");
                tvStatus.setTextColor(itemView.getContext().getColor(R.color.warning_color));
            } else {
                tvStatus.setText("In Stock");
                tvStatus.setTextColor(itemView.getContext().getColor(R.color.success_color));
            }

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onProductClick(product);
                }
            });
        }
    }
}
