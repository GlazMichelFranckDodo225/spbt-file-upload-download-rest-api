package com.dgmf.spbtfileuploaddownloadrestapi.service.implementation;

import com.dgmf.spbtfileuploaddownloadrestapi.entity.ImageData;
import com.dgmf.spbtfileuploaddownloadrestapi.repository.StorageRepository;
import com.dgmf.spbtfileuploaddownloadrestapi.service.interfaces.StorageService;
import com.dgmf.spbtfileuploaddownloadrestapi.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageRepository repository;

    // Stockage de l'image dans la DB
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                // A compresser avant l'envoie vers la DB
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());

        if(imageData != null) {
            return "File uploaded successfully : " + file.getOriginalFilename();
        }

        return null;
    }

    // Récupération de l'image stockée dans la DB
    @Override
    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        // Décompression des images (binaires) récupérées de la DB
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());

        return images;
    }


}
