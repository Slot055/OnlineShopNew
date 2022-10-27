package ru.example.onlineshopnew.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.onlineshopnew.model.buy.Order;
import ru.example.onlineshopnew.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements ServiceClass{

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ArrayList<Order> read() {
        return (ArrayList<Order>) orderRepository.findAll();
    }

    @Override
    public Order readOne(int numberOrder) {
        return orderRepository.getReferenceById(numberOrder);
    }

    @Override
    public Order create(Object object) {
        Order order = (Order) object;
        orderRepository.save(order);
        return order;
    }

    @Override
    public boolean update(Object object, int numberOrder) {
        Order order = (Order) object;
        if(orderRepository.existsById(numberOrder)){
            order.setNumberOrder(numberOrder);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int numberOrder) {
        if(orderRepository.existsById(numberOrder)){
            orderRepository.deleteById(numberOrder);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Order> loadObjectByParameter(String... parameter) {
        return Optional.empty();
    }

    @Override
    public List<Order> loadObjectsByParameter(String... id) {
        return orderRepository.findOrderByAccountId(Integer.parseInt(id[0]));
    }

}
