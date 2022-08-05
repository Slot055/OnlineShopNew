package ru.example.onlineshopnew.service;

import java.util.ArrayList;

public interface Service {

    ArrayList<?> read();

    Object readOne(int id);

    void create(Object object);

    boolean update(Object object, int id);


}
