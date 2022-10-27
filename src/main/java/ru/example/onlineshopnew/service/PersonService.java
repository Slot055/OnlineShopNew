package ru.example.onlineshopnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.example.onlineshopnew.model.person.Person;
import ru.example.onlineshopnew.model.product.Product;
import ru.example.onlineshopnew.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements ServiceClass {

    private final PersonRepository personRepository;


    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public ArrayList<Person> read() {
        return (ArrayList<Person>) personRepository.findAll();
    }

    @Override
    public Object readOne(int id) {
        return personRepository.getReferenceById(id);
    }

    @Override
    public Person create(Object object) {
        Person person = (Person) object;
        personRepository.save(person);
        return person;
    }

    @Override
    public boolean update(Object object, int id) {
        Person person = (Person) object;
        if (personRepository.existsById(id)) {
            person.setId(id);
            personRepository.save(person);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Person> loadObjectByParameter(String... parameter) throws UsernameNotFoundException {
        Optional<Person> personOptional;
        if (parameter[0].contains("@")) {
            personOptional = personRepository.findByEmail(parameter[0]);

        } else {
            personOptional = personRepository.findByPhoneNumber(parameter[0]);
        }
        if (personOptional.isEmpty()) {
            throw new UsernameNotFoundException("Персона не найдена");
        }
        return personOptional;
    }

    @Override
    public List<Person> loadObjectsByParameter(String... parameter) {
        return null;
    }

}
