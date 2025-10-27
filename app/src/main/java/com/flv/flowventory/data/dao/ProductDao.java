package com.flv.flowventory.data.dao;

import androidx.room.*;
import androidx.lifecycle.LiveData;
import com.flv.flowventory.data.model.Product;
import java.util.List;

@Dao
public interface ProductDao {
    
    @Query("SELECT * FROM products WHERE isActive = 1")
    LiveData<List<Product>> getAllActiveProducts();
    
    @Query("SELECT * FROM products WHERE id = :id")
    LiveData<Product> getProductById(int id);
    
    @Query("SELECT * FROM products WHERE category = :category AND isActive = 1")
    LiveData<List<Product>> getProductsByCategory(String category);
    
    @Query("SELECT * FROM products WHERE currentStock <= minStockLevel AND isActive = 1")
    LiveData<List<Product>> getLowStockProducts();
    
    @Query("SELECT * FROM products WHERE currentStock = 0 AND isActive = 1")
    LiveData<List<Product>> getOutOfStockProducts();
    
    @Query("SELECT * FROM products WHERE name LIKE '%' || :searchTerm || '%' AND isActive = 1")
    LiveData<List<Product>> searchProducts(String searchTerm);
    
    @Query("SELECT DISTINCT category FROM products WHERE isActive = 1")
    LiveData<List<String>> getAllCategories();
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);
    
    @Update
    void updateProduct(Product product);
    
    @Delete
    void deleteProduct(Product product);
    
    @Query("UPDATE products SET isActive = 0 WHERE id = :id")
    void deactivateProduct(int id);
    
    @Query("UPDATE products SET currentStock = :newStock, updatedAt = :timestamp WHERE id = :id")
    void updateStock(int id, int newStock, long timestamp);
}
