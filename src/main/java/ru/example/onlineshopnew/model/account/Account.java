package ru.example.onlineshopnew.model.client;
import java.util.Objects;

public class ClientAccount {

    private int idAccount;
    private String login;
    private String password;
    private StatusAccount statusAccount;
    private Client client;

    private CashAccount cashAccount;

    public ClientAccount(int idAccount, String login, String password, StatusAccount statusAccount, Client client) {
        this.idAccount = idAccount;
        this.login = login;
        this.password = password;
        this.statusAccount = statusAccount;
        this.client = client;
    }

    public ClientAccount(String login, String password, StatusAccount statusAccount, Client client) {
        this.login = login;
        this.password = password;
        this.statusAccount = statusAccount;
        this.client = client;
    }

    public ClientAccount(String login, String password, Client client) {
        this.login = login;
        this.password = password;
        this.client = client;
    }

    public ClientAccount() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAccount that = (ClientAccount) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "Логин: " + getLogin() + " , " + "Пароль: " + getPassword() +
                " , " + "Статус:" + getStatusAccount();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public StatusAccount getStatusAccount() {
        return statusAccount;
    }

    public void setStatusAccount(StatusAccount statusAccount) {
        this.statusAccount = statusAccount;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public CashAccount getCashAccount() {
        return cashAccount;
    }

    public void setCashAccount(CashAccount cashAccount) {
        this.cashAccount = cashAccount;
    }
}

