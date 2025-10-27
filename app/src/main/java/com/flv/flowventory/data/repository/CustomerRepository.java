package com.flv.flowventory.data.repository;

import androidx.lifecycle.LiveData;
import com.flv.flowventory.data.dao.CustomerDao;
import com.flv.flowventory.data.model.Customer;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerRepository {
    private CustomerDao customerDao;
    private ExecutorService executor;

    public CustomerRepository(CustomerDao customerDao) {
        this.customerDao = customerDao;
        this.executor = Executors.newFixedThreadPool(4);
    }

    public LiveData<List<Customer>> getAllActiveCustomers() {
        return customerDao.getAllActiveCustomers();
    }

    public LiveData<Customer> getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }

    public LiveData<List<Customer>> searchCustomers(String searchTerm) {
        return customerDao.searchCustomers(searchTerm);
    }

    public LiveData<List<Customer>> getCustomersByType(String type) {
        return customerDao.getCustomersByType(type);
    }

    public void insertCustomer(Customer customer) {
        executor.execute(() -> customerDao.insertCustomer(customer));
    }

    public void updateCustomer(Customer customer) {
        executor.execute(() -> customerDao.updateCustomer(customer));
    }

    public void deleteCustomer(Customer customer) {
        executor.execute(() -> customerDao.deleteCustomer(customer));
    }

    public void deactivateCustomer(int id) {
        executor.execute(() -> customerDao.deactivateCustomer(id));
    }
}
