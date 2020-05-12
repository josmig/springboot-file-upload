package com.pantigoso.app.Model.Service;

import com.pantigoso.app.Model.Dao.IClienteDao;
import com.pantigoso.app.Model.Entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class IClienteServiceImpl implements IClienteService {

    @Autowired
    public IClienteDao iClienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>)iClienteDao.findAll();
    }

    @Override
    public void save(Cliente cliente) {
        iClienteDao.save(cliente);
    }

    @Override
    public Cliente findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
