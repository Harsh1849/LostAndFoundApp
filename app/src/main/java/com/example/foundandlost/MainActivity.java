package com.example.foundandlost;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foundandlost.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCreateAdvert.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CreateAdvertActivity.class)));

        binding.btnShowItems.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListItemsActivity.class)));
    }
}
