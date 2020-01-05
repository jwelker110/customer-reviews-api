package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.domain.*;

import java.util.*;

public interface PolyglotService {

    ReviewMongo createReviewMongo(Review review);

    List<ReviewMongo> getReviewMongoListForProduct(Integer productId);

    CommentMongo addCommentToReview(Integer reviewId, Comment comment);
}
