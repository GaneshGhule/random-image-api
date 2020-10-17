package com.gg.randomimage.exception;

import com.gg.randomimage.entity.Image;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class RandomImageException extends RuntimeException {

    ResponseEntity<Image> responseEntity;

    public RandomImageException(String message, ResponseEntity<Image> responseEntity) {
        super(message);
        this.responseEntity = responseEntity;
    }

}
