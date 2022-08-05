package ru.example.onlineshopnew.model.client;

import java.io.Serial;
import java.io.Serializable;

public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String lastName;
    private String gender;
    private String age;
    private String phoneNumber;
    private String email;

    public Client(String name, String lastName, String gender, String age, String phoneNumber, String email) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }

    public Client() {

    }

    @Override
    public String toString() {
        return "Имя:" + getName() + " , " + "Фамилия:" + getLastName() + " , " +
                "Пол:" + getGender() + " , " + "Возраст:" + getAge() +
                " , " + "Номер телефона:" + getPhoneNumber() + " , " + "Email:" + getEmail();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}