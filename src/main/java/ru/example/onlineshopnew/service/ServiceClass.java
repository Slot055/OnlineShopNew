package ru.example.onlineshopnew.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ServiceClass {

    ArrayList<?> read();

    Object readOne(int id);

    Object create(Object object);

    boolean update(Object object, int id);

    boolean delete(int id);

    Optional<?> loadObjectByParameter(String...parameter);

    List<?> loadObjectsByParameter(String...parameter);

}
