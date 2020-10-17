package com.gg.randomimage.controller;

import com.gg.randomimage.dto.ImageDTO;
import com.gg.randomimage.exception.RandomImageException;
import com.gg.randomimage.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
