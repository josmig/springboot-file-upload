package com.company.app.Model.Dao;

import com.company.app.Model.Entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IPersonaDao extends PagingAndSortingRepository<Person,Long> {
    /*List<Person> findAll();
    void save(Person person);
    Person find(Long id);
    void delete(Long id);*/
}
