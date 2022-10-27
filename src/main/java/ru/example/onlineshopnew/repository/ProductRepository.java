package ru.example.onlineshopnew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.onlineshopnew.model.product.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByBarCode(Long barCode);

    List<Product> findProductsByTypeProductOrCategoryProductOrGroupProductOrNameProductOrPriceAfterAndPriceBefore(String typeProduct, String categoryProduct, String groupProduct,
                                                                                               String nameProduct, Double priceAfter, Double priceBefore);
}
