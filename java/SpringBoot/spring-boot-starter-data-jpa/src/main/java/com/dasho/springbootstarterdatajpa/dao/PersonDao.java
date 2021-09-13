package com.dasho.springbootstarterdatajpa.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dasho.springbootstarterdatajpa.entity.Person;

@Repository
public interface PersonDao extends CrudRepository<Person, Long> {

}