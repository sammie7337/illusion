package com.flv.flowventory.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.flv.flowventory.MainActivity;
import com.flv.flowventory.R;
import com.flv.flowventory.auth.AuthManager;
import com.flv.flowventory.data.database.InventoryDatabase;
import com.flv.flowventory.data.repository.UserRepository;
import com.flv.flowventory.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize AuthManager
        InventoryDatabase database = InventoryDatabase.getDatabase(this);
        UserRepository userRepository = new UserRepository(database.userDao());
        authManager = new AuthManager(this, userRepository);

        // Check if user is already logged in
        if (authManager.isLoggedIn()) {
            startMainActivity();
            return;
        }

        setupClickListeners();
    }

    private void setupClickListeners() {
        binding.btnLogin.setOnClickListener(v -> attemptLogin());
        binding.btnRegister.setOnClickListener(v -> showRegisterDialog());
    }

    private void attemptLogin() {
        String username = binding.etUsername.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.btnLogin.setEnabled(false);

        // Simulate login process
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Simulate network delay
                
                runOnUiThread(() -> {
                    if (authManager.login(username, password)) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                        startMainActivity();
                    } else {
                        Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        binding.progressBar.setVisibility(View.GONE);
                        binding.btnLogin.setEnabled(true);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.btnLogin.setEnabled(true);
                });
            }
        }).start();
    }

    private void showRegisterDialog() {
        // For demo purposes, create a simple registration
        String username = binding.etUsername.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // In a real app, you would create a new user in the database
        Toast.makeText(this, "Registration feature coming soon", Toast.LENGTH_SHORT).show();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
