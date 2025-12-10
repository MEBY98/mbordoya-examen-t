INSERT INTO EXAMPLE_TYPE_TABLE (type_id) VALUES (1);
INSERT INTO EXAMPLE_TYPE_TABLE (type_id) VALUES (2);
INSERT INTO EXAMPLE_TYPE_TABLE (type_id) VALUES (3);

INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (1, 'es-ES', 'TIPO_1');
INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (1, 'pt-PT', 'TIPO_1_PT');
INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (2, 'es-ES', 'TIPO_2');
INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (2, 'pt-PT', 'TIPO_2_PT');
INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (3, 'es-ES', 'TIPO_3');
INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (3, 'pt-PT', 'TIPO_3_PT');

INSERT INTO specialization (id) VALUES (1);
INSERT INTO specialization (id) VALUES (2);
INSERT INTO specialization (id) VALUES (3);

INSERT INTO specialization_name (id, locale_language_code, name) VALUES (1, 'es-ES', 'REFRIGERADO');
INSERT INTO specialization_name (id, locale_language_code, name) VALUES (1, 'pt-PT', 'REFRIGERADO_PT');
INSERT INTO specialization_name (id, locale_language_code, name) VALUES (2, 'es-ES', 'CONGELADO');
INSERT INTO specialization_name (id, locale_language_code, name) VALUES (2, 'pt-PT', 'CONGELADO_PT');
INSERT INTO specialization_name (id, locale_language_code, name) VALUES (3, 'es-ES', 'PALETIZADO');
INSERT INTO specialization_name (id, locale_language_code, name) VALUES (3, 'pt-PT', 'PALETIZADO_PT');

INSERT INTO product(id, name, selling_price, specialization_id) VALUES(1,'Arroz de Valencia', 6.5, 1);
INSERT INTO product(id, name, selling_price, specialization_id) VALUES(2,'Galletas sin gluten', 3.6, 1);
INSERT INTO product(id, name, selling_price, specialization_id) VALUES(3,'Leche de vaca', 1.5, 2);
INSERT INTO product(id, name, selling_price, specialization_id) VALUES(4,'Café soluble', 4.0, 3);
INSERT INTO product(id, name, selling_price, specialization_id) VALUES(5,'Aceite de oliva virgen extra', 8.0, 1);
INSERT INTO product(id, name, selling_price, specialization_id) VALUES(6,'Yogur natural', 1.5, 2);
INSERT INTO product(id, name, selling_price, specialization_id) VALUES(7, 'Pack Agua 6 ', 6.0, 3);
INSERT INTO product(id, name, selling_price, specialization_id) VALUES(8,'Zumo de naranja natural', 3.0, 3);
INSERT INTO product(id, name, selling_price, specialization_id) VALUES(9,'Helado chocolate y vainilla', 3.2, 1);
INSERT INTO product(id, name, selling_price, specialization_id) VALUES(10,'Mochis pistacho', 3.5, 2);

--store 1
WITH next_id AS (
  SELECT nextval('store_id_seq') AS store_id
)
INSERT INTO STORE (id, name, address)
SELECT store_id, 'Store A', '123 Main St' FROM next_id;

--module 1 store 1
WITH next_id AS (
  SELECT
  	currval('store_id_seq') AS store_id,
  	nextval('module_id_seq') as module_id
)
INSERT INTO "module" (id, capacity, specialization_id, store_id)
SELECT module_id, 50, 1, store_id  FROM next_id;

--module 2 store 1
WITH next_id AS (
  SELECT
  	currval('store_id_seq') AS store_id,
  	nextval('module_id_seq') as module_id
)
INSERT INTO "module" (id, capacity, specialization_id, store_id)
SELECT module_id, 100, 2, store_id  FROM next_id;

--module 3 store 1
WITH next_id AS (
  SELECT
  	currval('store_id_seq') AS store_id,
  	nextval('module_id_seq') as module_id
)
INSERT INTO "module" (id, capacity, specialization_id, store_id)
SELECT module_id, 20, 1, store_id  FROM next_id;

--module 4 store 1
WITH next_id AS (
  SELECT
  	currval('store_id_seq') AS store_id,
  	nextval('module_id_seq') as module_id
)
INSERT INTO "module" (id, capacity, specialization_id, store_id)
SELECT module_id, 35, 3, store_id  FROM next_id;

--store storage 1 store 1
WITH next_id AS (
  SELECT
  	currval('store_id_seq') AS store_id,
  	nextval('store_storage_id_seq') as store_storage_id
)
INSERT INTO store_storage (capacity, id, store_id, name)
SELECT 200, store_storage_id, store_id, 'Zona Almacenaje 1 Tienda 1'  FROM next_id;

--store storage 2 store 1
WITH next_id AS (
  SELECT
  	currval('store_id_seq') AS store_id,
  	nextval('store_storage_id_seq') as store_storage_id
)
INSERT INTO store_storage (capacity, id, store_id, name)
SELECT 300, store_storage_id, store_id, 'Zona Almacenaje 2 Tienda 1'  FROM next_id;

