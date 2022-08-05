package ru.example.onlineshopnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.service.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public String read(Model model) {
        model.addAttribute("accounts", accountService.read());
        return "accounts/accountsList";
    }

    @GetMapping("/{id}")
    public String readOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("account", accountService.readOne(id));

        return "accounts/accountOne";
    }

    @GetMapping("/new")
    public String newAccount(@ModelAttribute("account") Account account) {
        return "accounts/newAccount";
    }

    @PostMapping()
    public String create(@ModelAttribute("account") Account account) {
        accountService.create(account);
        return "redirect:/accounts";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("account", accountService.readOne(id));

        return "accounts/editAccount";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("account") Account account, @PathVariable("id") int id) {
        accountService.update(account, id);

        return "redirect:/accounts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        accountService.delete(id);

        return "redirect:/accounts";
    }


}
