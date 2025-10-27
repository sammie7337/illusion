package com.flv.flowventory.ui.customers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.flv.flowventory.data.model.Customer;
import com.flv.flowventory.data.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;

public class CustomersViewModel extends ViewModel {
    private CustomerRepository customerRepository;
    private MutableLiveData<List<Customer>> customers = new MutableLiveData<>();
    private MutableLiveData<String> searchQuery = new MutableLiveData<>();

    public CustomersViewModel() {
        loadCustomers();
    }

    public LiveData<List<Customer>> getCustomers() {
        return customers;
    }

    public LiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public void loadCustomers() {
        // In a real app, this would load from the database
        List<Customer> customerList = new ArrayList<>();
        
        // Add some demo customers
        Customer customer1 = new Customer();
        customer1.setName("ABC Corporation");
        customer1.setEmail("contact@abccorp.com");
        customer1.setPhone("+1-555-1000");
        customer1.setAddress("100 Business Ave");
        customer1.setCity("Chicago");
        customer1.setCountry("USA");
        customer1.setCustomerType("business");
        customerList.add(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Jane Doe");
        customer2.setEmail("jane.doe@email.com");
        customer2.setPhone("+1-555-2000");
        customer2.setAddress("200 Residential St");
        customer2.setCity("Boston");
        customer2.setCountry("USA");
        customer2.setCustomerType("individual");
        customerList.add(customer2);

        Customer customer3 = new Customer();
        customer3.setName("XYZ Enterprises");
        customer3.setEmail("info@xyzent.com");
        customer3.setPhone("+1-555-3000");
        customer3.setAddress("300 Enterprise Blvd");
        customer3.setCity("Seattle");
        customer3.setCountry("USA");
        customer3.setCustomerType("business");
        customerList.add(customer3);

        customers.setValue(customerList);
    }

    public void searchCustomers(String query) {
        searchQuery.setValue(query);
        // In a real app, this would search the database
        loadCustomers(); // For demo, just reload all customers
    }

    public void addCustomer(Customer customer) {
        List<Customer> currentCustomers = customers.getValue();
        if (currentCustomers != null) {
            currentCustomers.add(customer);
            customers.setValue(currentCustomers);
        }
    }

    public void updateCustomer(Customer customer) {
        List<Customer> currentCustomers = customers.getValue();
        if (currentCustomers != null) {
            for (int i = 0; i < currentCustomers.size(); i++) {
                if (currentCustomers.get(i).getId() == customer.getId()) {
                    currentCustomers.set(i, customer);
                    break;
                }
            }
            customers.setValue(currentCustomers);
        }
    }

    public void deleteCustomer(Customer customer) {
        List<Customer> currentCustomers = customers.getValue();
        if (currentCustomers != null) {
            currentCustomers.remove(customer);
            customers.setValue(currentCustomers);
        }
    }
}
