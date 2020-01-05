package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.domain.*;
import org.springframework.data.mongodb.repository.*;

import java.util.*;

public interface ReviewRepositoryMongo extends MongoRepository<ReviewMongo, String> {

    List<ReviewMongo> findAllByProductId(Integer productId);

    Optional<ReviewMongo> findBySqlId(Integer sqlId);

}
