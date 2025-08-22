package com.example.birthdayapp.activity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // кнопка "назад" в тулбаре
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        calendarView = findViewById(R.id.calendarView);
        birthdayList = findViewById(R.id.birthdayList);

        // сразу показываем текущий месяц
        int currentMonth = LocalDate.now().getMonthValue();
        showBirthdaysForMonth(currentMonth);

        // реагируем на выбор даты (месяц 0-based у CalendarView)
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            int selectedMonth = month + 1;
            showBirthdaysForMonth(selectedMonth);
        });
    }

    private void showBirthdaysForMonth(int month) {
        List<Birthday> birthdays = BirthdayRepository.getAll().stream()
                .filter(b -> b.getBirth().getMonthValue() == month)
                .sorted((a, b) -> Integer.compare(a.getBirth().getDayOfMonth(), b.getBirth().getDayOfMonth()))
                .collect(Collectors.toList());

        if (birthdays.isEmpty()) {
            birthdayList.setText("В этом месяце нет дней рождения");
        } else {
            StringBuilder sb = new StringBuilder("Дни рождения:\n");
            for (Birthday b : birthdays) {
                sb.append(b.getName())
                        .append(" — ")
                        .append(String.format("%02d.%02d", b.getBirth().getDayOfMonth(), b.getBirth().getMonthValue()))
                        .append("\n");
            }
            birthdayList.setText(sb.toString());
        }
    }
}
