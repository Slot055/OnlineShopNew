package ru.example.onlineshopnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.onlineshopnew.model.buy.Basket;
import ru.example.onlineshopnew.repository.BasketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BasketService implements ServiceClass {

    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public ArrayList<Basket> read() {
        return (ArrayList<Basket>) basketRepository.findAll();
    }

    @Override
    public Basket readOne(int id) {
        return basketRepository.getReferenceById(id);
    }

    @Override
    public Basket create(Object object) {
        Basket basket = (Basket) object;
        basketRepository.save(basket);
        return basket;
    }

    @Override
    public boolean update(Object object, int id) {
        Basket basket = (Basket) object;
        if (basketRepository.existsById(id)) {
            basket.setId(id);
            basketRepository.save(basket);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (basketRepository.existsById(id)) {
            basketRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Basket> loadObjectByParameter(String... parameter) {
        Optional<Basket> basketOptional = basketRepository.findBasketByAccountIdAndProductBarCodeAndStatusInOrder(Integer.parseInt(parameter[0]),
                Long.parseLong(parameter[1]), Byte.parseByte(parameter[2]));
        if (basketOptional.isEmpty()) {
            return Optional.empty();
        }
        return basketOptional;
    }

    @Override
    public List<Basket> loadObjectsByParameter(String... id) {

        return basketRepository.findBasketByAccountIdAndStatusInOrder(Integer.parseInt(id[0]), Byte.parseByte(id[1]));
    }

}
