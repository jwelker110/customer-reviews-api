package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.domain.Product;
import com.udacity.course3.reviews.domain.Review;
import com.udacity.course3.reviews.model.ReviewDto;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    private final ProductRepository productRepository;

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewsController(ProductRepository productRepository,
                             ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<Review> createReviewForProduct(
            @PathVariable("productId") Integer productId, @RequestBody ReviewDto reviewDto) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            Product value = product.get();

            Review review = new Review();
            review.setAuthorName(reviewDto.getAuthorName());
            review.setContent(reviewDto.getContent());
            review.setProduct(value);
            review = reviewRepository.save(review);

            value.getReviews().add(review);

            productRepository.saveAndFlush(value);

            return new ResponseEntity<>(review, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        List<Review> reviews = reviewRepository.findAllByProductId(productId);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}