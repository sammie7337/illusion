package com.flv.flowventory.ui.suppliers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.flv.flowventory.R;
import com.flv.flowventory.data.model.Supplier;
import java.util.ArrayList;
import java.util.List;

public class SuppliersAdapter extends RecyclerView.Adapter<SuppliersAdapter.SupplierViewHolder> {
    private List<Supplier> suppliers = new ArrayList<>();
    private OnSupplierClickListener listener;

    public interface OnSupplierClickListener {
        void onSupplierClick(Supplier supplier);
        void onSupplierEdit(Supplier supplier);
        void onSupplierDelete(Supplier supplier);
    }

    public void setOnSupplierClickListener(OnSupplierClickListener listener) {
        this.listener = listener;
    }

    public void updateSuppliers(List<Supplier> newSuppliers) {
        this.suppliers = newSuppliers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_supplier, parent, false);
        return new SupplierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierViewHolder holder, int position) {
        Supplier supplier = suppliers.get(position);
        holder.bind(supplier);
    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    class SupplierViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvContactPerson;
        private TextView tvEmail;
        private TextView tvPhone;
        private TextView tvLocation;

        public SupplierViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_supplier_name);
            tvContactPerson = itemView.findViewById(R.id.tv_contact_person);
            tvEmail = itemView.findViewById(R.id.tv_supplier_email);
            tvPhone = itemView.findViewById(R.id.tv_supplier_phone);
            tvLocation = itemView.findViewById(R.id.tv_supplier_location);
        }

        public void bind(Supplier supplier) {
            tvName.setText(supplier.getName());
            tvContactPerson.setText(supplier.getContactPerson());
            tvEmail.setText(supplier.getEmail());
            tvPhone.setText(supplier.getPhone());
            
            String location = supplier.getCity() + ", " + supplier.getCountry();
            tvLocation.setText(location);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onSupplierClick(supplier);
                }
            });
        }
    }
}
