package ru.example.onlineshopnew.model.account;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.example.onlineshopnew.model.buy.Basket;
import ru.example.onlineshopnew.model.buy.Order;
import ru.example.onlineshopnew.model.person.Person;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "accounts")
public class Account {

    private int id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 3, max = 15, message = "Поле должно быть от 3 до 15 символов")
    private String login;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 3, message = "Поле должно содержать минимум 3 символа")
    private String password;
    private Role role;
    private Person person;

    private List<Basket> basket;

    private List<Order> orders;

    public Account(int id, String login, String password, Role role, Person person, List<Basket> basket, List<Order> orders) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.person = person;
        this.basket = basket;
        this.orders = orders;
    }

    public Account() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account that = (Account) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "Логин: " + getLogin() + " , " + "Пароль: " + getPassword() +
                " , " + "Статус:" + getRole();
    }

    @Column(name = "login", nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Basket> getBasket() {
        return basket;
    }

    public void setBasket(List<Basket> basket) {
        this.basket = basket;
    }

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}

