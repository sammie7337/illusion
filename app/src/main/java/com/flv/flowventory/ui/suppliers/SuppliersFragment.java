package com.flv.flowventory.ui.suppliers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.flv.flowventory.databinding.FragmentSuppliersBinding;

public class SuppliersFragment extends Fragment {

    private FragmentSuppliersBinding binding;
    private SuppliersViewModel suppliersViewModel;
    private SuppliersAdapter suppliersAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        suppliersViewModel = new ViewModelProvider(this).get(SuppliersViewModel.class);

        binding = FragmentSuppliersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupRecyclerView();
        setupClickListeners();
        observeData();

        return root;
    }

    private void setupRecyclerView() {
        suppliersAdapter = new SuppliersAdapter();
        binding.recyclerViewSuppliers.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewSuppliers.setAdapter(suppliersAdapter);
        
        suppliersAdapter.setOnSupplierClickListener(new SuppliersAdapter.OnSupplierClickListener() {
            @Override
            public void onSupplierClick(com.flv.flowventory.data.model.Supplier supplier) {
                // Handle supplier click - show details
            }

            @Override
            public void onSupplierEdit(com.flv.flowventory.data.model.Supplier supplier) {
                // Handle supplier edit
            }

            @Override
            public void onSupplierDelete(com.flv.flowventory.data.model.Supplier supplier) {
                // Handle supplier delete
            }
        });
    }

    private void setupClickListeners() {
        binding.fabAddSupplier.setOnClickListener(v -> {
            // Open add supplier dialog
            showAddSupplierDialog();
        });

        binding.btnSearch.setOnClickListener(v -> {
            String searchTerm = binding.etSearch.getText().toString().trim();
            if (!searchTerm.isEmpty()) {
                suppliersViewModel.searchSuppliers(searchTerm);
            }
        });
    }

    private void observeData() {
        suppliersViewModel.getSuppliers().observe(getViewLifecycleOwner(), suppliers -> {
            suppliersAdapter.updateSuppliers(suppliers);
        });
    }

    private void showAddSupplierDialog() {
        AddSupplierDialogFragment dialog = new AddSupplierDialogFragment();
        dialog.setOnSupplierAddedListener(supplier -> {
            suppliersViewModel.addSupplier(supplier);
        });
        dialog.show(getParentFragmentManager(), "AddSupplierDialog");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
