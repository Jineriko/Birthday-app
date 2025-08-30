package com.example.birthdayapp.model;

import java.time.LocalDate;

public class Person {
    private String name;
    private LocalDate birth;

    public Person(String name, LocalDate birth) {
        this.name = name;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }
}
