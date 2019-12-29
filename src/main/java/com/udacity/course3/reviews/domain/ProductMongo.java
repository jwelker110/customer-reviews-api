package com.udacity.course3.reviews.domain;

import org.springframework.data.mongodb.core.mapping.*;

import javax.persistence.*;
import java.util.*;

@Document("product")
public class ProductMongo {

    @Id
    private String id;

    private Integer sqlId;

    private String name;

    private List<ReviewMongo> reviews;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSqlId() {
        return sqlId;
    }

    public void setSqlId(Integer sqlId) {
        this.sqlId = sqlId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReviewMongo> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewMongo> reviews) {
        this.reviews = reviews;
    }
}
