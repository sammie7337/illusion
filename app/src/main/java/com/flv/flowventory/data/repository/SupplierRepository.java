package com.flv.flowventory.data.repository;

import androidx.lifecycle.LiveData;
import com.flv.flowventory.data.dao.SupplierDao;
import com.flv.flowventory.data.model.Supplier;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SupplierRepository {
    private SupplierDao supplierDao;
    private ExecutorService executor;

    public SupplierRepository(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
        this.executor = Executors.newFixedThreadPool(4);
    }

    public LiveData<List<Supplier>> getAllActiveSuppliers() {
        return supplierDao.getAllActiveSuppliers();
    }

    public LiveData<Supplier> getSupplierById(int id) {
        return supplierDao.getSupplierById(id);
    }

    public LiveData<List<Supplier>> searchSuppliers(String searchTerm) {
        return supplierDao.searchSuppliers(searchTerm);
    }

    public void insertSupplier(Supplier supplier) {
        executor.execute(() -> supplierDao.insertSupplier(supplier));
    }

    public void updateSupplier(Supplier supplier) {
        executor.execute(() -> supplierDao.updateSupplier(supplier));
    }

    public void deleteSupplier(Supplier supplier) {
        executor.execute(() -> supplierDao.deleteSupplier(supplier));
    }

    public void deactivateSupplier(int id) {
        executor.execute(() -> supplierDao.deactivateSupplier(id));
    }
}
