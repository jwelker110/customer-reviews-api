package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.domain.Product;
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

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    private int productId;

    @Before
    public void setup() {
        Product product = new Product();
        product.setName("Doo hickey");

        product = productRepository.saveAndFlush(product);
        productId = product.getId();
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productRepository).isNotNull();
    }

    @Test
    public void testFindById() {
        Product product = productRepository.findById(productId).orElse(null);

        assertThat(product).isNotNull();
    }

    @Test
    public void testInsertProduct() {
        Product newProduct = new Product();
        newProduct.setName("My new product");

        newProduct = productRepository.saveAndFlush(newProduct);

        assertThat(newProduct.getId()).isNotNull();
    }

    @Test
    public void testUpdateProduct() {
        Product product = productRepository.findById(productId).orElse(null);

        assertThat(product).isNotNull();

        String uuid = UUID.randomUUID().toString();

        assertThat(product.getName()).isNotEqualTo(uuid);

        product.setName(uuid);

        product = productRepository.saveAndFlush(product);

        assertThat(product.getName()).isEqualTo(uuid);
    }

    @Test
    public void testDeleteProduct() {
        Product product = productRepository.findById(productId).orElse(null);

        assertThat(product).isNotNull();

        productRepository.delete(product);

        Optional<Product> deletedProduct = productRepository.findById(productId);

        assertThat(deletedProduct.isPresent()).isFalse();
    }
}
