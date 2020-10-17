package com.gg.randomimage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("image.rest.api")
@Component
@Data
public class RandomImageAPIProperties {
    private String url;
}
