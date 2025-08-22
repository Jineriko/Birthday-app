package com.example.birthdayapp.activity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.birthdayapp.R;
import com.example.birthdayapp.model.Birthday;
import com.example.birthdayapp.repository.BirthdayRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView birthdayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        birthdayList = findViewById(R.id.birthdayList);

        // обработчик выбора даты
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            int selectedMonth = month + 1; // CalendarView считает месяцы с 0
            showBirthdaysForMonth(selectedMonth);
        });
    }

    private void showBirthdaysForMonth(int month) {
        List<Birthday> all = BirthdayRepository.getAll();

        // фильтруем по месяцу
        List<Birthday> birthdays = all.stream()
                .filter(b -> b.getBirth().getMonthValue() == month)
                .collect(Collectors.toList());

        if (birthdays.isEmpty()) {
            birthdayList.setText("В этом месяце нет дней рождения");
        } else {
            StringBuilder sb = new StringBuilder("Дни рождения:\n");
            for (Birthday b : birthdays) {
                sb.append(b.getName())
                        .append(" — ")
                        .append(b.getBirth().getDayOfMonth())
                        .append(".")
                        .append(b.getBirth().getMonthValue())
                        .append("\n");
            }
            birthdayList.setText(sb.toString());
        }
    }
}