package com.flv.flowventory.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.flv.flowventory.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupDashboard();
        observeData();

        return root;
    }

    private void setupDashboard() {
        // Setup dashboard cards and charts
        binding.cardTotalProducts.setOnClickListener(v -> {
            // Navigate to products
        });

        binding.cardLowStock.setOnClickListener(v -> {
            // Navigate to low stock products
        });

        binding.cardOutOfStock.setOnClickListener(v -> {
            // Navigate to out of stock products
        });

        binding.cardTotalSales.setOnClickListener(v -> {
            // Navigate to sales report
        });
    }

    private void observeData() {
        dashboardViewModel.getTotalProducts().observe(getViewLifecycleOwner(), total -> {
            binding.tvTotalProducts.setText(String.valueOf(total));
        });

        dashboardViewModel.getLowStockCount().observe(getViewLifecycleOwner(), count -> {
            binding.tvLowStock.setText(String.valueOf(count));
        });

        dashboardViewModel.getOutOfStockCount().observe(getViewLifecycleOwner(), count -> {
            binding.tvOutOfStock.setText(String.valueOf(count));
        });

        dashboardViewModel.getTotalSales().observe(getViewLifecycleOwner(), sales -> {
            binding.tvTotalSales.setText(String.format("$%.2f", sales));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
