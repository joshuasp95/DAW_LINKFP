--Comprobamos que user1 puede realizar operaciones sobre la tabla incidencias

SELECT
    *
FROM
    tecnico.incidencias
ORDER BY
    cod_incidencia;

--cambiamos el formato que tiene el campo fecha
ALTER SESSION SET nls_date_format = 'DD-MM-YYYY HH24:MI:SS';

--Insertamos usuarios
INSERT INTO tecnico.incidencias VALUES (
    '005',
    'Asu_17',
    'No funciona el mouse',
    'carlos_ato',
    '02-12-2022 10:20:34',
    'N',
    10.99
);

--Modificamos valores

UPDATE tecnico.incidencias
SET
    fecha = '15-11-2022 15:45:33'
WHERE
    cod_incidencia = '003';

UPDATE tecnico.incidencias
SET
    fecha = '21-11-2022 17:21:23'
WHERE
    cod_incidencia = '002';

DELETE FROM tecnico.incidencias
WHERE
    cod_incidencia = '004';

--Para crear usuarios activamos la variable de sesion
ALTER SESSION SET "_oracle_script" = true;

CREATE USER user3 IDENTIFIED BY "System1234"
    DEFAULT TABLESPACE empresa
    QUOTA 2m ON empresa;

CREATE USER user4 IDENTIFIED BY "System1234"
    DEFAULT TABLESPACE empresa
    QUOTA 2m ON empresa;

--Eliminamos un usuario

DROP USER user4 CASCADE;

--COMO USER2

--Intentamos eliminar un registro

DELETE FROM tecnico.incidencias
WHERE
    cod_incidencia = '002';

--Dar permisos de lectura e iniciado de sesion a user4

GRANT
    CREATE SESSION
TO user4;

GRANT SELECT ON tecnico.incidencias TO user4;


--Como USER4

--Permiso de lectura validado

SELECT
    *
FROM
    tecnico.incidencias;


--Como USER3

-- Permiso de lectura de rol
SELECT
    *
FROM
    tecnico.incidencias;