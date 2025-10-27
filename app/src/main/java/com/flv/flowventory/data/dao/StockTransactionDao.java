package com.flv.flowventory.data.dao;

import androidx.room.*;
import androidx.lifecycle.LiveData;
import com.flv.flowventory.data.model.StockTransaction;
import java.util.List;
import java.util.Date;

@Dao
public interface StockTransactionDao {
    
    @Query("SELECT * FROM stock_transactions ORDER BY transactionDate DESC")
    LiveData<List<StockTransaction>> getAllTransactions();
    
    @Query("SELECT * FROM stock_transactions WHERE productId = :productId ORDER BY transactionDate DESC")
    LiveData<List<StockTransaction>> getTransactionsByProduct(int productId);
    
    @Query("SELECT * FROM stock_transactions WHERE transactionType = :type ORDER BY transactionDate DESC")
    LiveData<List<StockTransaction>> getTransactionsByType(String type);
    
    @Query("SELECT * FROM stock_transactions WHERE transactionDate BETWEEN :startDate AND :endDate ORDER BY transactionDate DESC")
    LiveData<List<StockTransaction>> getTransactionsByDateRange(long startDate, long endDate);
    
    @Query("SELECT * FROM stock_transactions WHERE userId = :userId ORDER BY transactionDate DESC")
    LiveData<List<StockTransaction>> getTransactionsByUser(int userId);
    
    @Query("SELECT SUM(quantity) FROM stock_transactions WHERE productId = :productId AND transactionType = 'IN'")
    LiveData<Integer> getTotalStockIn(int productId);
    
    @Query("SELECT SUM(quantity) FROM stock_transactions WHERE productId = :productId AND transactionType = 'OUT'")
    LiveData<Integer> getTotalStockOut(int productId);
    
    @Insert
    void insertTransaction(StockTransaction transaction);
    
    @Update
    void updateTransaction(StockTransaction transaction);
    
    @Delete
    void deleteTransaction(StockTransaction transaction);
    
    @Query("DELETE FROM stock_transactions WHERE id = :id")
    void deleteTransactionById(int id);
}
