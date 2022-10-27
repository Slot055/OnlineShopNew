package ru.example.onlineshopnew.controller.buy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.model.buy.Order;
import ru.example.onlineshopnew.service.BuyService;
import ru.example.onlineshopnew.service.ServiceClass;

@Controller
@RequestMapping("/authentification/order")
public class OrderController {

    private ServiceClass serviceClass;

    private BuyService buyService;

    @Autowired
    public OrderController(@Qualifier("orderService") ServiceClass serviceClass, BuyService buyService) {
        this.serviceClass = serviceClass;
        this.buyService = buyService;
    }

    @GetMapping
    public String showOrders(@SessionAttribute("account") Account account, Model model) {
        model.addAttribute("orders", serviceClass.loadObjectsByParameter(String.valueOf(account.getId())));
        return "buy/order/historyOrders";
    }
//TODO(реализовать привязку к аккаунту)
    @GetMapping("/{numberOrder}")
    public String showOneOrder(@PathVariable("numberOrder") int numberOrder,@SessionAttribute("account") Account account, Model model) {
        Order order = (Order) serviceClass.readOne(numberOrder);
        model.addAttribute("order",order);
        return "buy/order/showOrder";
    }

    @PostMapping()
    public String create(@SessionAttribute("account") Account account, @ModelAttribute("order") Order order) {
        buyService.makeAnOrder(account, order);
        serviceClass.create(order);
        order.getListProducts().forEach(basket -> getBuyService().getBasketService().update(basket, basket.getId()));
        return "redirect:/authentification/order";
    }

    public ServiceClass getOrderService() {
        return serviceClass;
    }

    public void setOrderService(ServiceClass orderService) {
        this.serviceClass = orderService;
    }

    public BuyService getBuyService() {
        return buyService;
    }

    public void setBuyService(BuyService buyService) {
        this.buyService = buyService;
    }
}
