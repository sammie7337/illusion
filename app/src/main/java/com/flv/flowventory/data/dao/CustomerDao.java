package com.flv.flowventory.data.dao;

import androidx.room.*;
import androidx.lifecycle.LiveData;
import com.flv.flowventory.data.model.Customer;
import java.util.List;

@Dao
public interface CustomerDao {
    
    @Query("SELECT * FROM customers WHERE isActive = 1")
    LiveData<List<Customer>> getAllActiveCustomers();
    
    @Query("SELECT * FROM customers WHERE id = :id")
    LiveData<Customer> getCustomerById(int id);
    
    @Query("SELECT * FROM customers WHERE name LIKE '%' || :searchTerm || '%' AND isActive = 1")
    LiveData<List<Customer>> searchCustomers(String searchTerm);
    
    @Query("SELECT * FROM customers WHERE customerType = :type AND isActive = 1")
    LiveData<List<Customer>> getCustomersByType(String type);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCustomer(Customer customer);
    
    @Update
    void updateCustomer(Customer customer);
    
    @Delete
    void deleteCustomer(Customer customer);
    
    @Query("UPDATE customers SET isActive = 0 WHERE id = :id")
    void deactivateCustomer(int id);
}
