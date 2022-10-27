package ru.example.onlineshopnew.model.buy;

public enum Pay {

    PAID("Оплачен"),

    NOT_PAID("Не оплачен");

    private final String name;

    Pay(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
