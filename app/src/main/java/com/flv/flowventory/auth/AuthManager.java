package com.flv.flowventory.auth;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.flv.flowventory.data.model.User;
import com.flv.flowventory.data.repository.UserRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthManager {
    private static final String PREFS_NAME = "auth_prefs";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ROLE = "role";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    
    private Context context;
    private UserRepository userRepository;
    private SharedPreferences encryptedPrefs;
    private User currentUser;

    public AuthManager(Context context, UserRepository userRepository) {
        this.context = context;
        this.userRepository = userRepository;
        initializeEncryptedPrefs();
    }

    private void initializeEncryptedPrefs() {
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            encryptedPrefs = EncryptedSharedPreferences.create(
                    context,
                    PREFS_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback to regular SharedPreferences
            encryptedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }
    }

    public boolean login(String username, String password) {
        // In a real app, you would hash the password and compare with stored hash
        // For now, we'll use a simple approach
        String hashedPassword = hashPassword(password);
        
        // Check if user exists and password matches
        // This would typically be done through the repository
        // For now, we'll simulate a successful login
        if (isValidCredentials(username, hashedPassword)) {
            saveLoginState(username, "admin"); // Default role for demo
            return true;
        }
        return false;
    }

    public void logout() {
        encryptedPrefs.edit()
                .putBoolean(KEY_IS_LOGGED_IN, false)
                .remove(KEY_USER_ID)
                .remove(KEY_USERNAME)
                .remove(KEY_ROLE)
                .apply();
        currentUser = null;
    }

    public boolean isLoggedIn() {
        return encryptedPrefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public User getCurrentUser() {
        if (currentUser == null && isLoggedIn()) {
            // Load user from database based on stored ID
            String username = encryptedPrefs.getString(KEY_USERNAME, "");
            // In a real app, you would fetch the user from the database
            // For now, create a mock user
            currentUser = new User();
            currentUser.setUsername(username);
            currentUser.setRole(encryptedPrefs.getString(KEY_ROLE, "staff"));
        }
        return currentUser;
    }

    public String getCurrentUserRole() {
        return encryptedPrefs.getString(KEY_ROLE, "staff");
    }

    public boolean hasAdminRole() {
        return "admin".equals(getCurrentUserRole());
    }

    private void saveLoginState(String username, String role) {
        encryptedPrefs.edit()
                .putBoolean(KEY_IS_LOGGED_IN, true)
                .putString(KEY_USERNAME, username)
                .putString(KEY_ROLE, role)
                .apply();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return password; // Fallback to plain text (not recommended for production)
        }
    }

    private boolean isValidCredentials(String username, String hashedPassword) {
        // In a real app, you would check against the database
        // For demo purposes, accept any non-empty credentials
        return !username.isEmpty() && !hashedPassword.isEmpty();
    }
}
