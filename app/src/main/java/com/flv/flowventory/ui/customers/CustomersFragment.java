package com.flv.flowventory.ui.customers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.flv.flowventory.databinding.FragmentCustomersBinding;

public class CustomersFragment extends Fragment {

    private FragmentCustomersBinding binding;
    private CustomersViewModel customersViewModel;
    private CustomersAdapter customersAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        customersViewModel = new ViewModelProvider(this).get(CustomersViewModel.class);

        binding = FragmentCustomersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupRecyclerView();
        setupClickListeners();
        observeData();

        return root;
    }

    private void setupRecyclerView() {
        customersAdapter = new CustomersAdapter();
        binding.recyclerViewCustomers.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCustomers.setAdapter(customersAdapter);
        
        customersAdapter.setOnCustomerClickListener(new CustomersAdapter.OnCustomerClickListener() {
            @Override
            public void onCustomerClick(com.flv.flowventory.data.model.Customer customer) {
                // Handle customer click - show details
            }

            @Override
            public void onCustomerEdit(com.flv.flowventory.data.model.Customer customer) {
                // Handle customer edit
            }

            @Override
            public void onCustomerDelete(com.flv.flowventory.data.model.Customer customer) {
                // Handle customer delete
            }
        });
    }

    private void setupClickListeners() {
        binding.fabAddCustomer.setOnClickListener(v -> {
            // Open add customer dialog
            showAddCustomerDialog();
        });

        binding.btnSearch.setOnClickListener(v -> {
            String searchTerm = binding.etSearch.getText().toString().trim();
            if (!searchTerm.isEmpty()) {
                customersViewModel.searchCustomers(searchTerm);
            }
        });
    }

    private void observeData() {
        customersViewModel.getCustomers().observe(getViewLifecycleOwner(), customers -> {
            customersAdapter.updateCustomers(customers);
        });
    }

    private void showAddCustomerDialog() {
        AddCustomerDialogFragment dialog = new AddCustomerDialogFragment();
        dialog.setOnCustomerAddedListener(customer -> {
            customersViewModel.addCustomer(customer);
        });
        dialog.show(getParentFragmentManager(), "AddCustomerDialog");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
