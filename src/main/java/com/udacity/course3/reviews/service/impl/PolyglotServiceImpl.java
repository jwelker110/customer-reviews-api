package com.udacity.course3.reviews.service.impl;

import com.udacity.course3.reviews.domain.*;
import com.udacity.course3.reviews.repository.*;
import com.udacity.course3.reviews.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PolyglotServiceImpl implements PolyglotService {

    private final ReviewRepositoryMongo reviewRepositoryMongo;

    private final ReviewRepository reviewRepository;

    @Autowired
    public PolyglotServiceImpl(ReviewRepository reviewRepository,
                               ReviewRepositoryMongo reviewRepositoryMongo) {
        this.reviewRepository = reviewRepository;
        this.reviewRepositoryMongo = reviewRepositoryMongo;
    }

    @Override
    public ReviewMongo createReviewMongo(Review review) {
        ReviewMongo reviewMongo = new ReviewMongo();

        reviewMongo.setProductId(review.getProduct().getId());
        reviewMongo.setSqlId(review.getId());
        reviewMongo.setContent(review.getContent());
        reviewMongo.setAuthorName(review.getAuthorName());
        reviewMongo.setComments(new ArrayList<>());

        return reviewRepositoryMongo.save(reviewMongo);
    }

    @Override
    public List<ReviewMongo> getReviewMongoListForProduct(Integer productId) {
        List<Review> reviews = reviewRepository.findAllByProductId(productId);
        List<ReviewMongo> reviewMongos = new ArrayList<>();

        for (Review r : reviews) {
            Optional<ReviewMongo> reviewMongo = reviewRepositoryMongo.findBySqlId(r.getId());

            reviewMongo.ifPresent(reviewMongos::add);
        }

        return reviewMongos;
    }

    @Override
    public CommentMongo addCommentToReview(Integer reviewId, Comment comment) {
        Optional<ReviewMongo> reviewMongo = reviewRepositoryMongo.findBySqlId(reviewId);
        CommentMongo newComment = null;

        if (reviewMongo.isPresent()) {
            ReviewMongo review = reviewMongo.get();

            newComment = new CommentMongo();
            newComment.setContent(comment.getContent());
            newComment.setAuthorName(comment.getAuthorName());
            newComment.setId(comment.getId());

            review.getComments().add(newComment);

            reviewRepositoryMongo.save(review);
        }

        return newComment;
    }
}
