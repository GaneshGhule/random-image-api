package com.gg.randomimage.service;

import com.gg.randomimage.dto.ImageDTO;
import com.gg.randomimage.entity.Image;
import com.gg.randomimage.repository.ImageRepository;
import com.gg.randomimage.util.image.ImageAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;

@Service
public class ImageService {
    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    private final ImageRepository imageRepository;

    private final ImageAPI imageAPI;

    @Autowired
    public ImageService(ImageRepository imageRepository, @Qualifier("imageAPI") ImageAPI imageAPI) {
        this.imageRepository = imageRepository;
        this.imageAPI = imageAPI;
    }

    @Transactional
    public Image saveImage(Long imageId) {

        logger.info("Generating random image ");
        Image image = imageAPI.fetchImage(imageId);
        image.setCreatedDate(Instant.now());
        return imageRepository.save(image);
    }

    public ImageDTO getImage(Long imageId) {
        Optional<Image> optionalImage = imageRepository.findById(imageId);

        optionalImage.ifPresent((image) -> {
            logger.info("Fetched image from database imageId {}", image.getId());
        });

        Image image = optionalImage.orElseGet(() -> saveImage(imageId));
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImageId(image.getId());
        imageDTO.setUrl(image.getUrl());
        imageDTO.setCratedDate(image.getCreatedDate());
        return imageDTO;
    }
}
