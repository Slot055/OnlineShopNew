package ru.example.onlineshopnew.model.buy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.DateTime;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "checks")
public class Check implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private Pay pay;

    private DateTime datePay;
    private Order order;

    public Check(Order order,DateTime datePay, Pay pay) {
        this.order = order;
        this.datePay = datePay;
        this.pay = pay;
    }

    public Check(Pay pay) {
        this.pay = pay;
    }

    public Check() {

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

    @OneToOne(mappedBy = "check",cascade = CascadeType.ALL)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "date_pay")
    public DateTime getDatePay() {
        return datePay;
    }

    public void setDatePay(DateTime datePay) {
        this.datePay = datePay;
    }

    @Transient

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }
}
