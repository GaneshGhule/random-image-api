package com.gg.randomimage.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
public class Image {

    @Id
    private Long id;

    @Column
    private String url;

    @Column
    private String author;

    @Column
    private int width;

    @Column
    private int height;

    @Column
    private String downloadUrl;

    @Column
    private Instant createdDate;

    //{"id":"0","author":"Alejandro Escamilla","width":5616,"height":3744,"url":"https://unsplash.com/photos/yC-Yzbqy7PY","download_url":"https://picsum.photos/id/0/5616/3744"}
}
