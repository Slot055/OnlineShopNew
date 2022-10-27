package ru.example.onlineshopnew.model.person;

public enum Gender {

    MALE("Мужской"),
    FEMALE("Женский");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
