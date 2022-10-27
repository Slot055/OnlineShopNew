package ru.example.onlineshopnew.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.repository.AccountRepository;
import ru.example.onlineshopnew.security.AccountDetails;
import java.util.Optional;

@Service
public class AccountDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByLogin(login);
        if (account.isEmpty()) {
            throw new UsernameNotFoundException("Аккаунт не найден");
        }
        return new AccountDetails(account.get());
    }

}
