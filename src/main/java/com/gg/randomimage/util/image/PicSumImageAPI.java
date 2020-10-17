package com.gg.randomimage.util.image;

import com.gg.randomimage.config.RandomImageAPIProperties;
import com.gg.randomimage.entity.Image;
import com.gg.randomimage.exception.RandomImageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component("imageAPI")
public class PicSumImageAPI implements ImageAPI {


    private static final Logger logger = LoggerFactory.getLogger(PicSumImageAPI.class);

    private final RestTemplate restTemplate;

    private final RandomImageAPIProperties randomImageAPIProperties;

    @Autowired
    PicSumImageAPI(RestTemplate restTemplate, RandomImageAPIProperties randomImageAPIProperties) {
        this.restTemplate = restTemplate;
        this.randomImageAPIProperties = randomImageAPIProperties;
    }

    @Override
    public Image fetchImage(Long imageId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<Image> response = restTemplate.exchange(randomImageAPIProperties.getUrl(), HttpMethod.GET, request, Image.class, imageId);
        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("Fetched image info  from {} successfully {}", randomImageAPIProperties.getUrl(), imageId);
        } else {
            logger.info("Failed to fetch image info  from {} , id {}, http status {}", randomImageAPIProperties.getUrl(), imageId, response.getStatusCode());
            throw new RandomImageException("Failed to fetch random image", response);
        }
        return response.getBody();
    }
}
