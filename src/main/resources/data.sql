INSERT INTO "PRODUCT" VALUES
(1, NULL, 150.0, 150.0, 150.0, 'Milk', 150.0, 0.0),
(2, NULL, 250.0, 150.0, 250.0, 'Almond milk', 150.0, 0.0),
(3, NULL, 80.0, 150.0, 50.0, 'Oat milk', 150.0, 0.0),
(4, NULL, 150.0, 150.0, 250.0, 'milk fit', 150.0, 0.0),
(5, NULL, 150.0, 150.0, 350.0, 'fit chocolate', 150.0, 0.0),
(6, NULL, 150.0, 150.0, 150.0, 'dark chocolate', 150.0, 0.0),
(7, NULL, 150.0, 150.0, 150.0, 'product 7', 150.0, 0.0),
(8, NULL, 150.0, 150.0, 150.0, 'product 8', 150.0, 0.0),
(9, NULL, 150.0, 150.0, 150.0, 'product 9', 150.0, 0.0);

INSERT INTO "AUTHORITIES" VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');


INSERT INTO "USERS" VALUES
(1, FALSE, FALSE, FALSE, 'admin@gmail.com', TRUE, '$2a$10$sarwiTVmQk/TNkUgSCmTuu9Xx8fdPgQCNp8CO6YKl2bWYjkt/Xzm2', 'admin'),
(2, FALSE, FALSE, FALSE, 'admin2@gmail.com', TRUE, '$2a$10$mbiICv6XHRGLcKE6UxbmKux3BE96Igx8Nv4HDaNiGdgK0Ap5H4RxK', 'admin2');

INSERT INTO AUTHORITIES_LIST (USER_ID,AUTHORITY_ID) VALUES
(1, 1),
(1, 2),
(2, 2);

INSERT INTO "MEAL" VALUES
(1, DATE '2020-04-24', 'Sniadanie', 1),
(2, DATE '2020-04-24', 'Sniadanie2', 1);

INSERT INTO "MEALS_LIST" VALUES
(1, 100, 1, 1),
(2, 100, 1, 2),
(3, 50, 1, 3),
(4, 50, 1, 2),
(5, 50, 1, 3),
(6, 50, 1, 2),
(7, 50, 1, 3),
(8, 50, 1, 2),
(9, 50, 1, 3),
(10, 50, 2, 1),
(11, 50, 2, 1),
(12, 50, 2, 2),
(13, 50, 2, 3),
(14, 50, 2, 4),
(15, 50, 2, 5),
(16, 50, 2, 6);

