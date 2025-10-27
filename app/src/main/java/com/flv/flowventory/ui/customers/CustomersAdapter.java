package com.flv.flowventory.ui.customers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.flv.flowventory.R;
import com.flv.flowventory.data.model.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomerViewHolder> {
    private List<Customer> customers = new ArrayList<>();
    private OnCustomerClickListener listener;

    public interface OnCustomerClickListener {
        void onCustomerClick(Customer customer);
        void onCustomerEdit(Customer customer);
        void onCustomerDelete(Customer customer);
    }

    public void setOnCustomerClickListener(OnCustomerClickListener listener) {
        this.listener = listener;
    }

    public void updateCustomers(List<Customer> newCustomers) {
        this.customers = newCustomers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer customer = customers.get(position);
        holder.bind(customer);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvEmail;
        private TextView tvPhone;
        private TextView tvLocation;
        private TextView tvType;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_customer_name);
            tvEmail = itemView.findViewById(R.id.tv_customer_email);
            tvPhone = itemView.findViewById(R.id.tv_customer_phone);
            tvLocation = itemView.findViewById(R.id.tv_customer_location);
            tvType = itemView.findViewById(R.id.tv_customer_type);
        }

        public void bind(Customer customer) {
            tvName.setText(customer.getName());
            tvEmail.setText(customer.getEmail());
            tvPhone.setText(customer.getPhone());
            
            String location = customer.getCity() + ", " + customer.getCountry();
            tvLocation.setText(location);
            
            String type = customer.getCustomerType() != null ? 
                customer.getCustomerType().substring(0, 1).toUpperCase() + 
                customer.getCustomerType().substring(1) : "Individual";
            tvType.setText(type);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onCustomerClick(customer);
                }
            });
        }
    }
}
