
INSERT INTO pizzas (created_at, description, name, photo_url, price) VALUES ('2023-11-09T11:35:00','pizza salsa e mozzarella', 'Margherita','https://www.tavolartegusto.it/wp/wp-content/uploads/2020/05/Pizza-napoletana-Ricetta-della-Pizza-Napoletana-Pizza-Margherita.jpg', 5.00);
INSERT INTO pizzas (created_at, description, name, photo_url, price) VALUES ('2023-11-09T11:35:00','pizza con tanti formaggi sfiziosi', '4 formaggi','https://blog.giallozafferano.it/zeroglutine/wp-content/uploads/2020/04/IMG_5544-720x411.jpg', 6.00);
INSERT INTO ingredients (name) VALUES ('mozzarella');
INSERT INTO ingredients (name) VALUES ('pomodoro');
INSERT INTO ingredients (name) VALUES ('olive');
INSERT INTO ingredients (name) VALUES ('tonno');
INSERT INTO ingredients (name) VALUES ('verdure');
INSERT INTO ingredients (name) VALUES ('funghi');

INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('john@email.com', 'John', 'Doe', '2023-11-20 10:35', '{noop}john');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('jane@email.com', 'Jane', 'Smith', '2023-11-20 10:35','{noop}jane');

INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 2);
