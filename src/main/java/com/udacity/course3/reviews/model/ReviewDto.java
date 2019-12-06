package com.udacity.course3.reviews.model;

public class ReviewDto {

    private String content;

    private String authorName;

    public ReviewDto() {
    }

    public ReviewDto(String content, String authorName) {
        this.content = content;
        this.authorName = authorName;
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

}
