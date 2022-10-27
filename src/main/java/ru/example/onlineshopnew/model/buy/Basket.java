package ru.example.onlineshopnew.model.buy;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.model.product.Product;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;


@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "basket")
public class Basket implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private Product product;
    private int quantityInBasket;

    private byte statusInOrder;
    private Account account;

    private Order order;


    public Basket(int id, Product product, int quantityInBasket, byte statusInOrder) {
        this.id = id;
        this.product = product;
        this.quantityInBasket = quantityInBasket;
        this.statusInOrder = statusInOrder;

    }

    public Basket() {

    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "item")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "quantity_in_basket")
    public int getQuantityInBasket() {
        return quantityInBasket;
    }

    public void setQuantityInBasket(int quantityInBasket) {
        this.quantityInBasket = quantityInBasket;
    }

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "number_order")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "status_in_order")
    public byte getStatusInOrder() {
        return statusInOrder;
    }

    public void setStatusInOrder(byte statusInOrder) {
        this.statusInOrder = statusInOrder;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
