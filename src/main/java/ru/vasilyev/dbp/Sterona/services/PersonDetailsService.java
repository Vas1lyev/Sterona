package ru.vasilyev.dbp.Sterona.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vasilyev.dbp.Sterona.dao.PersonDAO;
import ru.vasilyev.dbp.Sterona.models.Person;
import ru.vasilyev.dbp.Sterona.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonDAO personDAO;

    public PersonDetailsService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personDAO.findByUsername(username);

        if (person == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new PersonDetails(person);
    }
}