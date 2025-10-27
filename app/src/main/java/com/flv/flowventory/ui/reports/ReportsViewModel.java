package com.flv.flowventory.ui.reports;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class ReportsViewModel extends ViewModel {
    private MutableLiveData<List<ReportData>> inventoryReport = new MutableLiveData<>();
    private MutableLiveData<List<ReportData>> salesReport = new MutableLiveData<>();
    private MutableLiveData<List<ReportData>> stockReport = new MutableLiveData<>();
    private MutableLiveData<Double> totalSales = new MutableLiveData<>();
    private MutableLiveData<Integer> totalProducts = new MutableLiveData<>();
    private MutableLiveData<Integer> lowStockCount = new MutableLiveData<>();

    public ReportsViewModel() {
        loadReportsData();
    }

    public LiveData<List<ReportData>> getInventoryReport() {
        return inventoryReport;
    }

    public LiveData<List<ReportData>> getSalesReport() {
        return salesReport;
    }

    public LiveData<List<ReportData>> getStockReport() {
        return stockReport;
    }

    public LiveData<Double> getTotalSales() {
        return totalSales;
    }

    public LiveData<Integer> getTotalProducts() {
        return totalProducts;
    }

    public LiveData<Integer> getLowStockCount() {
        return lowStockCount;
    }

    public void loadReportsData() {
        // Load inventory report data
        List<ReportData> inventoryData = new ArrayList<>();
        inventoryData.add(new ReportData("Electronics", 45, 12500.0));
        inventoryData.add(new ReportData("Furniture", 23, 8900.0));
        inventoryData.add(new ReportData("Office Supplies", 67, 3200.0));
        inventoryData.add(new ReportData("Books", 34, 1800.0));
        inventoryReport.setValue(inventoryData);

        // Load sales report data
        List<ReportData> salesData = new ArrayList<>();
        salesData.add(new ReportData("January", 0, 12500.0));
        salesData.add(new ReportData("February", 0, 15200.0));
        salesData.add(new ReportData("March", 0, 18900.0));
        salesData.add(new ReportData("April", 0, 22100.0));
        salesData.add(new ReportData("May", 0, 19800.0));
        salesData.add(new ReportData("June", 0, 25600.0));
        salesReport.setValue(salesData);

        // Load stock report data
        List<ReportData> stockData = new ArrayList<>();
        stockData.add(new ReportData("In Stock", 120, 0.0));
        stockData.add(new ReportData("Low Stock", 15, 0.0));
        stockData.add(new ReportData("Out of Stock", 8, 0.0));
        stockReport.setValue(stockData);

        // Set summary data
        totalSales.setValue(114100.0);
        totalProducts.setValue(150);
        lowStockCount.setValue(15);
    }

    public static class ReportData {
        private String label;
        private int count;
        private double value;

        public ReportData(String label, int count, double value) {
            this.label = label;
            this.count = count;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public int getCount() {
            return count;
        }

        public double getValue() {
            return value;
        }
    }
}
