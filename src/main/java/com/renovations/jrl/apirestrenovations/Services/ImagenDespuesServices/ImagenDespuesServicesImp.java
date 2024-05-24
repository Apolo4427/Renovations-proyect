package com.renovations.jrl.apirestrenovations.Services.ImagenDespuesServices;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.ImagenDespues;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Repositories.ProyectoRepository;

@Service
public class ImagenDespuesServicesImp implements ImagenDespuesServices{

    @Autowired
    ProyectoRepository proyectoRepository;

    @Override
    @Transactional
    public ImagenDespues cargarImagenDespues(MultipartFile imagenDespues, Long ProyectoId) throws IOException {
        Proyecto proyecto = proyectoRepository.findByProyectoId(ProyectoId);
        List<ImagenDespues> listImagenesDespues = proyecto.getImagenesDespues();

        ImagenDespues imagenNueva = ImagenDespues.builder().originalName(imagenDespues.getOriginalFilename())
            .data(imagenDespues.getBytes()).build();
        
        listImagenesDespues.add(imagenNueva);
        proyecto.setImagenesDespues(listImagenesDespues);
        proyectoRepository.save(proyecto);

        return proyecto.getImagenesDespues().getLast();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImagenDespues> getAllImagenesDespues(Long proyectoId) {
        Proyecto proyecto = proyectoRepository.findByProyectoId(proyectoId);
        return proyecto.getImagenesDespues();
    }
}
