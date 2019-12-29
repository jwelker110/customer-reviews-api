package com.udacity.course3.reviews.domain;

import java.util.*;

public class ReviewMongo {

    private Integer id;

    private String content;

    private String authorName;

    private List<CommentMongo> comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
