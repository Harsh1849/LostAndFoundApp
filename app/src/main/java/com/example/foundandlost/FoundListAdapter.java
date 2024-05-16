package com.example.foundandlost;

import static com.example.foundandlost.Extensions.ItemDATA;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class FoundListAdapter extends RecyclerView.Adapter<FoundListAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Item> arrayList;

    public FoundListAdapter(Context context, ArrayList<Item> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_found, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTextView.setText(arrayList.get(position).getStatus() + " : " + arrayList.get(position).getName());
        holder.decsTextView.setText(context.getString(R.string.description) + " : " + arrayList.get(position).getDescription());
        holder.locationTextView.setText(context.getString(R.string.location) + " : " + arrayList.get(position).getLocation());
        holder.mainCardView.setOnClickListener(v -> context.startActivity(new Intent(context, DetailActivity.class).putExtra(ItemDATA, new Gson().toJson(arrayList.get(position), Item.class))));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, decsTextView, locationTextView;
        MaterialCardView mainCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mainCardView = itemView.findViewById(R.id.main_cardView);
            titleTextView = itemView.findViewById(R.id.title_textView);
            decsTextView = itemView.findViewById(R.id.decs_textView);
            locationTextView = itemView.findViewById(R.id.location_textView);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}