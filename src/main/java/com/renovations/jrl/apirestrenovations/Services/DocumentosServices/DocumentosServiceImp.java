package com.renovations.jrl.apirestrenovations.Services.DocumentosServices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.Documento;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Repositories.ProyectoRepository;

@Service
public class DocumentosServiceImp implements DocumentosServices {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Override
    public Documento cargarDocumento(MultipartFile docmuento, Long id) throws IOException {
        Proyecto proyecto = proyectoRepository.findByProyectoId(id);
        String orignalFilName = docmuento.getOriginalFilename();
        if(orignalFilName==null){
            orignalFilName= "Documento_sin _nombre";
        }
        String fileName = StringUtils.cleanPath(orignalFilName);//obtener el nombre original del archivo
        List<Documento> documentos = proyecto.getDocumentos();//obtener lista de documentos del proyecto
        Documento documentoNuevo = Documento.builder().name(fileName)
                .data(docmuento.getBytes()).build();//crear el documento nuevo
        documentos.add(documentoNuevo);//agregar el documento nuevo a la lista de documentos
        proyecto.setDocumentos(documentos);//setear la lista al proyecto 
        proyectoRepository.save(proyecto);//guardar el proyecto

        return proyecto.getDocumentos().get(proyecto.getDocumentos().size()-1);//devolver el ultimo documento agregado al proyecto
    }

    @Override
    @Transactional(readOnly = true)
    public Documento getDocumento(UUID id, Long proyectoId) throws FileNotFoundException{
        Proyecto proyecto = proyectoRepository.findByProyectoId(proyectoId);
        List<Documento> documentos = proyecto.getDocumentos();
        for (Documento documento : documentos) {
            if(id.toString().equals(documento.getId().toString())){
                return documento;
            }
        }
        throw new FileNotFoundException();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Documento> getAllDocumentos(Long proyectoId) {
        Proyecto proyecto = proyectoRepository.findByProyectoId(proyectoId);
        return proyecto.getDocumentos();
    }

}
