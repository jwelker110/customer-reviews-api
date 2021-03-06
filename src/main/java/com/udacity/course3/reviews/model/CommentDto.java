package com.udacity.course3.reviews.model;

public class CommentDto {

    private String content;

    private String authorName;

    public CommentDto() {
    }

    public CommentDto(String content, String authorName) {
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
