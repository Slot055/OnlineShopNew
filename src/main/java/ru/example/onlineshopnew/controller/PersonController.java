package ru.example.onlineshopnew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.model.person.Person;
import ru.example.onlineshopnew.service.ServiceClass;
import ru.example.onlineshopnew.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/authentification/person")
public class PersonController {

    private final ServiceClass serviceClass;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(@Qualifier("personService") ServiceClass serviceClass, PersonValidator personValidator) {
        this.serviceClass = serviceClass;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String personInformationShow(@SessionAttribute("account") Account account, Model model) {
        model.addAttribute("person", serviceClass.readOne(account.getPerson().getId()));

        return "client/personInformation";
    }

    @GetMapping("/edit")
    public String edit(@SessionAttribute("account") Account account, Model model) {
        model.addAttribute("person", serviceClass.readOne(account.getPerson().getId()));

        return "client/editPerson";
    }

    @PatchMapping()
    public String update(@SessionAttribute("account") Account account, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "client/editPerson";
        }
        serviceClass.update(person, account.getPerson().getId());

        return "redirect:/authentification/person";
    }

}

