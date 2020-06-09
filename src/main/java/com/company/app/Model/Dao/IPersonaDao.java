package com.company.app.Model.Dao;

import com.company.app.Model.Entity.Person;

import java.util.List;

public interface IPersonaDao {
    List<Person> findAll();
    void save(Person person);
    Person find(Long id);
    void delete(Long id);
}
