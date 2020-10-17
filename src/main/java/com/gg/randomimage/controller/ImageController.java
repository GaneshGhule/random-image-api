package com.gg.randomimage.controller;

import com.gg.randomimage.exception.RandomImageException;
import com.gg.randomimage.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    private final ImageService imageService;

    @Autowired
    ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/random/{id}")
    public ResponseEntity getRandomImage(@PathVariable("id") Long imageId) {
        try {
            return ResponseEntity.ok(imageService.getImage(imageId));
        } catch (RandomImageException exception) {
            return ResponseEntity.status(exception.getResponseEntity().getStatusCode())
                    .body(exception.getMessage());
        }
    }
}
