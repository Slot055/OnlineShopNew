package ru.example.onlineshopnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.model.buy.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class BuyService implements ServiceClass {

    private final AccountService accountService;
    private final BasketService basketService;
    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public BuyService(AccountService accountService, BasketService basketService, OrderService orderService, ProductService productService) {
        this.accountService = accountService;
        this.basketService = basketService;
        this.orderService = orderService;
        this.productService = productService;
    }

    public void makeAnOrder(Account account, Order order) {
        order.setDateTime(new Date());
        order.setAccount(account);
        order.setListProducts(List.copyOf(account.getBasket().stream().filter(basket -> basket.getStatusInOrder() == 0).toList()));
        order.getListProducts().forEach(basket -> basket.setStatusInOrder((byte) 1));
        order.getListProducts().forEach(basket -> basket.setOrder(order));
        order.setStatusOrder(StatusOrder.ACTIVE);
    }

    public Order pay(Order order, Check check) {
        check.setPay(Pay.PAID);
        order.setCheck(check);
        return order;
    }

    @Override
    public ArrayList<?> read() {
        return null;
    }

    @Override
    public Object readOne(int id) {
        return null;
    }

    @Override
    public Object create(Object object) {
        return null;
    }

    @Override
    public boolean update(Object object, int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Optional<?> loadObjectByParameter(String... parameter) {
        return Optional.empty();
    }

    @Override
    public List<?> loadObjectsByParameter(String... parameter) {
        return null;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public BasketService getBasketService() {
        return basketService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ProductService getProductService() {
        return productService;
    }
}
