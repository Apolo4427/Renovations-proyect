package com.renovations.jrl.apirestrenovations.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Repositories.ClienteRepository;

@Service
public class ServiceClienteImpl implements ServicesCliente {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        
        return null;
    }

    @Override
    public String eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
        return "El cliente con id: "+id+" ha sido eleiminado excitosamente.";
    }

    @Override
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    @Override
    public Cliente getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }
}
