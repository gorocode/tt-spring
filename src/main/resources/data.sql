-- Data initialization for Spring Boot application
-- This script uses standard SQL that Spring Boot can handle

-- Products - Only insert if not already present
INSERT INTO product_entity (id, deleted, description, name, stock, url)
SELECT * FROM (VALUES
(1, false, 'Toasted bread slices with garlic butter.', 'Garlic Bread', 50, 'https://res.cloudinary.com/dcstkilp1/image/upload/v1748096768/tt/products/dcaybkkb9jlay1uz5krv.jpg'),
(2, false, 'Delicious crispy croquettes filled with ham.', 'Ham Croquettes', 40, 'https://res.cloudinary.com/dcstkilp1/image/upload/v1748096841/tt/products/oosawrtq9u60zznibj3v.jpg'),
(3, false, 'Tender calamari fried with a crispy coating, served with aioli sauce.', 'Fried Calamari', 30, 'https://res.cloudinary.com/dcstkilp1/image/upload/v1748097252/tt/products/rixraozdrtdfifcf1yhb.jpg'),
(4, false, 'Fried potatoes served with spicy brava sauce and aioli.', 'Bravas Potatoes', 60, 'https://res.cloudinary.com/dcstkilp1/image/upload/v1748097307/tt/products/r7po2h30cqh8s57ffcxc.jpg'),
(5, false, 'Empanadas filled with spiced beef.', 'Beef Empanadas', 45, 'https://bonkerz4food.com/wp-content/uploads/2020/08/empanadas-web-1.jpg'),
(6, false, 'Romaine lettuce, croutons, parmesan cheese, and Caesar dressing.', 'Caesar Salad', 35, 'https://cookingwithayeh.com/wp-content/uploads/2023/11/Healthy-Caesar-Salad-Without-Anchovies-SQ-5-500x500.jpg'),
(7, false, 'Tomatoes, cucumber, onions, olives, and feta cheese.', 'Greek Salad', 25, 'https://www.simplyrecipes.com/thmb/0NrKQlJ691l6L9tZXpL06uOuWis=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Simply-Recipes-Easy-Greek-Salad-LEAD-2-4601eff771fd4de38f9722e8cafc897a.jpg'),
(8, false, 'Quinoa, avocado, cucumber, tomatoes, and lemon dressing.', 'Quinoa Salad', 20, 'https://cookingwithayeh.com/wp-content/uploads/2021/09/Quinoa-Salad-0-500x375.jpg'),
(9, false, 'Beef patty with cheddar cheese, lettuce, and tomato.', 'Classic Burger', 50, 'https://popmenucloud.com/cdn-cgi/image/width=1920,height=1920,format=auto,fit=scale-down/niuhvjds/da1c26d2-6431-4bd8-a103-53751e5fa766.jpeg'),
(10, false, 'Pizza with tomato sauce, mozzarella cheese, and fresh basil.', 'Margherita Pizza', 40, 'https://safrescobaldistatic.blob.core.windows.net/media/2022/11/PIZZA-MARGHERITA.jpg'),
(11, false, 'Spaghetti pasta with homemade bolognese sauce.', 'Spaghetti Bolognese', 30, 'https://supervalu.ie/thumbnail/800x600/var/files/real-food/recipes/Uploaded-2020/spaghetti-bolognese-recipe.jpg'),
(12, false, 'Juicy beef steak served with mashed potatoes.', 'Beef Steak', 25, 'https://sodamndelish.com/wp-content/uploads/2024/08/Easy-3-Ingredient-Steak-Marinade-for-Juicy-Grilled-Perfection.jpg'),
(13, false, 'Paella with seafood, chicken, and vegetables.', 'Valencian Paella', 15, 'https://www.tapasmagazine.es/wp-content/uploads/2023/07/10-mejores-paellas-valencia-8.jpg'),
(14, false, 'Soft tacos with grilled chicken, onions, and cilantro.', 'Chicken Tacos', 50, 'https://www.jocooks.com/wp-content/uploads/2024/07/crispy-fried-chicken-tacos-1-22.jpg'),
(15, false, 'Spinach-filled ravioli with tomato sauce.', 'Spinach Ravioli', 20, 'https://sandraseasycooking.com/wp-content/uploads/2016/03/Ravioli-Saut%C3%A9ed-with-Spinach-5-720x720.jpg'),
(16, false, 'Classic lasagna with meat, béchamel sauce, and tomato.', 'Lasagna', 30, 'https://cafedelites.com/wp-content/uploads/2018/01/Mamas-Best-Lasagna-IMAGE-43.jpg'),
(17, false, 'Rich chocolate cake with creamy frosting.', 'Chocolate Cake', 25, 'https://www.hersheyland.com/content/dam/hersheyland/en-us/recipes/recipe-images/2-hersheys-perfectly-chocolate-chocolate-cake-recipe-hero.jpg'),
(18, false, 'Creamy flan with caramel sauce.', 'Homemade Flan', 35, 'https://www.allrecipes.com/thmb/IRe_odRpELH0WqH-t7w2MZ9YAzg=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/691985-f05d7a4bda2d498f8142af132a44d2ed.jpg'),
(19, false, 'Vanilla ice cream served with fresh fruits.', 'Vanilla Ice Cream', 50, 'https://www.savoryexperiments.com/wp-content/uploads/2021/06/Vanilla-Ice-Cream-9-500x375.jpg'),
(20, false, 'Natural mineral water.', 'Mineral Water', 100, 'https://static.wixstatic.com/media/6e20fa_69d567038b35483cbfa0584928afccf2~mv2.png/v1/fill/w_980,h_730,al_c,q_90,usm_0.66_1.00_0.01,enc_avif,quality_auto/6e20fa_69d567038b35483cbfa0584928afccf2~mv2.png'),
(21, false, 'Coca Cola soft drink in a can.', 'Coca Cola', 150, 'https://imagenes.heraldo.es/files/image_1920_1080/uploads/imagenes/2023/02/24/cocacola-web-1.jpeg'),
(22, false, 'Freshly squeezed orange juice.', 'Orange Juice', 80, 'https://www.organicfacts.net/wp-content/uploads/orangejuice-1.jpg'),
(23, false, 'Locally brewed craft beer with a smooth flavor.', 'Craft Beer', 50, 'https://shop.bion.biz/cdn/shop/products/wp00086.jpg?v=1707143438'),
(24, false, 'Cold tea with lemon and mint.', 'Iced Tea', 70, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTq5NIGBko34qIxtBvXVdMes2TqNwNDBtbTkg&s'),
(25, false, 'Fresh lemonade with mint leaves.', 'Lemonade', 60, 'https://tarateaspoon.com/wp-content/uploads/2023/07/classic-lemonade-sq-08.jpg'),
(26, false, 'Espresso coffee with steamed milk.', 'Caffe Latte', 90, 'https://dairyfarmersofcanada.ca/sites/default/files/image_file_browser/conso_recipe/2021/Cafe%20Latte.jpg'),
(27, false, 'Refreshing lemon granita.', 'Lemon Granita', 110, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4RRBemtdtJZXeetvgkYjSU3lwCok7aaLy3w&s'),
(28, false, 'Creamy strawberry milkshake.', 'Strawberry Milkshake', 40, 'https://assets.epicurious.com/photos/647df8cad9749492c4d5d407/1:1/w_4506,h_4506,c_limit/StrawberryMilkshake_RECIPE_053123_3599.jpg'),
(29, false, 'Mineral water with bubbles.', 'Sparkling Water', 100, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSN6nyx2hGgWKECcpKwKOrYvgjrpJJN1gulVA&s'),
(30, false, 'Strong and concentrated espresso coffee.', 'Espresso', 60, 'https://upload.wikimedia.org/wikipedia/commons/a/a5/Tazzina_di_caff%C3%A8_a_Ventimiglia.jpg'),
(31, false, 'Black coffee with hot water.', 'Americano', 70, 'https://i.blogs.es/139e0f/cafe-americano2/840_560.jpeg'),
(32, false, 'Smooth coffee with steamed milk.', 'Caf Latte', 50, 'https://dairyfarmersofcanada.ca/sites/default/files/image_file_browser/conso_recipe/2021/Cafe%20Latte.jpg'),
(33, false, 'Coffee with steamed milk foam and a touch of cocoa powder.', 'Cappuccino', 40, 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/00/Cappuccino_PeB.jpg/1200px-Cappuccino_PeB.jpg')
) AS temp_table(id, deleted, description, name, stock, url)
WHERE NOT EXISTS (SELECT 1 FROM product_entity);

-- Product Allergens - Only insert if table is empty
INSERT INTO product_allergens (product_id, allergen)
SELECT * FROM (VALUES
(1, 'GLUTEN'), (1, 'LACTOSE'),
(2, 'GLUTEN'), (2, 'EGG'), (2, 'SOY'),
(3, 'FISH'),
(4, 'CELERY'), (4, 'MUSTARD'),
(5, 'GLUTEN'), (5, 'SOY'),
(6, 'LACTOSE'),
(7, 'LACTOSE'),
(8, 'LACTOSE'),
(9, 'GLUTEN'), (9, 'LACTOSE'),
(10, 'GLUTEN'), (10, 'LACTOSE'),
(11, 'GLUTEN'), (11, 'SOY'),
(13, 'CRUSTACEAN'), (13, 'MOLLUSK'),
(14, 'GLUTEN'),
(15, 'GLUTEN'), (15, 'LACTOSE'),
(16, 'GLUTEN'), (16, 'LACTOSE'),
(17, 'GLUTEN'), (17, 'LACTOSE'),
(18, 'LACTOSE'),
(19, 'LACTOSE'),
(23, 'GLUTEN'),
(26, 'LACTOSE'),
(28, 'LACTOSE'),
(32, 'LACTOSE'),
(33, 'LACTOSE')
) AS temp_table(product_id, allergen)
WHERE NOT EXISTS (SELECT 1 FROM product_allergens);


-- Map Entity
INSERT INTO map_entity (id, name)
SELECT * FROM (VALUES 
    (1,'Main'),
    (6,'Outdoor')
) AS temp_table(id, name)
WHERE NOT EXISTS (SELECT 1 FROM map_entity);

-- Menu Categories
INSERT INTO menu_category_entity (id, description, menu_order, name)
SELECT * FROM (VALUES 
    (1,'Delicious dishes to start your meal.',0,'Starters'),
    (2,'Fresh and healthy options.',1,'Salads'),
    (3,'Main dishes to enjoy as the main course.',2,'Main Courses'),
    (4,'Sweet and tasty desserts to finish your meal.',3,'Desserts'),
    (5,'Variety of refreshing drinks.',4,'Beverages'),
    (6,'Freshly brewed coffees for a special moment.',5,'Coffees')
) AS temp_table(id, description, menu_order, name)
WHERE NOT EXISTS (SELECT 1 FROM menu_category_entity);

-- Menu Entity
INSERT INTO menu_entity (id, available, description, name)
SELECT * FROM (VALUES 
    (1,true,'This is the main menu with all categories like starters, main courses, drinks, etc.','Main Menu')
) AS temp_table(id, available, description, name)
WHERE NOT EXISTS (SELECT 1 FROM menu_entity);

-- Menu Items
INSERT INTO menu_item_entity (id, available, deleted, menu_order, price, tax, category_id, menu_id, product_id)
SELECT * FROM (VALUES 
(1, true, false, 1, 3.50, 0.21, 1, 1, 1),
(2, true, false, 2, 4.00, 0.21, 1, 1, 2),
(3, true, false, 3, 6.50, 0.21, 1, 1, 3),
(4, true, false, 4, 4.50, 0.21, 1, 1, 4),
(5, true, false, 5, 5.00, 0.21, 1, 1, 5),
(6, true, false, 1, 7.50, 0.21, 2, 1, 6),
(7, true, false, 2, 8.00, 0.21, 2, 1, 7),
(8, true, false, 3, 9.00, 0.21, 2, 1, 8),
(9, true, false, 1, 10.00, 0.21, 3, 1, 9),
(10, true, false, 2, 12.50, 0.21, 3, 1, 10),
(11, true, false, 3, 11.00, 0.21, 3, 1, 11),
(12, true, false, 4, 14.00, 0.21, 3, 1, 12),
(13, true, false, 5, 16.00, 0.21, 3, 1, 13),
(14, true, false, 6, 10.50, 0.21, 3, 1, 14),
(15, true, false, 7, 13.00, 0.21, 3, 1, 15),
(16, true, false, 8, 15.00, 0.21, 3, 1, 16),
(17, true, false, 1, 6.00, 0.21, 4, 1, 17),
(18, true, false, 2, 4.50, 0.21, 4, 1, 18),
(19, true, false, 3, 5.00, 0.21, 4, 1, 19),
(20, true, false, 1, 2.00, 0.21, 5, 1, 20),
(21, true, false, 2, 2.50, 0.21, 5, 1, 21),
(22, true, false, 3, 3.00, 0.21, 5, 1, 22),
(23, true, false, 4, 4.00, 0.21, 5, 1, 23),
(24, true, false, 5, 3.50, 0.21, 5, 1, 24),
(25, true, false, 6, 3.00, 0.21, 5, 1, 25),
(26, true, false, 7, 2.80, 0.21, 5, 1, 26),
(27, true, false, 8, 3.00, 0.21, 5, 1, 27),
(28, true, false, 9, 4.00, 0.21, 5, 1, 28),
(29, true, false, 10, 2.20, 0.21, 5, 1, 29),
(30, true, false, 1, 2.50, 0.21, 6, 1, 30),
(31, true, false, 2, 3.00, 0.21, 6, 1, 31),
(32, true, false, 3, 3.50, 0.21, 6, 1, 32),
(33, true, false, 4, 4.00, 0.21, 6, 1, 33)
) AS temp_table(id, available, deleted, menu_order, price, tax, category_id, menu_id, product_id)
WHERE NOT EXISTS (SELECT 1 FROM menu_item_entity);

-- Tables
INSERT INTO table_entity (id, deleted, capacity, location, number)
SELECT * FROM (VALUES 
(1, false, 1, 0, 1),
(2, false, 1, 0, 2),
(3, false, 1, 0, 3),
(4, false, 1, 0, 4),
(5, false, 4, 1, 5),
(6, false, 6, 1, 6),
(7, false, 3, 1, 7),
(8, false, 2, 1, 8),
(9, false, 4, 1, 9),
(10, false, 2, 1, 10),
(11, false, 3, 2, 11),
(12, false, 3, 2, 12),
(13, false, 3, 2, 13),
(14, false, 3, 2, 14)
) AS temp_table(id, deleted, capacity, location, number)
WHERE NOT EXISTS (SELECT 1 FROM table_entity);

-- Table Map
INSERT INTO table_map_entity (id, angle, color, gapx, gapy, height, shape, width, x, y, z, map_id, table_id)
SELECT * FROM (VALUES 
(7, 270, '#000000', 30, 60, 100, 'square', 100, 300, 49, 0, 1, 5),
(8, 270, '#b26801', 0, 44, 100, 'rect', 70, 99, 0, 0, 1, 1),
(12, 270, '#b26801', 0, 44, 100, 'rect', 70, 99, 199, 0, 1, 3),
(13, 270, '#b26801', 0, 44, 100, 'rect', 70, 99, 99, 0, 1, 2),
(14, 0, '#b57373', 0, 0, 100, 'square', 100, 462, 490, 0, 6, 3),
(15, 0, '#000000', 30, 60, 100, 'square', 100, 300, 199, 0, 1, 6),
(16, 90, '#000000', 30, 60, 100, 'square', 100, 300, 350, 0, 1, 7),
(17, 90, '#000000', 30, 60, 100, 'rect', 100, 600, 49, 0, 1, 8),
(18, 90, '#000000', 30, 60, 100, 'square', 100, 600, 199, 0, 1, 9),
(19, 270, '#000000', 30, 60, 100, 'rect', 100, 600, 350, 0, 1, 10),
(20, 0, '#be1313', 0, 0, 100, 'circle', 100, 93, 535, 0, 1, 11),
(21, 180, '#bc0b0b', 0, 0, 100, 'circle', 100, 239, 654, 0, 1, 12),
(22, 0, '#c01616', 0, 0, 100, 'circle', 100, 440, 535, 0, 1, 13),
(23, 180, '#bd0a0a', 0, 0, 100, 'circle', 100, 588, 648, 0, 1, 14),
(24, 270, '#b26801', 0, 44, 100, 'rect', 70, 99, 300, 0, 1, 4)
) AS temp_table(id, angle, color, gapx, gapy, height, shape, width, x, y, z, map_id, table_id)
WHERE NOT EXISTS (SELECT 1 FROM table_map_entity);

-- Default Admin User
INSERT INTO users (id, account_non_expired, account_non_locked, created_at, credentials_non_expired, email, enabled, first_name, last_name, password, reset_password_token, reset_password_token_expiry, role, updated_at, username)
SELECT * FROM (VALUES (
    'd6a3dcb4-7727-4eba-999e-0431a6cda204'::uuid,
    true,
    true,
    '2025-04-27 09:55:45.216131'::timestamp,
    true,
    'admin@admin.com',
    true,
    'admin',
    'admin',
    '$2a$10$dPSgOqTRAYEELlUQz1xQeOz/qOpBkYIALnt287vSUoDxGt7G60HuK',
    NULL,
    NULL::timestamp, 
    'ADMIN', 
    '2025-04-27 09:55:45.216140'::timestamp,
    'admin'
), 
(
    '0624b164-fce8-471e-b01b-b903a63d84d0'::uuid, 
    true,
    true,
    '2025-04-27 09:55:45.216131'::timestamp,  
    true,
    'workern@worker.com',
    true,
    'worker',
    'worker',
    '$2a$10$dPSgOqTRAYEELlUQz1xQeOz/qOpBkYIALnt287vSUoDxGt7G60HuK',
    NULL,
    NULL::timestamp,
    'WORKER', 
    '2025-04-27 09:55:45.216140'::timestamp, 
    'worker'
),
(
    'f5fa03a6-6703-45b8-bc25-9f7fc35a978b'::uuid,  
    true,
    true,
    '2025-04-27 09:55:45.216131'::timestamp, 
    true,
    'manager@manager.com',
    true,
    'manager',
    'manager',
    '$2a$10$dPSgOqTRAYEELlUQz1xQeOz/qOpBkYIALnt287vSUoDxGt7G60HuK',
    NULL,
    NULL::timestamp,  
    'MANAGER', 
    '2025-04-27 09:55:45.216140'::timestamp,
    'manager'
)
) AS temp_table(id, account_non_expired, account_non_locked, created_at, credentials_non_expired, email, enabled, first_name, last_name, password, reset_password_token, reset_password_token_expiry, role, updated_at, username)
WHERE NOT EXISTS (SELECT 1 FROM users);

-- Insert into invoice_entity
INSERT INTO public.invoice_entity (id, address, customer_id, customer_name, date, paid_with_card, paid_with_cash)
SELECT * FROM (VALUES
(2, NULL, NULL, NULL, '2025-04-27 12:21:11'::timestamp, 55.00, 0.00)
) AS temp_table(id, address, customer_id, customer_name, date, paid_with_card, paid_with_cash)
WHERE NOT EXISTS (SELECT 1 FROM public.invoice_entity);



-- Insert into order_entity
-- Insert into order_entity
INSERT INTO public.order_entity (id, date, paid, status, total_with_tax, total_without_tax, invoice_id, table_id)
SELECT * FROM (VALUES
(1, '2025-04-27 12:20:23.317659'::timestamp, FALSE, 'PENDING', 14.50, 11.98, NULL, 1),
(2, '2025-04-27 12:20:31.744135'::timestamp, FALSE, 'IN_PROGRESS', 37.50, 30.99, NULL, 4),
(3, '2025-04-27 12:20:38.020672'::timestamp, FALSE, 'IN_PROGRESS', 48.50, 40.08, NULL, 3),
(5, '2025-04-27 12:21:04.40588'::timestamp, TRUE, 'FINISHED', 55.00, 45.45, 2, 9),
(4, '2025-04-27 12:20:51.258969'::timestamp, FALSE, 'COMPLETED', 40.50, 33.47, NULL, 6)
) AS temp_table(id, date, paid, status, total_with_tax, total_without_tax, invoice_id, table_id)
WHERE NOT EXISTS (SELECT 1 FROM public.order_entity);



-- Insert into order_item_entity
INSERT INTO public.order_item_entity (id, completed, note, price, quantity, tax, menu_item_id, order_id)
SELECT * FROM (VALUES
(2, FALSE, NULL, 7.50, 1, 0.21, 6, 1),
(1, FALSE, NULL, 7.00, 2, 0.21, 1, 1),
(3, FALSE, NULL, 7.50, 1, 0.21, 6, 2),
(5, FALSE, NULL, 8.00, 1, 0.21, 7, 2),
(4, FALSE, NULL, 22.00, 2, 0.21, 11, 2),
(6, FALSE, NULL, 7.50, 1, 0.21, 6, 3),
(8, FALSE, NULL, 9.00, 1, 0.21, 8, 3),
(7, FALSE, NULL, 16.00, 2, 0.21, 7, 3),
(9, FALSE, NULL, 16.00, 1, 0.21, 13, 3),
(11, FALSE, NULL, 8.00, 1, 0.21, 7, 4),
(12, FALSE, NULL, 22.00, 2, 0.21, 11, 4),
(13, FALSE, NULL, 10.50, 1, 0.21, 14, 4),
(15, FALSE, NULL, 9.00, 1, 0.21, 8, 5),
(16, FALSE, NULL, 14.00, 1, 0.21, 12, 5),
(14, FALSE, NULL, 32.00, 2, 0.21, 13, 5)
) AS temp_table(id, completed, note, price, quantity, tax, menu_item_id, order_id)
WHERE NOT EXISTS (SELECT 1 FROM public.order_item_entity);

-- Reset all sequences to ensure they start from correct values after inserts
-- Resetea las secuencias a MAX(id) + 1 automáticamente

SELECT setval('public.invoice_entity_id_seq', COALESCE((SELECT MAX(id) + 1 FROM public.invoice_entity), 1), false);
SELECT setval('public.map_entity_id_seq', COALESCE((SELECT MAX(id) + 1 FROM public.map_entity), 1), false);
SELECT setval('public.menu_category_entity_id_seq', COALESCE((SELECT MAX(id) + 1 FROM public.menu_category_entity), 1), false);
SELECT setval('public.menu_entity_id_seq', COALESCE((SELECT MAX(id) + 1 FROM public.menu_entity), 1), false);
SELECT setval('public.menu_item_entity_id_seq', COALESCE((SELECT MAX(id) + 1 FROM public.menu_item_entity), 1), false);
SELECT setval('public.order_entity_id_seq', COALESCE((SELECT MAX(id) + 1 FROM public.order_entity), 1), false);
SELECT setval('public.order_item_entity_id_seq', COALESCE((SELECT MAX(id) + 1 FROM public.order_item_entity), 1), false);
SELECT setval('public.product_entity_id_seq', COALESCE((SELECT MAX(id) + 1 FROM public.product_entity), 1), false);
SELECT setval('public.table_entity_id_seq', COALESCE((SELECT MAX(id) + 1 FROM public.table_entity), 1), false);
SELECT setval('public.table_map_entity_id_seq', COALESCE((SELECT MAX(id) + 1 FROM public.table_map_entity), 1), false);

