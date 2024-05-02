package com.renovations.jrl.apirestrenovations.Services;

import java.util.List;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;

public interface ServicesCliente {
    List<Cliente> getAllClientes();
    Cliente registrarCliente(Cliente cliente);
    Cliente actualizarCliente(Long id, Cliente cliente);
    String eliminarCliente(Long id);
    Cliente getClienteByEmail(String email);
}
