package ru.example.onlineshopnew.util;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.example.onlineshopnew.model.buy.Basket;
import ru.example.onlineshopnew.service.BasketService;
import java.util.ArrayList;

@Component
public class BasketValidator implements Validator {
    private final BasketService basketService;

    @Autowired
    public BasketValidator(BasketService basketService) {
        this.basketService = basketService;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return Basket.class.equals(aClass);
    }

    @Override
    public void validate(@NotNull Object object, @NotNull Errors errors) {
        Basket basket = (Basket) object;

        ArrayList<Basket> basketList = (ArrayList<Basket>) basketService.loadObjectsByParameter(String.valueOf(basket.getAccount().getId()),String.valueOf(basket.getStatusInOrder()));
        ArrayList<Basket> basketResult = new ArrayList<>();
        for (Basket basketOne : basketList) {
            if (basketOne.getProduct().getItem() == basket.getProduct().getItem()) {
                basketResult.add(basketOne);
            }
        }

            if (basketResult.isEmpty())
                return;

            errors.rejectValue("product", "1", "Данный товар уже добавлен в корзину");
        }
}
