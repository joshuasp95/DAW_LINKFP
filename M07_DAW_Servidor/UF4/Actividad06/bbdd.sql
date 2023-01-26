-- Borramos la BBDD
drop database actividad_06_maps;

/*CREAMOS LA BASE DE DATOS*/
create database if not exists actividad_06_maps;

use actividad_06_maps;

-- CREAMOS LA ESTRUCTURA DE LA TABLA
CREATE TABLE marcadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    coordenadas VARCHAR(100),
    poblacion VARCHAR(60),
    tipo VARCHAR(30)
);

-- AÑADIMOS LOS VALORES
-- PARA LOCALIDAD = LOIU
insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Restaurante Aurregoiti',
        '{lat: 43.328276, lng: -2.905529}',
        'Loiu',
        'Restaurantes'
    );

insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Bar Ejemplo Loiu',
        '{lat: 43.324256, lng: -2.905629}',
        'Loiu',
        'Bares'
    );

insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Bar Ejemplo Loiu 2',
        '{lat: 43.321256, lng: -2.905129}',
        'Loiu',
        'Bares'
    );

insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Discoteca Loiu',
        '{lat: 43.318246, lng: -2.915729}',
        'Loiu',
        'Discotecas'
    );

-- PARA LOCALIDAD = DERIO --43.292094, -2.886099
insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Pub Devons',
        '{lat: 43.292094, lng: -2.886099}',
        'Derio',
        'Bares'
    );

insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Restaurante Ejemplo Derio',
        '{lat: 43.296094, lng: -2.886199}',
        'Derio',
        'Restaurantes'
    );

insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Discoteca Ejemplo Derio',
        '{lat: 43.292554, lng: -2.886339}',
        'Derio',
        'Discotecas'
    );

insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Discoteca Ejemplo Derio 2',
        '{lat: 43.292139, lng: -2.887679}',
        'Derio',
        'Discotecas'
    );

-- PARA LOCALIDAD = BILBAO
insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Discoteca Fever',
        '{lat: 43.250368, lng: -2.900050}',
        'Bilbao',
        'Discotecas'
    );

insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Bar Ejemplo Bilbao',
        '{lat: 43.255638, lng: -2.914530}',
        'Bilbao',
        'Bares'
    );

insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Restaurante Ejemplo Bilbao',
        '{lat: 43.257938, lng: -2.919930}',
        'Bilbao',
        'Restaurantes'
    );

insert into
    marcadores(nombre, coordenadas, poblacion, tipo)
values
    (
        'Restaurante Ejemplo Bilbao 2',
        '{lat: 43.253231, lng: -2.921400}',
        'Bilbao',
        'Restaurantes'
    );

-- COMPROBAMOS EL RESULTADO
SELECT
    *
FROM
    marcadores;

SELECT
    nombre,
    coordenadas,
    tipo
FROM
    marcadores
WHERE
    poblacion = 'Loiu'
    AND tipo = 'Bares';