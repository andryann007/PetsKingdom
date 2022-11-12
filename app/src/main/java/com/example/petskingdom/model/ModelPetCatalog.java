package com.example.petskingdom.model;

public class ModelPetCatalog {
    private int imageId, favoriteOutlined;
    private String title, description, category, salesCount;

    public int getImageId(){
        return imageId;
    }

    public void setImageId(int imageId){
        this.imageId = imageId;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){ this.title = title; }

    public String getDescription(){ return description; }

    public void setDescription(String description){ this.description = description; }

    public int getFavoriteOutlined(){
        return favoriteOutlined;
    }

    public void setFavoriteOutlined(int favoriteOutlined) {this.favoriteOutlined = favoriteOutlined;}

    public String getCategory(){ return category; }

    public void setCategory(String category){ this.category = category; }

    public String getSalesCount(){ return salesCount; }

    public void setSalesCount(String salesCount){ this.salesCount = salesCount; }
}
