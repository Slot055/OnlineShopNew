package ru.example.onlineshopnew.util;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.example.onlineshopnew.model.product.Product;
import ru.example.onlineshopnew.service.ProductService;

@Component
public class ProductValidator implements Validator {

    private final ProductService productService;

    @Autowired
    public ProductValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(@NotNull Object object, @NotNull Errors errors) {
        Product product = (Product) object;
        try {
            productService.loadObjectByParameter(String.valueOf(product.getBarCode()));
        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("barCode", "2", "Товар с таким штрихкодом уже есть в базе данных");
    }
}
