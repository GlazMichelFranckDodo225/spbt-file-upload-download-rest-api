package com.dgmf.spbtfileuploaddownloadrestapi.web;

import com.dgmf.spbtfileuploaddownloadrestapi.service.interfaces.StorageService;
import com.dgmf.spbtfileuploaddownloadrestapi.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private StorageService service;

    // Endpoint pour stockage l'image dans la DB
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK).body(file);
    }

    // Endpoint pour récupérer l'image stockée dans la DB
    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
        byte[] imageData = service.downloadImage(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
