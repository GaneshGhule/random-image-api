package com.gg.randomimage.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ImageDTO {
    private Long imageId;
    private String url;
    private Instant cratedDate;
}
