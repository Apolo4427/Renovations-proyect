package com.renovations.jrl.apirestrenovations.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Entities.Filesproyecto;
import com.renovations.jrl.apirestrenovations.Entities.ImagenesAntes;
import com.renovations.jrl.apirestrenovations.Entities.ImagenesDespues;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Repositories.FilesRepository;
import com.renovations.jrl.apirestrenovations.Repositories.ImagenAntesRepository;
import com.renovations.jrl.apirestrenovations.Repositories.ImagenDespuesRepository;
import com.renovations.jrl.apirestrenovations.Repositories.ProyectoRepository;
import com.renovations.jrl.apirestrenovations.Services.ProyectoServices.ProyectoServicesImp;

@RestController
public class ProyectoController {

    @Autowired 
    ProyectoServicesImp proyectoServicesImp;

    //METODOS GET
    @GetMapping("/clientes/{id}/proyectos")
    public List<Proyecto> findAllProyectosById(@PathVariable Long id){
        List<Proyecto> listProyectos = proyectoServicesImp.getAllProyectosClientesById(id);
        return listProyectos;
    }

    @GetMapping("/clientes/email/proyectos")
    public List<Proyecto> findAllProyectosByEmail(@RequestParam String email){
        List<Proyecto> listPoyectos = proyectoServicesImp.getAllProyectosClienteByEmail(email);
        return listPoyectos;
    }

    @GetMapping("/clientes/proyectos/contrato")
    public Proyecto findProyectoByNumeroContrato(@RequestParam String numeroContrato){
        Proyecto proyecto = proyectoServicesImp.getProyectoByNumeroContrato(numeroContrato);
        return proyecto;
    }

    // @GetMapping("clientes/proyecyos/{id}/documentos")
    // public List<String> findDocumentList(@PathVariable Long id){
    //     List<String> documentos = proyectoServicesImp.getAllDocumentos(id);
    //     return documentos;
    // }

    //METODO POST
    @PostMapping("/clientes/{id}/proyectos/nuevoProyecto")
    public String saveProyecto(@RequestBody Proyecto proyecto, @PathVariable Long id){
        
        Boolean validarProyecto = proyectoServicesImp.validarProectoExistente(proyecto);

        if(!validarProyecto){
            Cliente cliente = proyectoServicesImp.registrarProyectoById(proyecto, id);
            List<Proyecto> proyectosCliente = cliente.getProyectosList();
            Proyecto proyectoGuardado = proyectosCliente.get(proyectosCliente.size()-1);
            return "el proyecto se aguardado exitosamente. Se a guardado en el cliente con email: "+proyectoGuardado.getEmailCliente();
        }
        Proyecto proyectoExistente = proyectoServicesImp.getProyectoByNumeroContrato(proyecto.getNumeroContrato());
        return "el numero de contrato que desas guardar ya existe en la base de datos de proyectos. y pertenece al cliente con email: "+proyectoExistente.getEmailCliente();
    }

    @Autowired
    private ProyectoRepository proyectoRepository;
    
    @Autowired
    private FilesRepository filesproyectoRepository;

    @Autowired
    private ImagenAntesRepository imagenAntesRepository;

    @Autowired
    private ImagenDespuesRepository imagenDespuesRepository;

    // @PostMapping
    // public ResponseEntity<Proyecto> crearProyecto(@RequestBody Proyecto proyecto) {
    //     Proyecto nuevoProyecto = proyectoRepository.save(proyecto);
    //     return new ResponseEntity<>(nuevoProyecto, HttpStatus.CREATED);
    // }

