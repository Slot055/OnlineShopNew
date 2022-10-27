package ru.example.onlineshopnew.model.buy;

public enum StatusOrder {

    ACTIVE("Активный"),

    PURCHASED("Выкуплен"),

    CANCELLED("Отменён");

    private final String name;

    StatusOrder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
