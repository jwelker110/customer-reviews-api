package com.udacity.course3.reviews.model;

public class ProductDto {

    private String name;

    public ProductDto() {
    }

    public ProductDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
