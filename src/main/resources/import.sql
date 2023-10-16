INSERT INTO products (description, name, price, url) VALUES('Mug Beware of the Dog with the image of the legendary mfloor mosaic "Cave Canem", found in the House of the Tragic Poet in Pompeii', 'Cave Canem mug', 7.39, 'https://www.museum-shop.it/cdn/shop/products/tazza-cave-canem-12524990169191.jpg');
INSERT INTO products (description, name, price, url) VALUES('Time travel capable of transporting you in an instant a world that no longer exists.', 'Luciano De Crescenzo The Naples of Bellavista', 18.89, 'https://www.museum-shop.it/cdn/shop/products/Luciano-De-Crescenzo-La-Napoli-di-Bellavista-Photographic-Front-Museum-shop.jpg');
INSERT INTO products (description, name, price, url) VALUES('Borsa per la spesa Leonardo Da Vinci', 'Vitruviano Leonardo Da Vinci', 9.99, 'https://www.museum-shop.it/cdn/shop/products/shopping-bag-Vitruvian-Man-leonardo-da-vinci-museum-shop-italy.jpg');
INSERT INTO products (description, name, price, url) VALUES('Smooth, comfortable and light structure.', 'Ballpoint pen', 10.89, 'https://www.museum-shop.it/cdn/shop/products/Ballpoint-pen-plastic-creation-museum-shop-italy.jpg');
INSERT INTO products (description, name, price, url) VALUES('Set di 5 cartoline', 'Musei Vaticani', 10.89, 'https://static.museivaticani.va/images/CARTOLINE_KIT_1.png');
INSERT INTO products (description, name, price, url) VALUES('Portachiavi', 'Porta chiavi museo', 5.89, 'https://images.pexels.com/photos/16163765/pexels-photo-16163765/free-photo-of-ciondolo-tigre.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');
INSERT INTO products (description, name, price, url) VALUES('Porta candele', 'Porta candele museo', 5.89, 'https://images.pexels.com/photos/3779019/pexels-photo-3779019.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');
INSERT INTO products (description, name, price, url) VALUES('A nice mug: for a cup of tea or a large cup of coffee. Dishwasher and microwave safe.', 'Mug, Rijksmuseum', 8.39, 'https://cdn.webshopapp.com/shops/290353/files/312012003/mug-rijksmuseum-logo.jpg');

INSERT INTO macro_category (name, description) VALUES ('Cancelleria', 'La nostra cancelleria');
INSERT INTO macro_category (name, description) VALUES ('Accessori', 'I nostri accessori');

INSERT INTO categories (description, name, macro_category_id) VALUES ('Le nostre tazze', 'Tazze', 2);
INSERT INTO categories (description, name, macro_category_id) VALUES ('I nostri libri', 'Libri', 1);
INSERT INTO categories (description, name, macro_category_id) VALUES ('Le nostre borse', 'Borse', 2);
INSERT INTO categories (description, name, macro_category_id) VALUES ('Le nostre penne', 'Penne', 1);
INSERT INTO categories (description, name, macro_category_id) VALUES ('Le nostre cartoline', 'Cartoline', 1);
INSERT INTO categories (description, name, macro_category_id) VALUES ('I nostri portachiavi', 'Portachiavi', 2);
INSERT INTO categories (description, name, macro_category_id) VALUES ('I nostri porta candele', 'Porta candele', 2);


INSERT INTO products_categories (categories_id, products_id) VALUES (1,1);
INSERT INTO products_categories (categories_id, products_id) VALUES (2,2);
INSERT INTO products_categories (categories_id, products_id) VALUES (3,3);
INSERT INTO products_categories (categories_id, products_id) VALUES (4,4);
INSERT INTO products_categories (categories_id, products_id) VALUES (5,5);
INSERT INTO products_categories (categories_id, products_id) VALUES (6,6);
INSERT INTO products_categories (categories_id, products_id) VALUES (7,7);
INSERT INTO products_categories (categories_id, products_id) VALUES (1,8);


INSERT INTO roles(id, name) VALUES(1, 'ADMIN');
INSERT INTO roles(id, name) VALUES(2, 'USER');

INSERT INTO users (id, first_name, last_name, registration_date, username, password) VALUES (1,'Admin', '1', '2023-01-01', 'admin1', '{noop}admin');
INSERT INTO users (id, first_name, last_name, registration_date, username, password) VALUES (2,'User', '1', '2023-05-05', 'user1', '{noop}user');


INSERT INTO users_roles(user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles(user_id, roles_id) VALUES(2, 2);

INSERT INTO turist_visit(name, description, date, price, url) VALUES ('Visita al museo', 'Visita al museo', '2023-11-10', 49.99, 'https://images.pexels.com/photos/2570059/pexels-photo-2570059.jpeg?auto=compress&cs=tinysrgb&w=1600');
INSERT INTO turist_visit(name, description, date, price, url) VALUES ('Visita alla prima sala', 'Visita approfondita alla prima sala', '2023-11-10', 39.99, 'https://images.pexels.com/photos/2570059/pexels-photo-2570059.jpeg?auto=compress&cs=tinysrgb&w=1600');
INSERT INTO turist_visit(name, description, date, price, url) VALUES ('Visita alla seconda sala', 'Visita approfondita alla seconda sala', '2023-11-10', 39.99, 'https://images.pexels.com/photos/2570059/pexels-photo-2570059.jpeg?auto=compress&cs=tinysrgb&w=1600');
INSERT INTO turist_visit(name, description, date, price, url) VALUES ('Visita alla terza sala', 'Visita approfondita alla terza sala', '2023-11-10', 39.99, 'https://images.pexels.com/photos/2570059/pexels-photo-2570059.jpeg?auto=compress&cs=tinysrgb&w=1600');



