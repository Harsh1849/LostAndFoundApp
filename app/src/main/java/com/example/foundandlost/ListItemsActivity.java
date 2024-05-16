package com.example.foundandlost;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foundandlost.databinding.ActivityListItemsBinding;

import java.util.ArrayList;

public class ListItemsActivity extends AppCompatActivity {

    private ActivityListItemsBinding binding;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new DatabaseHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItems();
    }

    private void loadItems() {
        ArrayList<Item> items = db.getAllItems();
        if (items.isEmpty()) {
            binding.noDataFound.setVisibility(View.VISIBLE);
            binding.foundRecyclerView.setVisibility(View.GONE);
        } else {
            binding.noDataFound.setVisibility(View.GONE);
            binding.foundRecyclerView.setVisibility(View.VISIBLE);
            binding.foundRecyclerView.setAdapter(new FoundListAdapter(this, items));
        }
    }
}
