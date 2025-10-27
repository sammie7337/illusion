package com.flv.flowventory;

import android.app.Application;
import com.flv.flowventory.data.database.InventoryDatabase;
import com.flv.flowventory.data.repository.*;

public class FlowventoryApplication extends Application {
    private static FlowventoryApplication instance;
    private InventoryDatabase database;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private StockTransactionRepository stockTransactionRepository;
    private SupplierRepository supplierRepository;
    private CustomerRepository customerRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initializeDatabase();
        initializeRepositories();
    }

    public static FlowventoryApplication getInstance() {
        return instance;
    }

    private void initializeDatabase() {
        database = InventoryDatabase.getDatabase(this);
    }

    private void initializeRepositories() {
        userRepository = new UserRepository(database.userDao());
        productRepository = new ProductRepository(database.productDao());
        stockTransactionRepository = new StockTransactionRepository(database.stockTransactionDao());
        supplierRepository = new SupplierRepository(database.supplierDao());
        customerRepository = new CustomerRepository(database.customerDao());
    }

    public InventoryDatabase getDatabase() {
        return database;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public StockTransactionRepository getStockTransactionRepository() {
        return stockTransactionRepository;
    }

    public SupplierRepository getSupplierRepository() {
        return supplierRepository;
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
