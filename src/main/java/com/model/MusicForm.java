package com.model;

import org.springframework.web.multipart.MultipartFile;

public class MusicForm {
    private Long id;
    private String name;
    private MultipartFile link;
    private String category;

    public MusicForm() {
    }

    public MusicForm(Long id, String name, MultipartFile link, String category) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getLink() {
        return link;
    }

    public void setLink(MultipartFile link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
