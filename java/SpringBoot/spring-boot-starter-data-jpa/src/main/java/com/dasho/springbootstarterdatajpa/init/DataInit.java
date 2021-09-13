package com.dasho.springbootstarterdatajpa.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.dasho.springbootstarterdatajpa.dao.PersonDao;
import com.dasho.springbootstarterdatajpa.entity.Person;

@Component
public class DataInit implements ApplicationRunner {

    @Autowired
    private PersonDao personDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = personDao.count();

        if (count == 0) {
            Person p1 = new Person();
            p1.setFirstName("James");
            p1.setLastName("Thomas");
            personDao.save(p1);

            Person p2 = new Person();
            p2.setFirstName("Mary");
            p2.setLastName("Scott");
            personDao.save(p2);

            Person p3 = new Person();
            p3.setFirstName("Sam");
            p3.setLastName("Smith");
            personDao.save(p3);
        }

    }

}