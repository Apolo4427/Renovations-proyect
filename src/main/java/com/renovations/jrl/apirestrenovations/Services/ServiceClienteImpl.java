package com.renovations.jrl.apirestrenovations.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
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

        if(cliente.getNombre()!=null && !cliente.getNombre().isEmpty()){
            clienteSinActualizar.setNombre(cliente.getNombre());
        }
        if(cliente.getEmail()!=null && !cliente.getEmail().isEmpty()){
            clienteSinActualizar.setEmail(cliente.getEmail());
        }
        if(cliente.getContacto()!=null && !cliente.getContacto().isEmpty()){
            clienteSinActualizar.setContacto(cliente.getContacto());
        }
        if(cliente.getDireccion()!=null && !cliente.getDireccion().isEmpty()){
            clienteSinActualizar.setDireccion(cliente.getDireccion());
        }
        if(cliente.getReferido_por()!=null && !cliente.getReferido_por().isEmpty()){
            clienteSinActualizar.setReferido_por(cliente.getReferido_por());
        }
        
        List<Proyecto> listProyectos = clienteSinActualizar.getProyectosList();
        for (Proyecto proyecto : listProyectos) {
            proyecto.setEmailCliente(cliente.getEmail());
        }

        return clienteRepository.save(clienteSinActualizar);
    }

    @Transactional
    @Override
    public String eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
        return "El cliente ha sido eleiminado excitosamente.";
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<String> getAllEmails(){
        List<Cliente> listaClientes= clienteRepository.findAll();
        List<String> emails = new ArrayList<String>();

        for(Cliente cliente : listaClientes){
            emails.add(cliente.getEmail());
        }
        return emails;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllEamilsWithContrato() {
        List<Cliente> listaClientes = clienteRepository.findAll();
        List<String> emailsWithContrato = new ArrayList<String>();

        for(Cliente cliente : listaClientes){
            if(cliente.getProyectosList().size()>=1){
                emailsWithContrato.add(cliente.getEmail());
            }
        }
        return emailsWithContrato;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllEmailsWtihOutContrato() {
        List<Cliente> listaClientes = clienteRepository.findAll();
        List<String> emailsWithContrato = new ArrayList<String>();

        for(Cliente cliente : listaClientes){
            if(cliente.getProyectosList().size()==0){
                emailsWithContrato.add(cliente.getEmail());
            }
        }
        return emailsWithContrato;
    }
}
