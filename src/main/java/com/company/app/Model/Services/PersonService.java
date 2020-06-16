package com.company.app.Model.Services;

import com.company.app.Model.Entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    List<Person> findAll();
    Page<Person> findAll(Pageable pageable);
    void save(Person person);
    Person findOne(Long id);
    void delete(Long id);
}
