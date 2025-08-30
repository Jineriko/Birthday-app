package com.example.birthdayapp.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.birthdayapp.R;
import com.example.birthdayapp.model.Person;
import com.example.birthdayapp.data.BirthdayRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BirthdayListActivity extends AppCompatActivity {

    private TextView birthdayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        birthdayList = findViewById(R.id.birthdayList);

        int monthValue = getIntent().getIntExtra("month", -1);
        if (monthValue != -1) {
            showBirthdaysForMonth(monthValue);
        }
    }

    private void showBirthdaysForMonth(int month) {
        List<Person> people = BirthdayRepository.getAll().stream()
                .filter(b -> b.getBirth().getMonthValue() == month)
                .sorted((a, b) -> Integer.compare(a.getBirth().getDayOfMonth(), b.getBirth().getDayOfMonth()))
                .collect(Collectors.toList());

        if (people.isEmpty()) {
            birthdayList.setText("В этом месяце нет дней рождения");
        } else {
            StringBuilder sb = new StringBuilder("Дни рождения:\n");
            for (Person b : people) {
                sb.append(b.getName())
                        .append(" — ")
                        .append(String.format("%02d.%02d", b.getBirth().getDayOfMonth(), b.getBirth().getMonthValue()))
                        .append("\n");
            }
            birthdayList.setText(sb.toString());
        }
    }
}