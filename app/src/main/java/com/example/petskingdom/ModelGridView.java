package com.example.petskingdom;

public class ModelGridView {
    private int imageId;
    private String title, description;

    public int getImageId(){
        return imageId;
    }

    public void setImageId(){
        this.imageId = imageId;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(){ this.title = title; }

    public String getDescription(){ return description; }

    public void setDescription(){ this.description = description; }

    ModelGridView(int images, String title, String description){
        imageId = images;
        this.title = title;
        this.description = description;
    }
}
