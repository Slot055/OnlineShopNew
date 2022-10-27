package ru.example.onlineshopnew.model.buy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Scope;
import ru.example.onlineshopnew.model.account.Account;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "orders")
@Scope("prototype")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int numberOrder;
    private List<Basket> listProducts;
    private Date dateTime;
    private Check check;
    private StatusOrder statusOrder;

    private Account account;

    public Order(int numberOrder, List<Basket> listProducts, Date dateTime, Check check, StatusOrder statusOrder, Account account) {
        this.numberOrder = numberOrder;
        this.listProducts = listProducts;
        this.dateTime = dateTime;
        this.check = check;
        this.statusOrder = statusOrder;
        this.account = account;
    }

    public Order() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number_order")
    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    public List<Basket> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Basket> listProducts) {
        this.listProducts = listProducts;
    }
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "check_id", referencedColumnName = "id")
    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    @Column(name = "status_order")
    @Enumerated(EnumType.STRING)
    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
