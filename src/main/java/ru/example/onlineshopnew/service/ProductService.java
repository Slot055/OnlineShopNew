package ru.example.onlineshopnew.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.model.product.Product;
import ru.example.onlineshopnew.repository.ProductRepository;
import ru.example.onlineshopnew.security.AccountDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ServiceClass {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ArrayList<Product> read() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    @Override
    public Product readOne(int id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public Product create(Object object) {
        Product product = (Product) object;
        productRepository.save(product);
        return product;
    }

    @Override
    public boolean update(Object object, int item) {
        Product product = (Product) object;
        if (productRepository.existsById(item)) {
            product.setItem(item);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int item) {
        if (productRepository.existsById(item)) {
            productRepository.deleteById(item);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Product> loadObjectByParameter(String...barCode) throws UsernameNotFoundException {
        Optional<Product> productOptional = productRepository.findByBarCode(Long.parseLong(barCode[0]));
        if (productOptional.isEmpty()) {
            throw new UsernameNotFoundException("Товар не найден");
        }
        return productOptional;
    }

    @Override
    public List<Product> loadObjectsByParameter(String... parameter) {
        ArrayList<Product> productList = (ArrayList<Product>) productRepository.findProductsByTypeProductOrCategoryProductOrGroupProductOrNameProductOrPriceAfterAndPriceBefore
                (parameter[0], parameter[1], parameter[2], parameter[3], Double.parseDouble(parameter[4]),Double.parseDouble(parameter[5]));

        return productList;
    }
}
