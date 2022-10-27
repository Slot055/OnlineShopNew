package ru.example.onlineshopnew.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.example.onlineshopnew.model.person.Person;
import ru.example.onlineshopnew.service.PersonService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(@NotNull Object object, @NotNull Errors errors) {
        Person person = (Person) object;

        try {
            personService.loadObjectByParameter(person.getPhoneNumber());
            errors.rejectValue("phoneNumber", "1", "Данный номер телефона уже зарегистрирован в базе данных");
        } catch (UsernameNotFoundException ignoredPhoneNumber) {
            try {
                personService.loadObjectByParameter(person.getEmail());
            } catch (UsernameNotFoundException ignoredEmail) {
                return;
            }
            errors.rejectValue("email", "2", "Данный email уже зарегистрирован в базе данных");

        }

    }

}
