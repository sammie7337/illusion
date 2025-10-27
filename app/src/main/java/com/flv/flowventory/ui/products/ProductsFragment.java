package com.flv.flowventory.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.flv.flowventory.databinding.FragmentProductsBinding;

public class ProductsFragment extends Fragment {

    private FragmentProductsBinding binding;
    private ProductsViewModel productsViewModel;
    private ProductsAdapter productsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productsViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);

        binding = FragmentProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupRecyclerView();
        setupClickListeners();
        observeData();

        return root;
    }

    private void setupRecyclerView() {
        productsAdapter = new ProductsAdapter();
        binding.recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewProducts.setAdapter(productsAdapter);
    }

    private void setupClickListeners() {
        binding.fabAddProduct.setOnClickListener(v -> {
            // Open add product dialog
            showAddProductDialog();
        });

        binding.btnSearch.setOnClickListener(v -> {
            String searchTerm = binding.etSearch.getText().toString().trim();
            if (!searchTerm.isEmpty()) {
                productsViewModel.searchProducts(searchTerm);
            }
        });
    }

    private void observeData() {
        productsViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            productsAdapter.updateProducts(products);
        });

        productsViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            // Update category filter if needed
        });
    }

    private void showAddProductDialog() {
        // In a real app, you would show a dialog or navigate to an add product screen
        // For now, just show a placeholder
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
