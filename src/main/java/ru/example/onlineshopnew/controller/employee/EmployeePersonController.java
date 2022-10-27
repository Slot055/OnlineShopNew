package ru.example.onlineshopnew.controller.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.onlineshopnew.model.person.Person;
import ru.example.onlineshopnew.service.ServiceClass;
import ru.example.onlineshopnew.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/authentification/employee/persons")
public class EmployeePersonController {
    private final ServiceClass serviceClass;
    private final PersonValidator personValidator;

    @Autowired
    public EmployeePersonController(@Qualifier("personService") ServiceClass serviceClass, PersonValidator personValidator) {
        this.serviceClass = serviceClass;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String read(Model model) {
        model.addAttribute("persons", serviceClass.read());
        return "employee/accounts/persons";
    }

    @GetMapping("/{id}")
    public String readOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", serviceClass.readOne(id));

        return "client/personInformation";
    }

   @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", serviceClass.readOne(id));

        return "client/editPerson";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "client/editPerson";
        }
        serviceClass.update(person, id);

        return "redirect:/authentification/employee/persons";
    }

}
