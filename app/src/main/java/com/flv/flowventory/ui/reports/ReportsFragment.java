package com.flv.flowventory.ui.reports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.flv.flowventory.databinding.FragmentReportsBinding;

public class ReportsFragment extends Fragment {

    private FragmentReportsBinding binding;
    private ReportsViewModel reportsViewModel;
    private InventoryReportAdapter inventoryAdapter;
    private SalesReportAdapter salesAdapter;
    private StockReportAdapter stockAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reportsViewModel = new ViewModelProvider(this).get(ReportsViewModel.class);

        binding = FragmentReportsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupRecyclerViews();
        setupClickListeners();
        observeData();

        return root;
    }

    private void setupRecyclerViews() {
        // Inventory report
        inventoryAdapter = new InventoryReportAdapter();
        binding.recyclerViewInventory.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewInventory.setAdapter(inventoryAdapter);

        // Sales report
        salesAdapter = new SalesReportAdapter();
        binding.recyclerViewSales.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewSales.setAdapter(salesAdapter);

        // Stock report
        stockAdapter = new StockReportAdapter();
        binding.recyclerViewStock.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewStock.setAdapter(stockAdapter);
    }

    private void setupClickListeners() {
        binding.btnGenerateReport.setOnClickListener(v -> {
            // Generate comprehensive report
            generateComprehensiveReport();
        });

        binding.btnExportReport.setOnClickListener(v -> {
            // Export report functionality
            exportReport();
        });
    }

    private void observeData() {
        reportsViewModel.getInventoryReport().observe(getViewLifecycleOwner(), data -> {
            inventoryAdapter.updateData(data);
        });

        reportsViewModel.getSalesReport().observe(getViewLifecycleOwner(), data -> {
            salesAdapter.updateData(data);
        });

        reportsViewModel.getStockReport().observe(getViewLifecycleOwner(), data -> {
            stockAdapter.updateData(data);
        });

        reportsViewModel.getTotalSales().observe(getViewLifecycleOwner(), total -> {
            binding.tvTotalSales.setText(String.format("$%.2f", total));
        });

        reportsViewModel.getTotalProducts().observe(getViewLifecycleOwner(), total -> {
            binding.tvTotalProducts.setText(String.valueOf(total));
        });

        reportsViewModel.getLowStockCount().observe(getViewLifecycleOwner(), count -> {
            binding.tvLowStockCount.setText(String.valueOf(count));
        });
    }

    private void generateComprehensiveReport() {
        // In a real app, this would generate a comprehensive report
    }

    private void exportReport() {
        // In a real app, this would export the report to PDF/Excel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
