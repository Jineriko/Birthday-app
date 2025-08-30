package com.example.birthdayapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayapp.R;

import java.time.Month;
import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MonthViewHolder> {

    public interface OnMonthClickListener {
        void onMonthClick(Month month);
    }

    private final List<Month> months;
    private final OnMonthClickListener listener;

    public MonthAdapter(List<Month> months, OnMonthClickListener listener) {
        this.months = months;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MonthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_month, parent, false);
        return new MonthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthViewHolder holder, int position) {
        Month month = months.get(position);
        holder.bind(month, listener);
    }

    @Override
    public int getItemCount() {
        return months.size();
    }

    static class MonthViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        MonthViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textMonth);
        }

        void bind(Month month, OnMonthClickListener listener) {
            textView.setText(month.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.getDefault()));
            itemView.setOnClickListener(v -> listener.onMonthClick(month));
        }
    }
}