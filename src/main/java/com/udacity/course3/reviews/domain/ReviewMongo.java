package com.udacity.course3.reviews.domain;

import org.springframework.data.mongodb.core.mapping.*;

import javax.persistence.*;
import java.util.*;

@Document("review")
public class ReviewMongo {

    @Id
    private String id;

    private Integer productId;

    private Integer sqlId;

    private String content;

    private String authorName;

    private List<CommentMongo> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSqlId() {
        return sqlId;
    }

    public void setSqlId(Integer sqlId) {
        this.sqlId = sqlId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<CommentMongo> getComments() {
        return comments;
    }

    public void setComments(List<CommentMongo> comments) {
        this.comments = comments;
    }
}
