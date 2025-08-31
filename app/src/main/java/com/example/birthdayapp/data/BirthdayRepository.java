package com.example.birthdayapp.data;

import com.example.birthdayapp.R;
import com.example.birthdayapp.model.Person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BirthdayRepository {

    // моковые данные — положи ваши реальные сюда
    public static List<Person> getAllBirthdays() {
        return Arrays.asList(
                new Person("Мама", LocalDate.of(1970, 8, 31)),
                new Person("Папа", LocalDate.of(1966, 9, 28)),
                new Person("Сестра", LocalDate.of(1999, 9, 11)),
                new Person("Брат", LocalDate.of(1999, 9, 11)),
                new Person("Дядя1", LocalDate.of(1995, 9, 14)),
                new Person("Дядя2", LocalDate.of(1977, 9, 29)),
                new Person("Тетя1", LocalDate.of(1900, 9, 30)),
                new Person("Тетя2", LocalDate.of(1876, 10, 1))
        );
    }

    // По умолчанию ближайшие 30 дней
    public static List<Person> getUpcomingBirthdays() {
        return getUpcomingBirthdays(30);
    }

    public static List<Person> getUpcomingBirthdays(int daysWindow) {
        LocalDate today = LocalDate.now();
        return getAllBirthdays().stream()
                .filter(person -> {
                    LocalDate nextBirthday = person.getBirthDate().withYear(today.getYear());
                    if (nextBirthday.isBefore(today)) {
                        nextBirthday = nextBirthday.plusYears(1);
                    }
                    long days = ChronoUnit.DAYS.between(today, nextBirthday);
                    return days >= 0 && days <= daysWindow;
                })
                .sorted(Comparator.comparing(person -> {
                    LocalDate nextBirthday = person.getBirthDate().withYear(today.getYear());
                    if (nextBirthday.isBefore(today)) nextBirthday = nextBirthday.plusYears(1);
                    return nextBirthday;
                }))
                .collect(Collectors.toList());
    }
}