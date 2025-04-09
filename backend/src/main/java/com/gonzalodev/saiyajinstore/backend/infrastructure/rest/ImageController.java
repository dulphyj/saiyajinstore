package com.gonzalodev.saiyajinstore.backend.infrastructure.rest;

import com.gonzalodev.saiyajinstore.backend.application.UploadFile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@RestController
//@RequestMapping("/api/images")
//@CrossOrigin(origins = "http://localhost:4200")
//@Slf4j
//@AllArgsConstructor
public class ImageController {
    /*private final UploadFile uploadFile;

    @PostMapping("/admin")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestPart("file") MultipartFile multipartFile) {
        try {
            String imageUrl = uploadFile.upload(multipartFile);
            Map<String, String> response = new HashMap<>();
            response.put("url", imageUrl);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            log.error("Error uploading image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error uploading image."));
        }
    }

    @DeleteMapping("/admin/{fileName}")
    public ResponseEntity<String> deleteImage(@PathVariable String fileName) {
        uploadFile.delete(fileName);
        return ResponseEntity.ok("Image deleted successfully.");
    }*/
}
