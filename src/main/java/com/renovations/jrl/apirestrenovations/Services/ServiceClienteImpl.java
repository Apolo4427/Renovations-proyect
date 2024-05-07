package com.renovations.jrl.apirestrenovations.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Repositories.ClienteRepository;
import com.renovations.jrl.apirestrenovations.error.ClienteNoFundException;



@Service
public class ServiceClienteImpl implements ServicesCliente {

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) throws ClienteNoFundException {
        Cliente clienteSinActualizar = clienteRepository.findById(id).get();

        clienteSinActualizar.setNombre(cliente.getNombre());
        clienteSinActualizar.setEmail(cliente.getEmail());
        clienteSinActualizar.setContacto(cliente.getContacto());
        clienteSinActualizar.setDireccion(cliente.getDireccion());
        clienteSinActualizar.setReferido_por(cliente.getReferido_por());

        return clienteRepository.save(clienteSinActualizar);
    }

    @Transactional
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
    public Cliente getClienteByEmail(String email) throws ClienteNoFundException {
        return clienteRepository.findByEmail(email);
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    @Override
    public Cliente getClienteBYId(Long id) throws ClienteNoFundException {
        return clienteRepository.findById(id).get();
    }

    @Override
    public List<String> getAllEmails(){
        List<Cliente> listaClientes= clienteRepository.findAll();
        List<String> emails = new ArrayList<String>();

        for(Cliente cliente : listaClientes){
            emails.add(cliente.getEmail());
        }
        return emails;
    }
}
