package com.flv.flowventory.ui.stock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.flv.flowventory.databinding.FragmentStockBinding;

public class StockFragment extends Fragment {

    private FragmentStockBinding binding;
    private StockViewModel stockViewModel;
    private LowStockAdapter lowStockAdapter;
    private OutOfStockAdapter outOfStockAdapter;
    private StockTransactionAdapter transactionAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        stockViewModel = new ViewModelProvider(this).get(StockViewModel.class);

        binding = FragmentStockBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupRecyclerViews();
        setupClickListeners();
        observeData();

        return root;
    }

    private void setupRecyclerViews() {
        // Low stock products
        lowStockAdapter = new LowStockAdapter();
        binding.recyclerViewLowStock.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewLowStock.setAdapter(lowStockAdapter);

        // Out of stock products
        outOfStockAdapter = new OutOfStockAdapter();
        binding.recyclerViewOutOfStock.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewOutOfStock.setAdapter(outOfStockAdapter);

        // Recent transactions
        transactionAdapter = new StockTransactionAdapter();
        binding.recyclerViewTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewTransactions.setAdapter(transactionAdapter);
    }

    private void setupClickListeners() {
        binding.btnStockIn.setOnClickListener(v -> {
            // Open stock in dialog
            showStockInDialog();
        });

        binding.btnStockOut.setOnClickListener(v -> {
            // Open stock out dialog
            showStockOutDialog();
        });

        binding.btnStockAdjustment.setOnClickListener(v -> {
            // Open stock adjustment dialog
            showStockAdjustmentDialog();
        });

        binding.btnSearch.setOnClickListener(v -> {
            String searchTerm = binding.etSearch.getText().toString().trim();
            if (!searchTerm.isEmpty()) {
                stockViewModel.searchStock(searchTerm);
            }
        });
    }

    private void observeData() {
        stockViewModel.getLowStockProducts().observe(getViewLifecycleOwner(), products -> {
            lowStockAdapter.updateProducts(products);
        });

        stockViewModel.getOutOfStockProducts().observe(getViewLifecycleOwner(), products -> {
            outOfStockAdapter.updateProducts(products);
        });

        stockViewModel.getRecentTransactions().observe(getViewLifecycleOwner(), transactions -> {
            transactionAdapter.updateTransactions(transactions);
        });
    }

    private void showStockInDialog() {
        // In a real app, you would show a dialog for stock in operations
    }

    private void showStockOutDialog() {
        // In a real app, you would show a dialog for stock out operations
    }

    private void showStockAdjustmentDialog() {
        // In a real app, you would show a dialog for stock adjustments
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
