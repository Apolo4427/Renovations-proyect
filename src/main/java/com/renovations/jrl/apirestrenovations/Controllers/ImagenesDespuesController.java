package com.renovations.jrl.apirestrenovations.Controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.DTO.ImagenDTO;
import com.renovations.jrl.apirestrenovations.Entities.ImagenDespues;
import com.renovations.jrl.apirestrenovations.Services.ImagenDespuesServices.ImagenDespuesServicesImp;

@RestController
@RequestMapping("/clientes/proyectos")
public class ImagenesDespuesController {

    @Autowired
    ImagenDespuesServicesImp     imagenDespuesServicesImp;

    //Metodo GET
    @GetMapping("/{ProyectoId}/imagenesDespues")
    public ResponseEntity<List<ImagenDTO>> getAllImagenesDespues(@PathVariable Long imagenDespues){
        
        List<ImagenDespues> listImagenesDespues = imagenDespuesServicesImp.getAllImagenesDespues(imagenDespues);
        List<ImagenDTO> listImagenesDTO = listImagenesDespues.stream().map(imagen->{
            return new ImagenDTO(imagen.getOriginalName(), Base64.encodeBase64String(imagen.getData()));
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listImagenesDTO);
    }

    @PostMapping("/{ProyectoId}/nuevaImagenDespues")
    public ResponseEntity<String> cargarImagenDespues(@PathVariable Long proyectoId, @RequestParam(name = "imagen") MultipartFile imagenDespues) throws IOException{
        ImagenDespues nuevaImagen = imagenDespuesServicesImp.cargarImagenDespues(imagenDespues, proyectoId);
        return ResponseEntity.ok("La imagen se aguardado con exito, con nombre: "+nuevaImagen.getOriginalName());
    }

}
