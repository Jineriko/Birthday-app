package com.example.birthdayapp.data;

import com.example.birthdayapp.R;
import com.example.birthdayapp.model.Person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BirthdayRepository {

    private static final List<Person> people = new ArrayList<>(Arrays.asList(
            new Person("Северюхин Михаил", LocalDate.of(1967, 5, 11)),
            new Person("Северюхина Марина", LocalDate.of(1974, 10, 15)),
            new Person("Северюхин Алексей", LocalDate.of(1995, 11, 18)),
            new Person("Северюхин Александр", LocalDate.of(2005, 7, 15)),
            new Person("Северюхина Мария", LocalDate.of(1998, 6, 21)),
            new Person("Северюхина София", LocalDate.of(2009, 2, 21)),
            new Person("Северюхин Владимир", LocalDate.of(1962, 10, 8)),
            new Person("Тимошка", LocalDate.of(2020, 10, 7)),
            new Person("Жуйков Валерий", LocalDate.of(1950, 12, 2)),
            new Person("Пестова Анна", LocalDate.of(1988, 6, 4)),
            new Person("Тарасов Вячеслав", LocalDate.of(1995, 11, 17)),
            new Person("Калинина Ксения", LocalDate.of(1996, 3, 19)),
            new Person("Тестовый Черт", LocalDate.of(1996, 9, 19))
    ));

    public static List<Person> getAllBirthdays() {
        // Возвращаем копию, чтобы внешний код не мог изменить наш список напрямую
        return new ArrayList<>(people);
    }

    public static void addPerson(Person person) {
        people.add(person);
    }

    public static List<Person> getUpcomingBirthdays() {
        return getUpcomingBirthdays(30);
    }

    public static List<Person> getUpcomingBirthdays(int daysWindow) {
        LocalDate today = LocalDate.now();
        return people.stream()
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