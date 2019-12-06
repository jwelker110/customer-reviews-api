package com.udacity.course3.reviews.model;

public class CommentDto {

    private String content;

    private String authorName;

    private Integer reviewId;

    public CommentDto() {
    }

    public CommentDto(String content, String authorName, Integer reviewId) {
        this.content = content;
        this.authorName = authorName;
        this.reviewId = reviewId;
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

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }
}
