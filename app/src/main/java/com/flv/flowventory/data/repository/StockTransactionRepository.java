package com.flv.flowventory.data.repository;

import androidx.lifecycle.LiveData;
import com.flv.flowventory.data.dao.StockTransactionDao;
import com.flv.flowventory.data.model.StockTransaction;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StockTransactionRepository {
    private StockTransactionDao stockTransactionDao;
    private ExecutorService executor;

    public StockTransactionRepository(StockTransactionDao stockTransactionDao) {
        this.stockTransactionDao = stockTransactionDao;
        this.executor = Executors.newFixedThreadPool(4);
    }

    public LiveData<List<StockTransaction>> getAllTransactions() {
        return stockTransactionDao.getAllTransactions();
    }

    public LiveData<List<StockTransaction>> getTransactionsByProduct(int productId) {
        return stockTransactionDao.getTransactionsByProduct(productId);
    }

    public LiveData<List<StockTransaction>> getTransactionsByType(String type) {
        return stockTransactionDao.getTransactionsByType(type);
    }

    public LiveData<List<StockTransaction>> getTransactionsByDateRange(long startDate, long endDate) {
        return stockTransactionDao.getTransactionsByDateRange(startDate, endDate);
    }

    public LiveData<List<StockTransaction>> getTransactionsByUser(int userId) {
        return stockTransactionDao.getTransactionsByUser(userId);
    }

    public LiveData<Integer> getTotalStockIn(int productId) {
        return stockTransactionDao.getTotalStockIn(productId);
    }

    public LiveData<Integer> getTotalStockOut(int productId) {
        return stockTransactionDao.getTotalStockOut(productId);
    }

    public void insertTransaction(StockTransaction transaction) {
        executor.execute(() -> stockTransactionDao.insertTransaction(transaction));
    }

    public void updateTransaction(StockTransaction transaction) {
        executor.execute(() -> stockTransactionDao.updateTransaction(transaction));
    }

    public void deleteTransaction(StockTransaction transaction) {
        executor.execute(() -> stockTransactionDao.deleteTransaction(transaction));
    }

    public void deleteTransactionById(int id) {
        executor.execute(() -> stockTransactionDao.deleteTransactionById(id));
    }
}
