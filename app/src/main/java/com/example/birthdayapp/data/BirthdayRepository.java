package com.example.birthdayapp.data;

import com.example.birthdayapp.model.Person;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BirthdayRepository {
    public static List<Person> getAll(){
        return Arrays.asList(
                new Person("Мама", LocalDate.of(1970, 8, 31)),
                new Person("Папа", LocalDate.of(1966, 8, 28)),
                new Person("Сестра", LocalDate.of(1999, 10, 11)),
                new Person("Брат", LocalDate.of(1999, 1, 11))
        );
    }

    public static List<Person> getUpcoming() {
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();

        return getAll().stream()
                .filter(b -> {
                    int month = b.getBirth().getMonthValue();
                    return month == currentMonth || month == (currentMonth % 12) + 1;
                })
                .sorted((b1, b2) -> {
                    int m1 = b1.getBirth().getMonthValue();
                    int m2 = b2.getBirth().getMonthValue();
                    if (m1 != m2) return Integer.compare(m1, m2);
                    return Integer.compare(b1.getBirth().getDayOfMonth(), b2.getBirth().getDayOfMonth());
                })
                .collect(Collectors.toList());
    }


}
