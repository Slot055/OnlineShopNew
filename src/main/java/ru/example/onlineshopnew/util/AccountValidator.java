package ru.example.onlineshopnew.util;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.service.AccountDetailsService;

@Component
public class AccountValidator implements Validator {

    private final AccountDetailsService accountDetailsService;

    @Autowired
    public AccountValidator(AccountDetailsService accountDetailsService) {
        this.accountDetailsService = accountDetailsService;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return Account.class.equals(aClass);
    }

    @Override
    public void validate(@NotNull Object object, @NotNull Errors errors) {
        Account account = (Account) object;
        try {
            accountDetailsService.loadUserByUsername(account.getLogin());
        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("login", "1", "Аккаунт с таким логином уже зарегистрирован");
    }
}
