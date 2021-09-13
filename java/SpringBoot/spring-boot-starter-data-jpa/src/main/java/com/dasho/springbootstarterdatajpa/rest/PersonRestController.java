package com.dasho.springbootstarterdatajpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dasho.springbootstarterdatajpa.dao.PersonDao;
import com.dasho.springbootstarterdatajpa.entity.Person;

@RestController
public class PersonRestController {

    @Autowired
    private PersonDao repository;

    @GetMapping("/rest")
    public Iterable<Person> all() {
        return repository.findAll();
    }

    @PostMapping("/rest")
    public Person create(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @GetMapping("/rest/{id}")
    public Person get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/rest/{id}")
    public Person put(@RequestBody Person existingPerson, @PathVariable Long id) {
        return repository.findById(id).map(person -> {
            person.setFirstName(existingPerson.getFirstName());
            person.setLastName(existingPerson.getLastName());
            return repository.save(person);
        }).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @DeleteMapping("/rest/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}