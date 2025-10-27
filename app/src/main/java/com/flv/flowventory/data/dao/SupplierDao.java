package com.flv.flowventory.data.dao;

import androidx.room.*;
import androidx.lifecycle.LiveData;
import com.flv.flowventory.data.model.Supplier;
import java.util.List;

@Dao
public interface SupplierDao {
    
    @Query("SELECT * FROM suppliers WHERE isActive = 1")
    LiveData<List<Supplier>> getAllActiveSuppliers();
    
    @Query("SELECT * FROM suppliers WHERE id = :id")
    LiveData<Supplier> getSupplierById(int id);
    
    @Query("SELECT * FROM suppliers WHERE name LIKE '%' || :searchTerm || '%' AND isActive = 1")
    LiveData<List<Supplier>> searchSuppliers(String searchTerm);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSupplier(Supplier supplier);
    
    @Update
    void updateSupplier(Supplier supplier);
    
    @Delete
    void deleteSupplier(Supplier supplier);
    
    @Query("UPDATE suppliers SET isActive = 0 WHERE id = :id")
    void deactivateSupplier(int id);
}
