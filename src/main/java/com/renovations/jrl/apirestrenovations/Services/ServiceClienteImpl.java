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
        Cliente clienteSinActualizar = clienteRepository.findById(id).get();

        clienteSinActualizar.setNombre(cliente.getNombre());
        clienteSinActualizar.setEmail(cliente.getEmail());
        clienteSinActualizar.setContacto(cliente.getContacto());
        clienteSinActualizar.setDireccion(cliente.getDireccion());
        clienteSinActualizar.setFecha_estimado(cliente.getFecha_estimado());
        clienteSinActualizar.setReferido_por(cliente.getReferido_por());

        return clienteRepository.save(clienteSinActualizar);
    }

    @Override
    public String eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
        return "El cliente ha sido eleiminado excitosamente.";
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

    @Override
    public Cliente getClienteBYId(Long id) {
        return clienteRepository.findById(id).get();
    }
}
