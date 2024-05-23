package com.renovations.jrl.apirestrenovations.Services.ImagenDespuesServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renovations.jrl.apirestrenovations.Repositories.ProyectoRepository;

@Service
public class ImagenDespuesServicesImp implements ImagenDespuesServices{

    @Autowired
    ProyectoRepository proyectoRepository;
}
