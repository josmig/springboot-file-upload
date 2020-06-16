package com.company.app.Model.Services;

import com.company.app.Model.Dao.IPersonaDao;
import com.company.app.Model.Entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    public IPersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return (List<Person>)personaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Person> findAll(Pageable pageable) {
        return personaDao.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Person person) {
        personaDao.save(person);
    }

    @Override
    @Transactional
    public Person findOne(Long id) {
        return personaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        personaDao.deleteById(id);
    }
}
