package com.mountblue.StackOverFlow.model;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long  imageId;

    @Column(name = "data", columnDefinition = "TEXT", length = 65536)
    private String data;

    public Image() {
    }

    public Image(String data) {
        this.data = data;
    }

    public Image(Long imageId, String data) {
        this.imageId = imageId;
        this.data = data;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
