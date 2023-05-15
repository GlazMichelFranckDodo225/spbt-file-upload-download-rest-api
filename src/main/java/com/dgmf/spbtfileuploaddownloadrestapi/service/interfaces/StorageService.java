package com.dgmf.spbtfileuploaddownloadrestapi.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String uploadImage(MultipartFile file) throws IOException;
    public byte[] downloadImage(String fileName);
}
