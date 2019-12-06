CREATE TABLE IF NOT EXISTS product (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS review (
  id INT(11) NOT NULL AUTO_INCREMENT,
  content longtext NOT NULL,
  author_name NVARCHAR(50) NOT NULL,
  product_id INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT review_product_id_fk FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS comment
(
	id INT auto_increment,
	content LONGTEXT NOT NULL,
	author_name NVARCHAR(50) NOT NULL,
	review_id INT NOT NULL,
	CONSTRAINT comment_id_pk
		PRIMARY KEY (id),
	CONSTRAINT comment_review_id_fk
		FOREIGN KEY (review_id) REFERENCES review (id)
);






