package com.udacity.course3.reviews.service.impl;

import com.udacity.course3.reviews.domain.*;
import com.udacity.course3.reviews.repository.*;
import com.udacity.course3.reviews.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PolyglotServiceImpl implements PolyglotService {

    private final ProductRepositoryMongo productRepositoryMongo;

    @Autowired
    public PolyglotServiceImpl(ProductRepositoryMongo productRepositoryMongo) {
        this.productRepositoryMongo = productRepositoryMongo;
    }

    /**
     * Using this to check whether the Product SQL Entity has already been saved in the MongoDB collection. Ideally, all
     * SQL Entities will already be saved in the MongoDB collection, but this should let us be more confident in that
     * assertion.
     */
    private ProductMongo createProductMongoIfNotExists(Product product) {
        return this.productRepositoryMongo.findBySqlId(product.getId())
                .orElseGet(() -> this.createProductMongo(product));
    }

    @Override
    public ProductMongo createProductMongo(Product product) {
        ProductMongo newProduct = new ProductMongo();
        newProduct.setSqlId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setReviews(Collections.emptyList());

        return this.productRepositoryMongo.save(newProduct);
    }

    @Override
    public ReviewMongo createReviewMongo(Review review) {
        ProductMongo productMongo = this.createProductMongoIfNotExists(review.getProduct());

        ReviewMongo newReview = new ReviewMongo();
        newReview.setId(review.getId());
        newReview.setAuthorName(review.getAuthorName());
        newReview.setContent(review.getContent());
        newReview.setComments(Collections.emptyList());

        productMongo.getReviews().add(newReview);

        this.productRepositoryMongo.save(productMongo);

        return newReview;
    }

    @Override
    public CommentMongo createCommentMongo(Comment comment) {
        ProductMongo productMongo = this.createProductMongoIfNotExists(comment.getReview().getProduct());

        if (productMongo.getReviews().isEmpty()) {
            ReviewMongo newReview = new ReviewMongo();
            newReview.setId(comment.getReview().getId());
            newReview.setAuthorName(comment.getReview().getAuthorName());
            newReview.setContent(comment.getReview().getContent());
            newReview.setComments(Collections.emptyList());
            productMongo.getReviews().add(newReview);
        }

        CommentMongo newComment = new CommentMongo();
        newComment.setId(comment.getId());
        newComment.setAuthorName(comment.getAuthorName());
        newComment.setContent(comment.getContent());

        for (ReviewMongo r : productMongo.getReviews()) {
            if (r.getId().equals(comment.getReview().getId())) {
                r.getComments().add(newComment);
                break;
            }
        }

        this.productRepositoryMongo.save(productMongo);

        return newComment;
    }
}
