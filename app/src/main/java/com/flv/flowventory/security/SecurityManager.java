package com.flv.flowventory.security;

import android.content.Context;
import com.flv.flowventory.auth.AuthManager;

public class SecurityManager {
    private AuthManager authManager;
    private Context context;

    public SecurityManager(Context context, AuthManager authManager) {
        this.context = context;
        this.authManager = authManager;
    }

    public boolean hasPermission(String permission) {
        if (!authManager.isLoggedIn()) {
            return false;
        }

        String userRole = authManager.getCurrentUserRole();
        
        switch (permission) {
            case "ADMIN_ACCESS":
                return "admin".equals(userRole);
            case "PRODUCT_MANAGEMENT":
                return "admin".equals(userRole) || "staff".equals(userRole);
            case "STOCK_MANAGEMENT":
                return "admin".equals(userRole) || "staff".equals(userRole);
            case "SUPPLIER_MANAGEMENT":
                return "admin".equals(userRole) || "staff".equals(userRole);
            case "CUSTOMER_MANAGEMENT":
                return "admin".equals(userRole) || "staff".equals(userRole);
            case "REPORTS_ACCESS":
                return "admin".equals(userRole) || "staff".equals(userRole);
            case "USER_MANAGEMENT":
                return "admin".equals(userRole);
            case "SETTINGS_ACCESS":
                return "admin".equals(userRole) || "staff".equals(userRole);
            default:
                return false;
        }
    }

    public boolean canAddProduct() {
        return hasPermission("PRODUCT_MANAGEMENT");
    }

    public boolean canEditProduct() {
        return hasPermission("PRODUCT_MANAGEMENT");
    }

    public boolean canDeleteProduct() {
        return hasPermission("PRODUCT_MANAGEMENT");
    }

    public boolean canManageStock() {
        return hasPermission("STOCK_MANAGEMENT");
    }

    public boolean canManageSuppliers() {
        return hasPermission("SUPPLIER_MANAGEMENT");
    }

    public boolean canManageCustomers() {
        return hasPermission("CUSTOMER_MANAGEMENT");
    }

    public boolean canViewReports() {
        return hasPermission("REPORTS_ACCESS");
    }

    public boolean canManageUsers() {
        return hasPermission("USER_MANAGEMENT");
    }

    public boolean canAccessSettings() {
        return hasPermission("SETTINGS_ACCESS");
    }

    public String getUserRole() {
        return authManager.getCurrentUserRole();
    }

    public boolean isAdmin() {
        return "admin".equals(getUserRole());
    }

    public boolean isStaff() {
        return "staff".equals(getUserRole());
    }
}
