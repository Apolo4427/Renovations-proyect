package com.renovations.jrl.apirestrenovations.Services;

import java.util.List;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.error.ClienteNoFundException;

public interface ServicesCliente {
    List<Cliente> getAllClientes();
    Cliente registrarCliente(Cliente cliente);
    Cliente actualizarCliente(Long id, Cliente cliente) throws ClienteNoFundException;
    String eliminarCliente(Long id);
    Cliente getClienteByEmail(String email) throws ClienteNoFundException;
    Cliente getClienteBYId(Long id) throws ClienteNoFundException;
    List<String> getAllEmails();
    List<String> getAllEamilsWithContrato();
    List<String> getAllEmailsWtihOutContrato();
}
