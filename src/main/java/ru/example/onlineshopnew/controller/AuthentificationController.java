package ru.example.onlineshopnew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.service.ServiceClass;
import ru.example.onlineshopnew.util.AccountValidator;
import ru.example.onlineshopnew.service.AccountService;

import javax.validation.Valid;

@Controller
@RequestMapping("/authentification")
public class AuthentificationController {

    private final AccountValidator accountValidator;
    private final ServiceClass serviceClass;

    @Autowired
    public AuthentificationController(AccountValidator accountValidator, @Qualifier("accountService") ServiceClass serviceClass) {
        this.accountValidator = accountValidator;
        this.serviceClass = serviceClass;
    }

    @GetMapping("/login")
    public String loginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken)
            return "authentification/login";
        else
            return "redirect:/authentification/hello";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("account") Account account) {
        return "client/newAccountClient";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) {
        accountValidator.validate(account, bindingResult);

        if (bindingResult.hasErrors()) {
            return "client/newAccountClient";
        }
        serviceClass.create(account);

        return "redirect:/authentification/login";
    }

}
