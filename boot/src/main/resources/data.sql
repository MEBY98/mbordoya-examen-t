
INSERT INTO EXAMPLE_TYPE_TABLE (type_id) VALUES (1);
INSERT INTO EXAMPLE_TYPE_TABLE (type_id) VALUES (2);
INSERT INTO EXAMPLE_TYPE_TABLE (type_id) VALUES (3);

INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (1, 'es-ES', 'TIPO_1');
INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (1, 'pt-PT', 'TIPO_1_PT');
INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (2, 'es-ES', 'TIPO_2');
INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (2, 'pt-PT', 'TIPO_2_PT');
INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (3, 'es-ES', 'TIPO_3');
INSERT INTO EXAMPLE_TYPE_NAME_TABLE (type_id, locale_language_code, description) VALUES (3, 'pt-PT', 'TIPO_3_PT');

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