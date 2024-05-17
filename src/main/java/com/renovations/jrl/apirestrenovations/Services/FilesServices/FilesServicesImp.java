package com.renovations.jrl.apirestrenovations.Services.FilesServices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renovations.jrl.apirestrenovations.Entities.Filesproyecto;
import com.renovations.jrl.apirestrenovations.Repositories.FilesRepository;
import com.renovations.jrl.apirestrenovations.Response.ResponseFile;

@Service
public class FilesServicesImp implements FilesServices {

    @Autowired
    FilesRepository filesRepository;

    @Override
    public Filesproyecto almacenarFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Filesproyecto fileCargado = Filesproyecto.builder().originalName(fileName)//asigna el nombre 
                                                            .type(file.getContentType())//ontiene el tipo de archivo
                                                            .data(file.getBytes())//obtiene el arreglo de bytes que componene el archivo
                                                            .build();
        return filesRepository.save(fileCargado);
    }

    @Override
    public Optional<Filesproyecto> getFile(UUID id) throws FileNotFoundException {
        Optional<Filesproyecto> file = filesRepository.findById(id);
        if(file.isPresent()){
            return file;
        }
        throw new FileNotFoundException();
    }

    @Override
    public List<ResponseFile> getAllFiles() {
        return filesRepository.findAll().stream()
                .map(dbfile -> {
                    String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/fileManager/files/")
                            .path(dbfile.getId().toString())
                            .toUriString();
                    return ResponseFile.builder()
                            .name(dbfile.getOriginalName())
                            .url(fileUrl)
                            .type(dbfile.getType())
                            .size((long) dbfile.getData().length)
                            .build();
                })
                .collect(Collectors.toList());
    }

}
