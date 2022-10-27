package ru.example.onlineshopnew.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.onlineshopnew.model.product.Product;
import ru.example.onlineshopnew.service.ServiceClass;
import ru.example.onlineshopnew.util.ProductValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/authentification/employee/products")
public class EmployeeProductsController {

    private final ServiceClass serviceClass;
    private final ProductValidator productValidator;

    @Autowired
    public EmployeeProductsController(@Qualifier("productService") ServiceClass serviceClass, ProductValidator productValidator) {
        this.serviceClass = serviceClass;
        this.productValidator = productValidator;
    }

    @GetMapping()
    public String read(Model model) {
        model.addAttribute("products", serviceClass.read());
        return "employee/products/productsList";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "employee/products/newProduct";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {
        productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            return "employee/products/newProduct";
        }
        serviceClass.create(product);
        return "redirect:/authentification/employee/products";
    }

    @GetMapping("/{item}/edit")
    public String edit(@PathVariable("item") int item, Model model) {
        model.addAttribute("product", serviceClass.readOne(item));

        return "employee/products/editProduct";
    }

    @PatchMapping("/{item}")
    public String update(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("item") int item) {
        productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            return "employee/products/editProduct";
        }
        serviceClass.update(product, item);

        return "redirect:/authentification/employee/products";
    }

    @DeleteMapping("/{item}")
    public String delete(@PathVariable("item") int item) {
        serviceClass.delete(item);

        return "redirect:/authentification/employee/products";
    }


}
