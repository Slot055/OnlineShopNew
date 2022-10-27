package ru.example.onlineshopnew.model.person;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;
import ru.example.onlineshopnew.model.account.Account;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "persons")
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private int id;

    @Size(min = 2, max = 15, message = "Поле должно быть от 3 до 15 символов")
    private String name;
    @Size(min = 2, max = 15, message = "Поле должно быть от 3 до 15 символов")
    private String lastName;

    private Gender gender;

    @Range(min = 0, max = 150, message = "Ваш возраст выходит за пределы корректных значений")
    private int age;

    @Pattern(regexp = "(^$|\\d{10})", message = "Номер телефона должен содержать 10 цифр без кода страны")
    private String phoneNumber;

    @Email(message = "Некорректный адрес электронной почты")
    private String email;

    private Account account;

    public Person(int id, String name, String lastName, Gender gender, int age, String phoneNumber, String email, Account account) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.account = account;
    }

    public Person() {

    }

    @Override
    public String toString() {
        return "Номер клиента: " + getId() + " , " + "Имя: " + getName() + " , " + "Фамилия: " + getLastName() + " , " +
                "Пол: " + getGender().getName() + " , " + "Возраст" + getAge() + " , " + "Номер телефона: " + getPhoneNumber() + " , " + "email: " + getEmail();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "phone_number", unique = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne(mappedBy = "person")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