    @PostMapping("/clientes/proyectos/{id}/documentos")
    public ResponseEntity<Proyecto> subirDocumentos(@PathVariable Long id, @RequestParam("files") MultipartFile[] files) throws IOException {
        Proyecto proyecto = proyectoRepository.findById(id).orElseThrow();
        List<Filesproyecto> documentos = new ArrayList<>();
        for (MultipartFile file : files) {
            Filesproyecto nuevoArchivo = new Filesproyecto(null, file.getOriginalFilename(), file.getContentType(), file.getBytes());
            documentos.add(filesproyectoRepository.save(nuevoArchivo));
        }
        proyecto.setDocumentos(documentos);
        proyectoRepository.save(proyecto);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    @PostMapping("/cleintes/proyectos/{id}/imagenesAntes")
    public ResponseEntity<Proyecto> subirImagenesAntes(@PathVariable Long id, @RequestParam("files") MultipartFile[] files) throws IOException {
        Proyecto proyecto = proyectoRepository.findById(id).orElseThrow();
        List<ImagenesAntes> imagenesAntes = new ArrayList<>();
        for (MultipartFile file : files) {
            ImagenesAntes nuevaImagen = new ImagenesAntes(null, file.getOriginalFilename(), file.getContentType(), file.getBytes());
            imagenesAntes.add(imagenAntesRepository.save(nuevaImagen));
        }
        proyecto.setImagenesAntes(imagenesAntes);
        proyectoRepository.save(proyecto);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    @PostMapping("/cleintes/proyectos/{id}/imagenesDespues")
    public ResponseEntity<Proyecto> subirImagenesDespues(@PathVariable Long id, @RequestParam("files") MultipartFile[] files) throws IOException {
        Proyecto proyecto = proyectoRepository.findById(id).orElseThrow();
        List<ImagenesDespues> imagenesDespues = new ArrayList<>();
        for (MultipartFile file : files) {
            ImagenesDespues nuevaImagen = new ImagenesDespues(null, file.getOriginalFilename(), file.getContentType(), file.getBytes());
            imagenesDespues.add(imagenDespuesRepository.save(nuevaImagen));
        }
        proyecto.setImagenesDespues(imagenesDespues);
        proyectoRepository.save(proyecto);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    @GetMapping("/cleintes/proyectos/{id}/documentos/{fileId}")
    public ResponseEntity<byte[]> descargarDocumento(@PathVariable Long id, @PathVariable UUID fileId) {
        Filesproyecto file = filesproyectoRepository.findById(fileId).orElseThrow();
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=\"" + file.getOriginalName() + "\"")
            .body(file.getData());
    }

    @GetMapping("/cleintes/proyectos/{id}/imagenesAntes/{imagenId}")
    public ResponseEntity<byte[]> descargarImagenAntes(@PathVariable Long id, @PathVariable UUID imagenId) {
        ImagenesAntes imagen = imagenAntesRepository.findById(imagenId).orElseThrow();
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=\"" + imagen.getOriginalName() + "\"")
            .body(imagen.getData());
    }

    @GetMapping("/cleintes/proyectos/{id}/imagenesDespues/{imagenId}")
    public ResponseEntity<byte[]> descargarImagenDespues(@PathVariable Long id, @PathVariable UUID imagenId) {
        ImagenesDespues imagen = imagenDespuesRepository.findById(imagenId).orElseThrow();
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=\"" + imagen.getOriginalName() + "\"")
            .body(imagen.getData());
    }

    @GetMapping("/{id}/imagenesAntes")
    public ResponseEntity<List<String>> listarImagenesAntes(@PathVariable Long id) {
        Proyecto proyecto = proyectoRepository.findById(id).orElseThrow();
        List<String> imagenesAntesUrls = new ArrayList<>();
        for (ImagenesAntes imagen : proyecto.getImagenesAntes()) {
            String imagenBase64 = Base64.getEncoder().encodeToString(imagen.getData());
            imagenesAntesUrls.add("data:" + imagen.getType() + ";base64," + imagenBase64);
        }
        return ResponseEntity.ok(imagenesAntesUrls);
    }

    @GetMapping("/{id}/imagenesDespues")
    public ResponseEntity<List<String>> listarImagenesDespues(@PathVariable Long id) {
        Proyecto proyecto = proyectoRepository.findById(id).orElseThrow();
        List<String> imagenesDespuesUrls = new ArrayList<>();
        for (ImagenesDespues imagen : proyecto.getImagenesDespues()) {
            String imagenBase64 = Base64.getEncoder().encodeToString(imagen.getData());
            imagenesDespuesUrls.add("data:" + imagen.getType() + ";base64," + imagenBase64);
        }
        return ResponseEntity.ok(imagenesDespuesUrls);
    }

}