INSERT INTO module_stock (quantity, module_id, product_id)
VALUES (10, 1, 1);
INSERT INTO module_stock (quantity, module_id, product_id)
VALUES (30, 1, 2);
INSERT INTO module_stock (quantity, module_id, product_id)
VALUES (10, 2, 2);
INSERT INTO module_stock (quantity, module_id, product_id)
VALUES (0, 4, 4);

INSERT INTO store_storage_stock (quantity, store_storage_id, product_id)
VALUES (10, 1, 1);
INSERT INTO store_storage_stock (quantity, store_storage_id, product_id)
VALUES (30, 1, 2);
INSERT INTO store_storage_stock (quantity, store_storage_id, product_id)
VALUES (10, 2, 2);
INSERT INTO store_storage_stock (quantity, store_storage_id, product_id)
VALUES (10, 2, 3);
INSERT INTO store_storage_stock (quantity, store_storage_id, product_id)
VALUES (50, 2, 4);

--INSERT INTO PRODUCT (id, name, price) VALUES (1, 'Product A', 10.0);
--INSERT INTO PRODUCT (id, name, price) VALUES (2, 'Product B', 20.0);
--INSERT INTO PRODUCT (id, name, price) VALUES (3, 'Product C', 30.0);
--INSERT INTO PRODUCT (id, name, price) VALUES (4, 'Product D', 40.0);
--INSERT INTO PRODUCT (id, name, price) VALUES (5, 'Product E', 50.0);
--INSERT INTO PRODUCT (id, name, price) VALUES (6, 'Product F', 60.0);
--INSERT INTO PRODUCT (id, name, price) VALUES (7, 'Product G', 70.0);
--INSERT INTO PRODUCT (id, name, price) VALUES (8, 'Product H', 80.0);
--INSERT INTO PRODUCT (id, name, price) VALUES (9, 'Product I', 90.0);
--INSERT INTO PRODUCT (id, name, price) VALUES (10, 'Product J', 100.0);
--
----store 1
--WITH next_id AS (
--  SELECT nextval('store_id_seq') AS store_id
--)
--INSERT INTO STORE (id, name, address)
--SELECT store_id, 'Store A', '123 Main St' FROM next_id;
--
--WITH next_id AS (
--  SELECT
--  	currval('store_id_seq') AS store_id,
--  	nextval('vehicle_id_seq') as vehicle_id
--)
--INSERT INTO VEHICLE (id, capacity, store_id, model, name, type_id)
--SELECT vehicle_id, 12, store_id, 'MODEL_1', 'NAME_1', 'DRONE' FROM next_id;
--
--WITH next_id AS (
--  SELECT
--  	currval('store_id_seq') AS store_id,
--  	nextval('vehicle_id_seq') as vehicle_id
--)
--INSERT INTO VEHICLE (id, capacity, store_id, model, name, type_id)
--SELECT vehicle_id, 40, store_id, 'MODEL_2', 'NAME_2', 'TRUCK' FROM next_id;
--
----store 2
--WITH next_id AS (
--  SELECT nextval('store_id_seq') AS store_id
--)
--INSERT INTO STORE (id, name, address)
--SELECT store_id, 'Store B', '456 Main St' FROM next_id;
--
--WITH next_id AS (
--  SELECT
--  	currval('store_id_seq') AS store_id,
--  	nextval('vehicle_id_seq') as vehicle_id
--)
--INSERT INTO VEHICLE (id, capacity, store_id, model, name, type_id)
--SELECT vehicle_id, 12, store_id, 'MODEL_3', 'NAME_3', 'DRONE' FROM next_id;
--
--WITH next_id AS (
--  SELECT
--  	currval('store_id_seq') AS store_id,
--  	nextval('vehicle_id_seq') as vehicle_id
--)
--INSERT INTO VEHICLE (id, capacity, store_id, model, name, type_id)
--SELECT vehicle_id, 40, store_id, 'MODEL_4', 'NAME_4', 'TRUCK' FROM next_id;
--
----store 3
--WITH next_id AS (
--  SELECT nextval('store_id_seq') AS store_id
--)
--INSERT INTO STORE (id, name, address)
--SELECT store_id, 'Store C', '789 Main St' FROM next_id;
--
--WITH next_id AS (
--  SELECT
--  	currval('store_id_seq') AS store_id,
--  	nextval('vehicle_id_seq') as vehicle_id
--)
--INSERT INTO VEHICLE (id, capacity, store_id, model, name, type_id)
--SELECT vehicle_id, 12, store_id, 'MODEL_5', 'NAME_5', 'DRONE' FROM next_id;
--
--WITH next_id AS (
--  SELECT
--  	currval('store_id_seq') AS store_id,
--  	nextval('vehicle_id_seq') as vehicle_id
--)
--INSERT INTO VEHICLE (id, capacity, store_id, model, name, type_id)
--SELECT vehicle_id, 30, store_id, 'MODEL_6', 'NAME_6', 'TRUCK' FROM next_id;