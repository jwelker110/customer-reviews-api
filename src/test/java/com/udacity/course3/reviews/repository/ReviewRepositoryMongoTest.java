package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.domain.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.data.mongo.*;
import org.springframework.data.mongodb.core.*;
import org.springframework.test.context.junit.jupiter.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;


@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ReviewRepositoryMongoTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ReviewRepositoryMongo reviewRepositoryMongo;

    private final Integer PRODUCT_ID = 9;
    private final Integer REVIEW_LIST_SIZE = 5;

    @BeforeEach
    public void setup() {

        for (int i = 0; i < REVIEW_LIST_SIZE; i++) {
            ReviewMongo review = new ReviewMongo();
            review.setProductId(PRODUCT_ID);
            review.setSqlId(i);
            review.setComments(Collections.emptyList());
            review.setAuthorName("John Doe ".concat(Integer.toString(i)));
            review.setContent("Lorem ipsum dolor sit amet");

            reviewRepositoryMongo.save(review);
        }
    }

    @AfterEach
    public void teardown() {
        reviewRepositoryMongo.deleteAll();
    }

    @Test
    public void testSaveReview() {
        ReviewMongo review = new ReviewMongo();
        review.setSqlId(1);
        ReviewMongo savedReview = reviewRepositoryMongo.save(review);

        assertThat(savedReview.getId()).isNotNull();
        assertThat(savedReview.getSqlId()).isEqualTo(1);

    }

    @Test
    public void testFindAllByProductId() {
        List<ReviewMongo> reviews = reviewRepositoryMongo.findAllByProductId(PRODUCT_ID);

        assertThat(reviews.size()).isEqualTo(REVIEW_LIST_SIZE);
    }

    @Test
    public void testFindBySqlId() {
        Optional<ReviewMongo> review = reviewRepositoryMongo.findBySqlId(0);

        assertThat(review.isPresent()).isTrue();
    }


}
