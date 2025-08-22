package com.example.birthdayapp.model;

import java.time.LocalDate;

public class Birthday {
    private String name;
    private LocalDate birth;

    public Birthday(String name, LocalDate birth) {
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
