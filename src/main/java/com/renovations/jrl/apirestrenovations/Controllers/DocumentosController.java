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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.Documento;
import com.renovations.jrl.apirestrenovations.Services.DocumentosServices.DocumentosServiceImp;

@RestController
@RequestMapping("/clientes/proyecto")
public class DocumentosController {

    @Autowired
    DocumentosServiceImp documentosServiceImp;

    //Metodo GET
    @GetMapping("/{proyectoId}/documento")
    public ResponseEntity<Resource> getDocumento(@PathVariable Long proyectoId, @RequestParam String documentoId) throws FileNotFoundException{
        UUID id = UUID.fromString(documentoId);
        Documento documento = documentosServiceImp.getDocumento(id, proyectoId);
        if (documento != null && documento.getId().toString().equals(id.toString())) {
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documento.getName() + "\"")
                .body(new ByteArrayResource(documento.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{proyectoId}/documentos")
    public ResponseEntity<List<String>> getAllDocumentosNombres(@PathVariable Long proyectoId) {
        List<Documento> documentos = documentosServiceImp.getAllDocumentos(proyectoId);
        List<String> nombresDocumentos = documentos.stream()
                .map(Documento::getName)
                .collect(Collectors.toList());
        return ResponseEntity.ok(nombresDocumentos);
    }

    //Metodo POST
    @PostMapping("/{id}/nuevoDocumento")
    public ResponseEntity<String> saveDocumento(@RequestParam("file") MultipartFile documento, @PathVariable Long proyectoId) throws IOException {
        Documento documentoGuardado = documentosServiceImp.cargarDocumento(documento, proyectoId);
        return ResponseEntity.ok().body("el documento, con el nombre "+ documentoGuardado.getName()+" se guardado exsitosamente");
    }

}
