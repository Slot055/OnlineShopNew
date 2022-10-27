package ru.example.onlineshopnew.controller.buy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.model.buy.Basket;
import ru.example.onlineshopnew.model.product.Product;
import ru.example.onlineshopnew.service.ServiceClass;
import ru.example.onlineshopnew.util.BasketValidator;

import java.util.Optional;

@Controller
@RequestMapping("/authentification/basket")
public class BasketController {

    private final ServiceClass basketService;
    private final ServiceClass productService;
    private final BasketValidator basketValidator;

    @Autowired
    public BasketController(@Qualifier("basketService") ServiceClass basketService, BasketValidator basketValidator, @Qualifier("productService") ServiceClass productService) {
        this.basketService = basketService;
        this.productService = productService;
        this.basketValidator = basketValidator;
    }

    @GetMapping()
    public String showBasket(@SessionAttribute("account") Account account, Model model) {
        model.addAttribute("basket", basketService.loadObjectsByParameter(String.valueOf(account.getId()),String.valueOf(0)));
        return "buy/basket/showBasket";
    }

    @PostMapping("/{item}")
    public String addToBasket(@SessionAttribute("account") Account account, @ModelAttribute("basket") Basket basket,
                              BindingResult bindingResult, @PathVariable("item") int item, Model model) {
        Product product = (Product) productService.readOne(item);
        basket.setProduct(product);
        basket.setAccount(account);
        basketValidator.validate(basket, bindingResult);
        if (bindingResult.hasErrors()) {
            @SuppressWarnings("unchecked")
            Optional<Basket> basketOptional = (Optional<Basket>) basketService.loadObjectByParameter(String.valueOf(account.getId()),
                    String.valueOf(product.getBarCode()), String.valueOf(basket.getStatusInOrder()));
            basketOptional.ifPresent(value -> basket.setId(value.getId()));
            basketOptional.ifPresent(value -> basket.setQuantityInBasket(value.getQuantityInBasket() + 1));
            account.getBasket().remove(account.getBasket().stream().filter(basketChange -> basketChange.getId()==basket.getId()).findFirst().get());
            account.getBasket().add(basket);
            basketService.update(basket, basket.getId());
            model.addAttribute("product", product);
            return "redirect:/authentification/basket";
        }
        basket.setQuantityInBasket(1);
        account.getBasket().add(basket);
        basketService.create(basket);
        return "redirect:/authentification/basket";
    }

    @PatchMapping("/{id}")
    public String removeOneFromBasket(@ModelAttribute("basket") Basket basket, @PathVariable("id") int id) {
        basket = (Basket) basketService.readOne(id);
        if (basket.getQuantityInBasket() > 1) {
            basket.setQuantityInBasket(basket.getQuantityInBasket() - 1);
            basketService.update(basket, id);
        } else basketService.delete(id);
        return "redirect:/authentification/basket";
    }

    @DeleteMapping("/{id}")
    public String removeFromBasket(@PathVariable("id") int id) {
        basketService.delete(id);
        return "redirect:/authentification/basket";
    }

}
