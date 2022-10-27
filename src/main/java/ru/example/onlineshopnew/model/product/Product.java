package ru.example.onlineshopnew.model.product;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.CodePointLength;
import org.hibernate.validator.constraints.EAN;
import org.hibernate.validator.constraints.Range;
import ru.example.onlineshopnew.model.buy.Basket;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "products")
public class Product implements Comparable<Product>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private int item;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 3, max = 20, message = "Поле должно содержать от 3 до 15 символов")
    private String typeProduct;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 3, max = 20, message = "Поле должно содержать от 3 до 20 символов")
    private String categoryProduct;
    @NotEmpty(message = "Поле не может быть пустым")
    private String groupProduct;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 3, max = 20, message = "Поле должно содержать от 3 до 20 символов")
    private String nameProduct;
    @Digits(integer = 99999999, fraction = 2, message = "inValid")
    @Range(min = 1, max = 10000000, message = "Минимальное значение цены на товар 1 рубль, максимум 10 000 000 рублей")
    private double price;
    @Range(min = 10000000, max = 99999999, message = "Некоректный Штрих-код, необходимое количество знаков - 8")
    private long barCode;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 10, max = 150, message = "Поле должно содержать от 10 до 150 символов")
    private String description;

    private Basket basket;

    public Product(int item, String typeProduct, String categoryProduct, String groupProduct, String nameProduct, double price, long barCode, String description) {
        this.item = item;
        this.typeProduct = typeProduct;
        this.categoryProduct = categoryProduct;
        this.groupProduct = groupProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.barCode = barCode;
        this.description = description;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Тип товара:" + getTypeProduct() + " , " + "Категория товара:" + getCategoryProduct() + " , " + "Группа товара:"
                + getGroupProduct() + " , " + "Наименование товара:" + getNameProduct() +
                " , " + "Цена:" + getPrice() + " , " + "Штрих-код:" + getBarCode() + " , " + "Описание:" + getDescription();
    }


    public void productInfo() {

    }

    @Column(name = "type_product", nullable = false)
    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    @Column(name = "category_product", nullable = false)
    public String getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(String categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    @Column(name = "group_product", nullable = false)
    public String getGroupProduct() {
        return groupProduct;
    }

    public void setGroupProduct(String groupProduct) {
        this.groupProduct = groupProduct;
    }

    @Column(name = "name_product", nullable = false)
    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "bar_code", nullable = false)
    public long getBarCode() {
        return barCode;
    }

    public void setBarCode(long barcode) {
        this.barCode = barcode;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item", unique = true, nullable = false)
    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    @Transient
    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Override
    public int compareTo(Product product) {
        return (int) (this.getPrice() - product.getPrice());
    }
}
