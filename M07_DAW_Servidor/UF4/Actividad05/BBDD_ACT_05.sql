create database if not exists ACT_05;

use ACT_05;

CREATE TABLE IF NOT EXISTS categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    fk_id_categoria INT,
    FOREIGN KEY (fk_id_categoria) REFERENCES categoria (id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT into
    categoria
values
    (1, 'Electronica');

INSERT into
    categoria
values
    (2, 'Deportes');

INSERT into
    categoria
values
    (3, 'Jardineria');

INSERT into
    categoria
values
    (4, 'Cocina');

INSERT into
    producto
values
    (1, 'Robot de cocina', 4);

INSERT into
    producto
values
    (2, 'Raqueta de tenis', 2);

INSERT into
    producto
values
    (3, 'Pelota de baloncesto', 2);

INSERT into
    producto
values
    (4, 'Altavoces bluetooth', 1);

INSERT into
    producto
values
    (5, 'Portatil HP Flux Pavilion', 1);

SELECT
    *
FROM
    producto;

SELECT
    *
FROM
    categoria;

SELECT
    producto.nombre AS Productos,
    categoria.nombre AS Categorias
FROM
    producto
    RIGHT JOIN categoria ON categoria.id = producto.fk_id_categoria;