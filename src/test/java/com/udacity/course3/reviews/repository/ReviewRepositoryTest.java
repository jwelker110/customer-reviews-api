package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.domain.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.jdbc.core.*;
import org.springframework.test.context.junit4.*;

import javax.persistence.*;
import javax.sql.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    private int productId;
    private int reviewId;

    @Before
    public void setup() {
        Product product = new Product();
        product.setName("Doo hickey");

        product = productRepository.saveAndFlush(product);
        productId = product.getId();

        Review review = new Review();
        review.setAuthorName("Jane Doe");
        review.setContent("Bacon ipsum dolor fat");
        review.setProduct(product);

        review = reviewRepository.saveAndFlush(review);
        reviewId = review.getId();
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(reviewRepository).isNotNull();
    }

    @Test
    public void testFindByProductId() {
        List<Review> reviews = reviewRepository.findAllByProductId(productId);

        assertThat(reviews.size()).isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        Optional<Review> review = reviewRepository.findById(reviewId);

        assertThat(review.isPresent()).isTrue();
    }

    @Test
    public void testInsertReview() {
        Product productToReview = productRepository.findById(productId).orElse(null);

        assertThat(productToReview).isNotNull();

        Review newReview = new Review();
        newReview.setContent("This is the inserted Review");
        newReview.setAuthorName("Bobby Tables");
        newReview.setProduct(productToReview);

        newReview = reviewRepository.saveAndFlush(newReview);

        assertThat(newReview.getId()).isNotNull();
    }

    @Test
    public void testUpdateReview() {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        assertThat(review).isNotNull();

        String uuid = UUID.randomUUID().toString();

        assertThat(review.getContent()).isNotEqualTo(uuid);

        review.setContent(uuid);

        review = reviewRepository.saveAndFlush(review);

        assertThat(review.getContent()).isEqualTo(uuid);
    }

    @Test
    public void testDeleteReview() {
        Review reviewToDelete = reviewRepository.findById(reviewId).orElse(null);

        assertThat(reviewToDelete).isNotNull();

        reviewRepository.delete(reviewToDelete);

        Optional<Review> deletedReview = reviewRepository.findById(reviewId);

        assertThat(deletedReview.isPresent()).isFalse();
    }

}
