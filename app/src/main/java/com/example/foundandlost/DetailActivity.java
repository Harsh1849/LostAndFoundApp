package com.example.foundandlost;

import static com.example.foundandlost.Extensions.ItemDATA;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foundandlost.databinding.ActivityDetailBinding;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        if (getIntent() != null) {
            Item item = new Gson().fromJson(getIntent().getStringExtra(ItemDATA), Item.class);
            setUpData(item);
        }
    }

    public void setUpData(Item item) {
        binding.titleTextView.setText(item.getStatus() + " : " + item.getName());
        binding.dateNameTextView.setText(item.getDate());
        binding.decsTextView.setText(item.getDescription());
        binding.phoneDecsTextView.setText(item.getPhone());
        binding.locationDecsTextView.setText(item.getLocation());

        binding.btnRemove.setOnClickListener(v -> {
            DatabaseHelper db = new DatabaseHelper(this);
            db.deleteItem(item.getId());
            finish();
        });
    }
}