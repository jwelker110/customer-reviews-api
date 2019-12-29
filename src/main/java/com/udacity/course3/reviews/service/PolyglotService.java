package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.domain.*;

public interface PolyglotService {

    ProductMongo createProductMongo(Product product);

    ReviewMongo createReviewMongo(Review review);

    CommentMongo createCommentMongo(Comment comment);

}
