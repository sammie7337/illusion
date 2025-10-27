package com.flv.flowventory.ui.customers;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.flv.flowventory.data.model.Customer;
import com.flv.flowventory.databinding.DialogAddCustomerBinding;

public class AddCustomerDialogFragment extends DialogFragment {
    private DialogAddCustomerBinding binding;
    private OnCustomerAddedListener listener;

    public interface OnCustomerAddedListener {
        void onCustomerAdded(Customer customer);
    }

    public void setOnCustomerAddedListener(OnCustomerAddedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogAddCustomerBinding.inflate(getLayoutInflater());
        
        setupClickListeners();
        
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        
        return dialog;
    }

    private void setupClickListeners() {
        binding.btnSave.setOnClickListener(v -> saveCustomer());
        binding.btnCancel.setOnClickListener(v -> dismiss());
    }

    private void saveCustomer() {
        String name = binding.etCustomerName.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String phone = binding.etPhone.getText().toString().trim();
        String address = binding.etAddress.getText().toString().trim();
        String city = binding.etCity.getText().toString().trim();
        String country = binding.etCountry.getText().toString().trim();
        String customerType = binding.spCustomerType.getSelectedItem().toString().toLowerCase();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setCountry(country);
        customer.setCustomerType(customerType);

        if (listener != null) {
            listener.onCustomerAdded(customer);
        }

        dismiss();
    }
}
