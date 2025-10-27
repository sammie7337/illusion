package com.flv.flowventory.ui.suppliers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.flv.flowventory.data.model.Supplier;
import com.flv.flowventory.data.repository.SupplierRepository;
import java.util.ArrayList;
import java.util.List;

public class SuppliersViewModel extends ViewModel {
    private SupplierRepository supplierRepository;
    private MutableLiveData<List<Supplier>> suppliers = new MutableLiveData<>();
    private MutableLiveData<String> searchQuery = new MutableLiveData<>();

    public SuppliersViewModel() {
        loadSuppliers();
    }

    public LiveData<List<Supplier>> getSuppliers() {
        return suppliers;
    }

    public LiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public void loadSuppliers() {
        // In a real app, this would load from the database
        List<Supplier> supplierList = new ArrayList<>();
        
        // Add some demo suppliers
        Supplier supplier1 = new Supplier();
        supplier1.setName("TechCorp Solutions");
        supplier1.setContactPerson("John Smith");
        supplier1.setEmail("john@techcorp.com");
        supplier1.setPhone("+1-555-0123");
        supplier1.setAddress("123 Tech Street");
        supplier1.setCity("San Francisco");
        supplier1.setCountry("USA");
        supplierList.add(supplier1);

        Supplier supplier2 = new Supplier();
        supplier2.setName("Office Supplies Plus");
        supplier2.setContactPerson("Sarah Johnson");
        supplier2.setEmail("sarah@officesupplies.com");
        supplier2.setPhone("+1-555-0456");
        supplier2.setAddress("456 Office Ave");
        supplier2.setCity("New York");
        supplier2.setCountry("USA");
        supplierList.add(supplier2);

        Supplier supplier3 = new Supplier();
        supplier3.setName("Global Electronics");
        supplier3.setContactPerson("Mike Chen");
        supplier3.setEmail("mike@globalelectronics.com");
        supplier3.setPhone("+1-555-0789");
        supplier3.setAddress("789 Electronics Blvd");
        supplier3.setCity("Los Angeles");
        supplier3.setCountry("USA");
        supplierList.add(supplier3);

        suppliers.setValue(supplierList);
    }

    public void searchSuppliers(String query) {
        searchQuery.setValue(query);
        // In a real app, this would search the database
        loadSuppliers(); // For demo, just reload all suppliers
    }

    public void addSupplier(Supplier supplier) {
        List<Supplier> currentSuppliers = suppliers.getValue();
        if (currentSuppliers != null) {
            currentSuppliers.add(supplier);
            suppliers.setValue(currentSuppliers);
        }
    }

    public void updateSupplier(Supplier supplier) {
        List<Supplier> currentSuppliers = suppliers.getValue();
        if (currentSuppliers != null) {
            for (int i = 0; i < currentSuppliers.size(); i++) {
                if (currentSuppliers.get(i).getId() == supplier.getId()) {
                    currentSuppliers.set(i, supplier);
                    break;
                }
            }
            suppliers.setValue(currentSuppliers);
        }
    }

    public void deleteSupplier(Supplier supplier) {
        List<Supplier> currentSuppliers = suppliers.getValue();
        if (currentSuppliers != null) {
            currentSuppliers.remove(supplier);
            suppliers.setValue(currentSuppliers);
        }
    }
}
