package com.renovations.jrl.apirestrenovations.Services.ProyectoServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Repositories.ClienteRepository;
import com.renovations.jrl.apirestrenovations.Repositories.ProyectoRepository;

@Service
public class ProyectoServicesImp implements ProyectoServices {

    @Autowired
    ProyectoRepository proyectoRepository;
    @Autowired
    ClienteRepository   clienteRepository;

    @Override
    public List<Proyecto> getAllProyectosClienteByEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        List<Proyecto> listaProyectos = cliente.getProyectosList();
        return listaProyectos;
    }

    @Override
    public Proyecto getProyectoByNumeroContrato(String numeroContrato) {
        Proyecto proyecto = proyectoRepository.findByNumeroContrato(numeroContrato);
        return proyecto;
    }

    @Override
    public List<Proyecto> getAllProyectosClientesById(Long id) {
        Cliente cliente = clienteRepository.findById(id).get();
        List<Proyecto> listaProyectos = cliente.getProyectosList();
        return listaProyectos;
    }

    @Override
    public Cliente registrarProyectoById(Proyecto proyecto, Long id) {

        List<Proyecto> allProyectos = proyectoRepository.findAll();
        for (Proyecto proyecto2 : allProyectos) {
            if (proyecto2.getNumeroContrato().equals(proyecto.getNumeroContrato())){
                Cliente cliente = clienteRepository.findByEmail(proyecto2.getEmailCliente());
                return cliente;
            }
        }

        Cliente cliente = clienteRepository.findById(id).get();
        List<Proyecto> listProyectos = cliente.getProyectosList();
        listProyectos.add(proyecto);
        cliente.setProyectosList(listProyectos);
        clienteRepository.save(cliente);
        return cliente;
    }

}
