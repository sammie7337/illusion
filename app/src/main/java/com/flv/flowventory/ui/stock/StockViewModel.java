package com.flv.flowventory.ui.stock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.flv.flowventory.data.model.Product;
import com.flv.flowventory.data.model.StockTransaction;
import com.flv.flowventory.data.repository.ProductRepository;
import com.flv.flowventory.data.repository.StockTransactionRepository;
import java.util.ArrayList;
import java.util.List;

public class StockViewModel extends ViewModel {
    private ProductRepository productRepository;
    private StockTransactionRepository stockTransactionRepository;
    
    private MutableLiveData<List<Product>> lowStockProducts = new MutableLiveData<>();
    private MutableLiveData<List<Product>> outOfStockProducts = new MutableLiveData<>();
    private MutableLiveData<List<StockTransaction>> recentTransactions = new MutableLiveData<>();
    private MutableLiveData<String> searchQuery = new MutableLiveData<>();

    public StockViewModel() {
        loadStockData();
    }

    public LiveData<List<Product>> getLowStockProducts() {
        return lowStockProducts;
    }

    public LiveData<List<Product>> getOutOfStockProducts() {
        return outOfStockProducts;
    }

    public LiveData<List<StockTransaction>> getRecentTransactions() {
        return recentTransactions;
    }

    public LiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public void loadStockData() {
        // Load low stock products
        List<Product> lowStockList = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("Laptop XYZ");
        product1.setCurrentStock(2);
        product1.setMinStockLevel(5);
        product1.setCategory("Electronics");
        lowStockList.add(product1);

        Product product2 = new Product();
        product2.setName("Mouse ABC");
        product2.setCurrentStock(1);
        product2.setMinStockLevel(10);
        product2.setCategory("Electronics");
        lowStockList.add(product2);

        lowStockProducts.setValue(lowStockList);

        // Load out of stock products
        List<Product> outOfStockList = new ArrayList<>();
        Product product3 = new Product();
        product3.setName("Keyboard DEF");
        product3.setCurrentStock(0);
        product3.setMinStockLevel(3);
        product3.setCategory("Electronics");
        outOfStockList.add(product3);

        outOfStockProducts.setValue(outOfStockList);

        // Load recent transactions
        List<StockTransaction> transactions = new ArrayList<>();
        StockTransaction transaction1 = new StockTransaction();
        transaction1.setProductId(1);
        transaction1.setTransactionType("IN");
        transaction1.setQuantity(50);
        transaction1.setUnitPrice(25.99);
        transaction1.setNotes("New shipment received");
        transactions.add(transaction1);

        StockTransaction transaction2 = new StockTransaction();
        transaction2.setProductId(2);
        transaction2.setTransactionType("OUT");
        transaction2.setQuantity(10);
        transaction2.setUnitPrice(15.99);
        transaction2.setNotes("Sales transaction");
        transactions.add(transaction2);

        recentTransactions.setValue(transactions);
    }

    public void searchStock(String query) {
        searchQuery.setValue(query);
        // In a real app, this would search the database
        loadStockData(); // For demo, just reload data
    }

    public void addStockTransaction(StockTransaction transaction) {
        List<StockTransaction> currentTransactions = recentTransactions.getValue();
        if (currentTransactions != null) {
            currentTransactions.add(0, transaction); // Add to beginning
            recentTransactions.setValue(currentTransactions);
        }
    }
}
