package ru.example.onlineshopnew.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.example.onlineshopnew.model.account.Account;
import ru.example.onlineshopnew.model.account.Role;
import ru.example.onlineshopnew.model.person.Person;
import ru.example.onlineshopnew.repository.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements ServiceClass {
    private final AccountRepository accountRepository;

    private final PersonService personService;

    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PersonService personService, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ArrayList<Account> read() {
        return (ArrayList<Account>) accountRepository.findAll();
    }

    @Override
    public Account readOne(int id) {
        return accountRepository.getReferenceById(id);
    }

    @Override
    public Account create(Object object) {
        Account account = (Account) object;
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        if (account.getRole() == null)
            account.setRole(Role.USER);
        account.setPerson(personService.create(new Person()));
        accountRepository.save(account);

        return account;
    }

    @Override
    public boolean update(Object object, int id) {
        Account account = (Account) object;
        if (accountRepository.existsById(id)) {
            account.setId(id);
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<?> loadObjectByParameter(String...parameter) {
        return Optional.empty();
    }

    @Override
    public List<Account> loadObjectsByParameter(String... parameter) {
        return null;
    }

}
