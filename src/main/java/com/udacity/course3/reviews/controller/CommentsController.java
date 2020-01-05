package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.domain.*;
import com.udacity.course3.reviews.model.CommentDto;
import com.udacity.course3.reviews.repository.*;
import com.udacity.course3.reviews.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentRepository commentRepository;

    private final ReviewRepository reviewRepository;

    private final PolyglotService polyglotService;

    @Autowired
    public CommentsController(CommentRepository commentRepository,
                              ReviewRepository reviewRepository,
                              PolyglotService polyglotService) {
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
        this.polyglotService = polyglotService;
    }

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<Comment> createCommentForReview(
            @PathVariable("reviewId") Integer reviewId, @RequestBody CommentDto commentDto) {
        Optional<Review> review = reviewRepository.findById(reviewId);

        if (review.isPresent()) {
            final Review value = review.get();

            Comment comment = new Comment();
            comment.setAuthorName(commentDto.getAuthorName());
            comment.setContent(commentDto.getContent());
            comment.setReview(value);
            comment = commentRepository.saveAndFlush(comment);

            review.get().getComments().add(comment);

            reviewRepository.saveAndFlush(value);

            polyglotService.addCommentToReview(reviewId, comment);

            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);

        if (!review.isPresent()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }

        List<Comment> comments = commentRepository.findAllByReviewId(reviewId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}