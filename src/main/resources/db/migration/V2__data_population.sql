INSERT INTO product
    (name)
VALUES ('Playing Cards'),
       ('Blender'),
       ('Laptop');

INSERT INTO review
    (content, author_name, product_id)
VALUES ('Lorem ipsum dolor sit amet consectetur adipiscing elit tortor accumsan nullam, quam laoreet tempus duis dapibus vel sagittis felis vivamus et, tincidunt venenatis ultricies eu dignissim nisl a mus aliquam.',
        'John Doe', 1),
       ('Lorem ipsum dolor sit amet consectetur adipiscing elit tortor accumsan nullam, quam laoreet tempus duis dapibus.',
        'Tim Robinson', 2);

INSERT INTO comment
    (content, author_name, review_id)
VALUES ('Lorem ipsum dolor sit amet consectetur adipiscing elit', 'Alfred A.', 1),
       ('Lorem ipsum sit', 'John Doe', 1);
