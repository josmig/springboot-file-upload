package com.pantigoso.app.Model.Service;

import com.pantigoso.app.Model.Entity.Cliente;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();
    public void save(Cliente cliente);
    public Cliente findOne(Long id);
    public void delete(Long id);

}
