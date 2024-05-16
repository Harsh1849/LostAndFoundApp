package com.example.foundandlost;

import static com.example.foundandlost.Extensions.checkEmptyString;
import static com.example.foundandlost.Extensions.clearError;
import static com.example.foundandlost.Extensions.hideKeyboard;
import static com.example.foundandlost.Extensions.showError;
import static com.example.foundandlost.Extensions.showToast;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foundandlost.databinding.ActivityCreateAdvertBinding;

import java.util.Objects;

public class CreateAdvertActivity extends AppCompatActivity {
    private ActivityCreateAdvertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAdvertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpView();
        manageClicks();
    }

    private void setUpView() {
        binding.fullNameTiet.addTextChangedListener(new ClearErrorTextWatcher(binding.fullNameTil));
        binding.phoneTiet.addTextChangedListener(new ClearErrorTextWatcher(binding.phoneTil));
        binding.descriptionTiet.addTextChangedListener(new ClearErrorTextWatcher(binding.descriptionTil));
        binding.dateTiet.addTextChangedListener(new ClearErrorTextWatcher(binding.dateTil));
        binding.locationTiet.addTextChangedListener(new ClearErrorTextWatcher(binding.locationTil));
    }

    private void manageClicks() {

        binding.btnSave.setOnClickListener(v -> {
            if (!isValid()) return;

            hideKeyboard(this, binding.getRoot());

            String name = Objects.requireNonNull(binding.fullNameTiet.getText()).toString();
            String phone = Objects.requireNonNull(binding.phoneTiet.getText()).toString();
            String description = Objects.requireNonNull(binding.descriptionTiet.getText()).toString();
            String date = Objects.requireNonNull(binding.dateTiet.getText()).toString();
            String location = Objects.requireNonNull(binding.locationTiet.getText()).toString();
            String status = binding.radioLost.isChecked() ? "Lost" : "Found";

            Item newItem = new Item(name, phone, description, date, location, status);
            DatabaseHelper db = new DatabaseHelper(CreateAdvertActivity.this);
            db.addItem(newItem);
            // Confirm data has been saved and clear form or show a message
            showToast(this, "Item saved successfully");
            finish();
        });
    }

    private boolean isValid() {
        if (checkEmptyString(binding.fullNameTiet.getText())) {
            showError(binding.fullNameTil, getString(R.string.please_enter_username));
            return false;
        } else {
            clearError(binding.fullNameTil);
        }
        if (checkEmptyString(binding.phoneTiet.getText())) {
            showError(binding.phoneTil, getString(R.string.enter_number));
            return false;
        } else {
            clearError(binding.phoneTil);
        }
        if (binding.phoneTiet.getText().length() < 6) {
            showError(binding.phoneTil, getString(R.string.enter_valid_number));
            return false;
        } else {
            clearError(binding.phoneTil);
        }
        if (checkEmptyString(binding.descriptionTiet.getText())) {
            showError(binding.descriptionTil, getString(R.string.enter_description));
            return false;
        } else {
            clearError(binding.descriptionTil);
        }
        if (checkEmptyString(binding.dateTiet.getText())) {
            showError(binding.dateTil, getString(R.string.enter_date));
            return false;
        } else {
            clearError(binding.dateTil);
        }
        if (checkEmptyString(binding.locationTiet.getText())) {
            showError(binding.locationTil, getString(R.string.enter_location));
            return false;
        } else {
            clearError(binding.locationTil);
        }
        return true;
    }

}
