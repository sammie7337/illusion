package com.flv.flowventory.data.repository;

import androidx.lifecycle.LiveData;
import com.flv.flowventory.data.dao.ProductDao;
import com.flv.flowventory.data.model.Product;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductRepository {
    private ProductDao productDao;
    private ExecutorService executor;

    public ProductRepository(ProductDao productDao) {
        this.productDao = productDao;
        this.executor = Executors.newFixedThreadPool(4);
    }

    public LiveData<List<Product>> getAllActiveProducts() {
        return productDao.getAllActiveProducts();
    }

    public LiveData<Product> getProductById(int id) {
        return productDao.getProductById(id);
    }

    public LiveData<List<Product>> getProductsByCategory(String category) {
        return productDao.getProductsByCategory(category);
    }

    public LiveData<List<Product>> getLowStockProducts() {
        return productDao.getLowStockProducts();
    }

    public LiveData<List<Product>> getOutOfStockProducts() {
        return productDao.getOutOfStockProducts();
    }

    public LiveData<List<Product>> searchProducts(String searchTerm) {
        return productDao.searchProducts(searchTerm);
    }

    public LiveData<List<String>> getAllCategories() {
        return productDao.getAllCategories();
    }

    public void insertProduct(Product product) {
        executor.execute(() -> productDao.insertProduct(product));
    }

    public void updateProduct(Product product) {
        executor.execute(() -> productDao.updateProduct(product));
    }

    public void deleteProduct(Product product) {
        executor.execute(() -> productDao.deleteProduct(product));
    }

    public void deactivateProduct(int id) {
        executor.execute(() -> productDao.deactivateProduct(id));
    }

    public void updateStock(int id, int newStock, long timestamp) {
        executor.execute(() -> productDao.updateStock(id, newStock, timestamp));
    }
}
