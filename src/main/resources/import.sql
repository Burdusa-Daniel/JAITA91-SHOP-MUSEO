INSERT INTO products (description, name, price, url) VALUES('Mug Beware of the Dog with the image of the legendary mfloor mosaic "Cave Canem", found in the House of the Tragic Poet in Pompeii', 'Cave Canem mug', 7.39, 'https://www.museum-shop.it/cdn/shop/products/tazza-cave-canem-12524990169191.jpg');
INSERT INTO products (description, name, price, url) VALUES('Time travel capable of transporting you in an instant a world that no longer exists.', 'Luciano De Crescenzo The Naples of Bellavista', 18.89, 'https://www.museum-shop.it/cdn/shop/products/Luciano-De-Crescenzo-La-Napoli-di-Bellavista-Photographic-Front-Museum-shop.jpg');
INSERT INTO products (description, name, price, url) VALUES('Borsa per la spesa Leonardo Da Vinci', 'Vitruviano Leonardo Da Vinci', 9.99, 'https://www.museum-shop.it/cdn/shop/products/shopping-bag-Vitruvian-Man-leonardo-da-vinci-museum-shop-italy.jpg');
INSERT INTO products (description, name, price, url) VALUES('Smooth, comfortable and light structure.', 'Ballpoint pen', 10.89, 'https://www.museum-shop.it/cdn/shop/products/Ballpoint-pen-plastic-creation-museum-shop-italy.jpg');
INSERT INTO products (description, name, price, url) VALUES('Set di 5 cartoline', 'Musei Vaticani', 10.89, 'https://static.museivaticani.va/images/CARTOLINE_KIT_1.png');

INSERT INTO categories (description, name) VALUES ('Tazze', 'Tazza');
INSERT INTO categories (description, name) VALUES ('Libri', 'Libro');
INSERT INTO categories (description, name) VALUES ('Borse', 'Borsa');
INSERT INTO categories (description, name) VALUES ('Penne', 'Penna');
INSERT INTO categories (description, name) VALUES ('Cartoline', 'Cartolina');



INSERT INTO products_categories (categories_id, products_id) VALUES (1,1);
INSERT INTO products_categories (categories_id, products_id) VALUES (2,2);
INSERT INTO products_categories (categories_id, products_id) VALUES (3,3);
INSERT INTO products_categories (categories_id, products_id) VALUES (4,4);
INSERT INTO products_categories (categories_id, products_id) VALUES (5,5);

INSERT INTO users (id, name) VALUES (1, 'Jaita91')