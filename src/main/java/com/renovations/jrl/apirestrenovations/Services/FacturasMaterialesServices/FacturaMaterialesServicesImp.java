package com.renovations.jrl.apirestrenovations.Services.FacturasMaterialesServices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.FacturasMateriales;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Repositories.ProyectoRepository;

@Service
public class FacturaMaterialesServicesImp implements FacturasMaterialesServices{

    @Autowired
    ProyectoRepository proyectoRepository;

    @Override
    public FacturasMateriales cargarFacturasMateriales(MultipartFile facturaMaterial, Long proyectoId, String fecha)
            throws IOException {
        Proyecto proyecto = proyectoRepository.findByProyectoId(proyectoId);
        String orignalFilName = facturaMaterial.getOriginalFilename();
        if(orignalFilName== null){
            orignalFilName = "Factura_sin_nombre";
        }
        List<FacturasMateriales> listFacturas= proyecto.getFacturas_de_marteriales();
        FacturasMateriales facturaNueva = FacturasMateriales.builder().originalName(orignalFilName)
                .fecha(fecha)
                .data(facturaMaterial.getBytes()).build();
        listFacturas.add(facturaNueva);
        proyecto.setFacturas_de_marteriales(listFacturas);
        proyectoRepository.save(proyecto);
        
        return proyecto.getFacturas_de_marteriales().get(proyecto.getFacturas_de_marteriales().size()-0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturasMateriales> getAllFcturas(Long proyectoId) {
        Proyecto proyecto = proyectoRepository.findByProyectoId(proyectoId);
        return proyecto.getFacturas_de_marteriales();
    }

    @Override
    public FacturasMateriales getFactura(UUID id, Long proyectoId) throws FileNotFoundException {
        Proyecto proyecto = proyectoRepository.findByProyectoId(proyectoId);
        List<FacturasMateriales> listFacturas = proyecto.getFacturas_de_marteriales();
        for (FacturasMateriales facturaMateriales : listFacturas) {
            if(id.toString().equals(facturaMateriales.getId().toString())){
                return facturaMateriales;
            }
        }
        throw new FileNotFoundException();
    }

}
