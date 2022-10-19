package com.model;

public class Music {
    private Long id;
    private String name;
    private String link;
    private String category;

    public Music() {
    }

    public Music(Long id, String name, String link, String category) {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
