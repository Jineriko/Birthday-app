package com.example.birthdayapp.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
    private final String name;
    private final LocalDate birthDate;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    // строка "дд.мм.гггг"
    public String getDateString() {
        return birthDate.format(FORMAT);
    }

    // сколько исполнится в этом году (если ДР уже прошёл — возвращает текущий возраст)
    public int getUpcomingAge() {
        LocalDate now = LocalDate.now();
        int currentYears = Period.between(birthDate, now).getYears();
        LocalDate bThisYear = birthDate.withYear(now.getYear());
        // если ДР ещё не был в этом году — скоро исполнится currentYears + 1
        if (bThisYear.isAfter(now)) {
            return currentYears + 1;
        } else {
            return currentYears;
        }
    }
}