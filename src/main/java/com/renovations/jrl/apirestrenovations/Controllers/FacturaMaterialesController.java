package com.renovations.jrl.apirestrenovations.Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.FacturasMateriales;
import com.renovations.jrl.apirestrenovations.Services.FacturasMaterialesServices.FacturaMaterialesServicesImp;

@RestController
@RequestMapping("/clientes/proyectos")
public class FacturaMaterialesController  {

    @Autowired 
    FacturaMaterialesServicesImp facturaMaterialesServicesImp;

    //Metodo get
    @GetMapping("/{proyectoId}/facturaMateriales")
    public ResponseEntity<Resource> findFactura(@PathVariable Long proyectoId, @RequestParam String facturaId) throws FileNotFoundException{
        UUID id = UUID.fromString(facturaId);
        FacturasMateriales facturaMateriales = facturaMaterialesServicesImp.getFactura(id, proyectoId);
        if(facturaMateriales != null && facturaMateriales.getId().toString().equals(id.toString())){
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + facturaMateriales.getOriginalName() + "\"")
                .body(new ByteArrayResource(facturaMateriales.getData()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{proyectoId}/facturasMateriales")
    public ResponseEntity<List<String>> findAllFacturas(@PathVariable Long proyectoId){
        List<FacturasMateriales> listFacturas = facturaMaterialesServicesImp.getAllFcturas(proyectoId);
        List<String> fechasFacturas = listFacturas.stream().map(FacturasMateriales::getFecha).collect(Collectors.toList());
        return ResponseEntity.ok(fechasFacturas);
    }

    //Metodo POST
    @PostMapping("/{proyectoId}/nuevaFactura")
    public ResponseEntity<String> cargarFacturaMaterial(@RequestParam("file") MultipartFile facturaMaterial, @PathVariable Long proyectoId, @RequestParam String fecha) throws IOException{
        FacturasMateriales facturaNueva = facturaMaterialesServicesImp.cargarFacturasMateriales(facturaMaterial, proyectoId, fecha);
        return ResponseEntity.ok("La factura se ha guardado exitosamente, con fecha de: "+facturaNueva.getFecha());
    }
}
