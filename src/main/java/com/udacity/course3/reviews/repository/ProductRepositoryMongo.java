package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.domain.*;
import org.springframework.data.mongodb.repository.*;

import java.util.*;

public interface ProductRepositoryMongo extends MongoRepository<ProductMongo, String> {

    Optional<ProductMongo> findBySqlId(Integer sqlId);

}
