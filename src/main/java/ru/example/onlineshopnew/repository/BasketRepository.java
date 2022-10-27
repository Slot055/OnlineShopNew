package ru.example.onlineshopnew.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.onlineshopnew.model.buy.Basket;
import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
    Optional<Basket> findBasketByAccountIdAndProductBarCodeAndStatusInOrder(Integer id, Long barCode, Byte statusInOrder);

    List<Basket> findBasketByAccountIdAndStatusInOrder(int id,byte statusInOrder);
}
