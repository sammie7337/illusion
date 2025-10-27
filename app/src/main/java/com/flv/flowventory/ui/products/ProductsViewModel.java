package com.flv.flowventory.ui.products;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.flv.flowventory.data.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductsViewModel extends ViewModel {
    private MutableLiveData<List<Product>> products = new MutableLiveData<>();
    private MutableLiveData<List<String>> categories = new MutableLiveData<>();

    public ProductsViewModel() {
        loadProducts();
        loadCategories();
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public LiveData<List<String>> getCategories() {
        return categories;
    }

    public void loadProducts() {
        // In a real app, this would load from the database
        List<Product> productList = new ArrayList<>();
        
        // Add some demo products
        Product product1 = new Product();
        product1.setName("Laptop XYZ");
        product1.setCategory("Electronics");
        product1.setPrice(999.99);
        product1.setCurrentStock(15);
        product1.setMinStockLevel(5);
        productList.add(product1);

        Product product2 = new Product();
        product2.setName("Mouse ABC");
        product2.setCategory("Electronics");
        product2.setPrice(25.99);
        product2.setCurrentStock(2);
        product2.setMinStockLevel(10);
        productList.add(product2);

        Product product3 = new Product();
        product3.setName("Office Chair");
        product3.setCategory("Furniture");
        product3.setPrice(199.99);
        product3.setCurrentStock(8);
        product3.setMinStockLevel(3);
        productList.add(product3);

        products.setValue(productList);
    }

    public void loadCategories() {
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Electronics");
        categoryList.add("Furniture");
        categoryList.add("Office Supplies");
        categoryList.add("Books");
        categories.setValue(categoryList);
    }

    public void searchProducts(String searchTerm) {
        // In a real app, this would search the database
        loadProducts(); // For demo, just reload all products
    }

    public void addProduct(Product product) {
        List<Product> currentProducts = products.getValue();
        if (currentProducts != null) {
            currentProducts.add(product);
            products.setValue(currentProducts);
        }
    }

    public void updateProduct(Product product) {
        List<Product> currentProducts = products.getValue();
        if (currentProducts != null) {
            for (int i = 0; i < currentProducts.size(); i++) {
                if (currentProducts.get(i).getId() == product.getId()) {
                    currentProducts.set(i, product);
                    break;
                }
            }
            products.setValue(currentProducts);
        }
    }

    public void deleteProduct(Product product) {
        List<Product> currentProducts = products.getValue();
        if (currentProducts != null) {
            currentProducts.remove(product);
            products.setValue(currentProducts);
        }
    }
}
