package com.renovations.jrl.apirestrenovations.Services.ImagenAntesServices;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.ImagenAntes;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Repositories.ProyectoRepository;

@Service
public class ImagenAntesServicesImp implements ImagenAntesServices{

    @Autowired
    ProyectoRepository proyectoRepository;

    @Override
    public ImagenAntes cargarImagenAntes(MultipartFile imagenAntes, Long ProyectoId) throws IOException {
        Proyecto proyecto = proyectoRepository.findByProyectoId(ProyectoId);
        List<ImagenAntes> listImagenes = proyecto.getImagenesAntes();
        ImagenAntes imagenNueva = ImagenAntes.builder().originalName(imagenAntes.getOriginalFilename())
                .data(imagenAntes.getBytes()).build();
        listImagenes.add(imagenNueva);
        proyecto.setImagenesAntes(listImagenes);

        return proyecto.getImagenesAntes().get(proyecto.getImagenesAntes().size()-1);
    }

    @Override
    public List<ImagenAntes> getAllImagenesAntes(Long proyectoId) {
        Proyecto proyecto = proyectoRepository.findByProyectoId(proyectoId);
        return proyecto.getImagenesAntes();
    }

}
