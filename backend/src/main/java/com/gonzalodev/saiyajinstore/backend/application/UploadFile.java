package com.gonzalodev.saiyajinstore.backend.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFile {

    private final String FOLDER = "src/main/resources/static/images/";
    private final String IMG_DEFAULT = "default.jpg";
    private final String URL = "http://localhost:8080/images/";

    public String upload(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return URL + IMG_DEFAULT;
        }

        // Crete file if...
        Path folderPath = Paths.get(FOLDER);
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        // Save
        String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        Path filePath = folderPath.resolve(fileName);
        Files.write(filePath, multipartFile.getBytes());

        return URL + fileName;
    }

    public void delete(String fileName) {
        File file = new File(FOLDER + fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
