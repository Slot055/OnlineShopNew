package ru.example.onlineshopnew.controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.example.onlineshopnew.security.AccountDetails;

@Controller
@RequestMapping("/authentification")
@SessionAttributes("account")
public class WelcomeRedirectController {

    @GetMapping("/hello")
    public String hello(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        model.addAttribute("account",accountDetails.getAccount());
        if (accountDetails.getAccount().getRole().getAuthority().equals("ROLE_ADMIN")) {
            return "employee/adminPage";
        } else if (accountDetails.getAccount().getRole().getAuthority().equals("ROLE_USER")) {
            return "client/clientPage";
        } else
            return "notFound";
    }

}
