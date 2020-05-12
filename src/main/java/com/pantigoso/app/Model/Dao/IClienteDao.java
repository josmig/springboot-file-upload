package com.pantigoso.app.Model.Dao;

import com.pantigoso.app.Model.Entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
