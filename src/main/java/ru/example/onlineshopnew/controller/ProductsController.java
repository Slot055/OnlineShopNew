package ru.example.onlineshopnew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.onlineshopnew.model.product.Product;
import ru.example.onlineshopnew.service.ServiceClass;

import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ServiceClass serviceClass;

    @Autowired
    public ProductsController(@Qualifier("productService") ServiceClass serviceClass) {
        this.serviceClass = serviceClass;
    }

    @GetMapping("/catalog")
    public String showCatalog(ModelMap modelMap, @RequestParam String parameterCatalog) {
        @SuppressWarnings("unchecked")
        ArrayList<Product> products = (ArrayList<Product>) serviceClass.read();
        Set<String> parameterSet = new HashSet<>();
        ArrayList<Product> productsResult = new ArrayList<>();
        for (Product product : products) {
            if (parameterCatalog.isBlank())
                parameterSet.add(product.getTypeProduct());
            else if (parameterCatalog.equals(product.getTypeProduct()))
                parameterSet.add(product.getCategoryProduct());
            else if (parameterCatalog.equals(product.getCategoryProduct()))
                parameterSet.add(product.getGroupProduct());
            else if (parameterCatalog.equals(product.getGroupProduct()))
                productsResult.add(product);
        }
        modelMap.addAttribute("parameterSet", parameterSet);
        modelMap.addAttribute("productsResult", productsResult);

        return "products/catalog";
    }

    @GetMapping("/{item}")
    public String showProductCard(@PathVariable("item") int item, Model model) {
        model.addAttribute("product", serviceClass.readOne(item));
        return "products/productCard";
    }

    @GetMapping("/searchProducts")
    public String searchProduct() {
        return "products/searchProducts";
    }

    @PostMapping("/searchProducts")
    public String searchProductsResult(@RequestParam("typeProduct") String typeProduct, @RequestParam("categoryProduct") String categoryProduct,
                                       @RequestParam("groupProduct") String groupProduct, @RequestParam("nameProduct") String nameProduct,
                                       @RequestParam("priceMin") Double priceAfter, @RequestParam("priceMax") Double priceBefore,
                                       Model model) {

        model.addAttribute("searchProductsResult", serviceClass.loadObjectsByParameter(typeProduct,
                categoryProduct, groupProduct, nameProduct, String.valueOf(priceAfter), String.valueOf(priceBefore)));


        return "products/searchProductsResult";
    }

}
