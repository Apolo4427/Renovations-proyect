package com.renovations.jrl.apirestrenovations.Services.ProyectoServices;

import java.util.List;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;

public interface ProyectoServices {
    List<Proyecto> getAllProyectosClienteByEmail(String email);
    List<Proyecto> getAllProyectosClientesById(Long id);
    Proyecto getProyectoByNumeroContrato(String numeroContrato);
    List<String> getAllDocumentos(Long id);
    Cliente registrarProyectoById(Proyecto proyecto, Long id);
    List<String> agregarDocumento(String documento, Long id);
    Boolean validarProectoExistente(Proyecto proyecto);

}
