package com.company.app.Model.Dao;

import com.company.app.Model.Entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class IPersonDaoImpl implements IPersonaDao{


    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return em.createQuery(" from Person").getResultList();
    }

    @Override
    @Transactional
    public void save(Person person) {
        if(person.getId() != null && person.getId()>0){
            em.merge(person);
        }else{
            em.persist(person);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Person find(Long id) {
        return em.find(Person.class,id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        em.remove(find(id));
    }
}
