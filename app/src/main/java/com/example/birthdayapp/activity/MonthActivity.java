package com.example.birthdayapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayapp.R;
import com.example.birthdayapp.adapter.MonthAdapter;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MonthActivity extends AppCompatActivity implements MonthAdapter.OnMonthClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMonths);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Month> months = Arrays.asList(Month.values());
        MonthAdapter adapter = new MonthAdapter(months, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onMonthClick(Month month) {
        Intent intent = new Intent(this, BirthdayListActivity.class);
        intent.putExtra("month", month.getValue()); // передаём номер месяца
        startActivity(intent);
    }
}