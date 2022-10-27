package ru.example.onlineshopnew.controller.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.model.person.Person;
import ru.example.onlineshopnew.util.AccountValidator;
import ru.example.onlineshopnew.service.ServiceClass;
import javax.validation.Valid;

@Controller
@RequestMapping("/authentification/employee/accounts")
public class EmployeeAccountsController {

    private final ServiceClass serviceClass;
    private final AccountValidator accountValidator;

    @Autowired
    public EmployeeAccountsController(@Qualifier("accountService") ServiceClass serviceClass,AccountValidator accountValidator) {
        this.serviceClass = serviceClass;
        this.accountValidator = accountValidator;
    }

    @GetMapping()
    public String read(Model model) {
        model.addAttribute("accounts", serviceClass.read());
        return "employee/accounts/accountsList";
    }

    @GetMapping("/{id}")
    public String readOne(@PathVariable("id") int id, Model model) {
        Account account = (Account) serviceClass.readOne(id);
        model.addAttribute("account", account);
        return "employee/accounts/accountOne";
    }

    @GetMapping("/new")
    public String newAccount(@ModelAttribute("account") Account account) {
        return "employee/accounts/newAccount";
    }

    @PostMapping()
    public String create(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) {
        accountValidator.validate(account, bindingResult);
        if (bindingResult.hasErrors()) {
            return "employee/accounts/newAccount";
        }
        serviceClass.create(account);
        return "redirect:/authentification/employee/accounts";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("account", serviceClass.readOne(id));

        return "employee/accounts/editAccount";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult, @PathVariable("id") int id) {
        accountValidator.validate(account, bindingResult);
        if (bindingResult.hasErrors()) {
            return "employee/accounts/editAccount";
        }
        serviceClass.update(account, id);

        return "redirect:/authentification/employee/accounts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        serviceClass.delete(id);

        return "redirect:/authentification/employee/accounts";
    }


}
