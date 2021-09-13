package com.dasho.springbootstarterdatajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasho.springbootstarterdatajpa.dao.PersonDao;
import com.dasho.springbootstarterdatajpa.entity.Person;

@Controller
public class IndexController {

    @Autowired
    private PersonDao personDao;

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        StringBuilder sb = new StringBuilder();
        Iterable<Person> all = personDao.findAll();
        all.forEach((p) -> sb.append(getUserHtml(p)));
        return sb.toString();
    }

    private String getUserHtml(Person person) {
      return "<div><a href='#id=" + person.getId() + "'>" + person.getId() + " " + person.getFirstName() + " " + person.getLastName() + "</a></div>";
    }

}