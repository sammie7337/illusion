package com.flv.flowventory.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.flv.flowventory.data.database.InventoryDatabase;
import com.flv.flowventory.data.repository.ProductRepository;
import com.flv.flowventory.data.repository.StockTransactionRepository;

public class DashboardViewModel extends ViewModel {
    private ProductRepository productRepository;
    private StockTransactionRepository stockTransactionRepository;
    
    private MutableLiveData<Integer> totalProducts = new MutableLiveData<>();
    private MutableLiveData<Integer> lowStockCount = new MutableLiveData<>();
    private MutableLiveData<Integer> outOfStockCount = new MutableLiveData<>();
    private MutableLiveData<Double> totalSales = new MutableLiveData<>();

    public DashboardViewModel() {
        // Initialize repositories
        // In a real app, you would inject these through dependency injection
    }

    public LiveData<Integer> getTotalProducts() {
        return totalProducts;
    }

    public LiveData<Integer> getLowStockCount() {
        return lowStockCount;
    }

    public LiveData<Integer> getOutOfStockCount() {
        return outOfStockCount;
    }

    public LiveData<Double> getTotalSales() {
        return totalSales;
    }

    public void loadDashboardData() {
        // Load dashboard data from repositories
        // This would typically involve database queries
        // For now, set some demo data
        totalProducts.setValue(150);
        lowStockCount.setValue(12);
        outOfStockCount.setValue(3);
        totalSales.setValue(12500.50);
    }
}
