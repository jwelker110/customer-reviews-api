package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.domain.Comment;
import com.udacity.course3.reviews.domain.Product;
import com.udacity.course3.reviews.domain.Review;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    private int reviewId;
    private int commentId;
    private int productId;

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

        Comment comment = new Comment();
        comment.setAuthorName("John Doe");
        comment.setContent("Lorem ipsum dolor set");
        comment.setReview(review);

        commentRepository.saveAndFlush(comment);
        commentId = comment.getId();
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productRepository).isNotNull();
        assertThat(reviewRepository).isNotNull();
        assertThat(commentRepository).isNotNull();
    }

    @Test
    public void testFindByReviewId() {
        List<Comment> reviewComments = commentRepository.findAllByReviewId(reviewId);

        assertThat(reviewComments.size()).isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        Comment foundComment = commentRepository.findById(commentId).orElse(null);

        assertThat(foundComment).isNotNull();
    }

    @Test
    public void testInsertComment() {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        assertThat(review).isNotNull();

        Comment newComment = new Comment();
        newComment.setReview(review);
        newComment.setAuthorName("Bobby Tables");
        newComment.setContent("This is the inserted comment");

        newComment = commentRepository.saveAndFlush(newComment);

        assertThat(newComment.getId()).isNotNull();
    }

    @Test
    public void testUpdateComment() {
        Comment comment = commentRepository.findById(commentId).orElse(null);

        assertThat(comment).isNotNull();

        String uuid = UUID.randomUUID().toString();

        assertThat(comment.getContent()).isNotEqualTo(uuid);

        comment.setContent(uuid);

        Comment updatedComment = commentRepository.saveAndFlush(comment);

        assertThat(updatedComment.getContent()).isEqualTo(uuid);
    }

    @Test
    public void testDeleteComment() {
        Comment comment = commentRepository.findById(commentId).orElse(null);

        assertThat(comment).isNotNull();

        commentRepository.delete(comment);

        Optional<Comment> deletedComment = commentRepository.findById(commentId);

        assertThat(deletedComment.isPresent()).isFalse();
    }

}
