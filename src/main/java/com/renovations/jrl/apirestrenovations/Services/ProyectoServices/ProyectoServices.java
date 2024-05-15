package com.renovations.jrl.apirestrenovations.Services.ProyectoServices;

import java.util.List;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;

public interface ProyectoServices {
    List<Proyecto> getAllProyectosClienteByEmail(String email);
    List<Proyecto> getAllProyectosClientesById(Long id);
    Proyecto getProyectoByNumeroContrato(String numeroContrato);
    Cliente registrarProyectoById(Proyecto proyecto, Long id);
}
