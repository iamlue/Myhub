package com.lue.pojo;

import java.awt.image.BufferedImageFilter;
import java.io.File;

public class Anime {
    private Integer id;
    private String name;
    private String href;
    private String local;
    private String num;
    private String title;
    private String brief;
    private String image;

    public Anime() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Anime [href=" + href + ", num=" + num + ", title=" + title + ", brief=" + brief + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
