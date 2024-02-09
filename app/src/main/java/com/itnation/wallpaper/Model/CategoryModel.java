package com.itnation.wallpaper.Model;

public class CategoryModel {

    String categoryName;
    String categoryImageUrl;

    public CategoryModel(String categoryName, String categoryImageUrl) {
        this.categoryName = categoryName;
        this.categoryImageUrl = categoryImageUrl;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }
}
