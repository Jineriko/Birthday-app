package com.example.birthdayapp.data;

import com.example.birthdayapp.R;
import com.example.birthdayapp.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BirthdayRepository {

    private final List<Person> people = new ArrayList<>();
    public BirthdayRepository() {
        // Пример данных
        people.add(new Person("Иван Иванов", LocalDate.of(1990, 9, 15)));  // сентябрь
        people.add(new Person("Петр Петров", LocalDate.of(1985, 10, 5)));  // октябрь
        people.add(new Person("Анна Смирнова", LocalDate.of(2000, 12, 25))); // декабрь
        people.add(new Person("Мария Кузнецова", LocalDate.of(1995, 3, 3))); // март
    }

    public List<Person> getAllPeople() {
        return people;
    }

    // Получить только тех, у кого ДР в ближайший месяц
    public List<Person> getUpcomingBirthdays() {
        LocalDate now = LocalDate.now();
        LocalDate oneMonthLater = now.plusMonths(1);

        return people.stream()
                .filter(person -> {
                    LocalDate birthdayThisYear = person.getBirthDate()
                            .withYear(now.getYear());

                    // Если ДР уже прошел в этом году -> переносим на след. год
                    if (birthdayThisYear.isBefore(now)) {
                        birthdayThisYear = birthdayThisYear.plusYears(1);
                    }

                    return !birthdayThisYear.isAfter(oneMonthLater);
                })
                .collect(Collectors.toList());
    }
}