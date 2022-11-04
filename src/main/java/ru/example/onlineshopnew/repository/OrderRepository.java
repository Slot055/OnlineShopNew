package ru.example.onlineshopnew.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.onlineshopnew.model.buy.Order;
import ru.example.onlineshopnew.model.buy.StatusOrder;
import java.util.Collection;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrderByAccountIdAndStatusOrderIn(int account_id, Collection<StatusOrder> statusOrder);

}
