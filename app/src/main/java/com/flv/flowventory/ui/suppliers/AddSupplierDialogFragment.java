package com.flv.flowventory.ui.suppliers;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.flv.flowventory.data.model.Supplier;
import com.flv.flowventory.databinding.DialogAddSupplierBinding;

public class AddSupplierDialogFragment extends DialogFragment {
    private DialogAddSupplierBinding binding;
    private OnSupplierAddedListener listener;

    public interface OnSupplierAddedListener {
        void onSupplierAdded(Supplier supplier);
    }

    public void setOnSupplierAddedListener(OnSupplierAddedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogAddSupplierBinding.inflate(getLayoutInflater());
        
        setupClickListeners();
        
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        
        return dialog;
    }

    private void setupClickListeners() {
        binding.btnSave.setOnClickListener(v -> saveSupplier());
        binding.btnCancel.setOnClickListener(v -> dismiss());
    }

    private void saveSupplier() {
        String name = binding.etSupplierName.getText().toString().trim();
        String contactPerson = binding.etContactPerson.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String phone = binding.etPhone.getText().toString().trim();
        String address = binding.etAddress.getText().toString().trim();
        String city = binding.etCity.getText().toString().trim();
        String country = binding.etCountry.getText().toString().trim();

        if (name.isEmpty() || contactPerson.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Supplier supplier = new Supplier();
        supplier.setName(name);
        supplier.setContactPerson(contactPerson);
        supplier.setEmail(email);
        supplier.setPhone(phone);
        supplier.setAddress(address);
        supplier.setCity(city);
        supplier.setCountry(country);

        if (listener != null) {
            listener.onSupplierAdded(supplier);
        }

        dismiss();
    }
}
