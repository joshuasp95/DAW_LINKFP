-- CONFIGURACION DE VARIABLES DE SESION PARA TRABAJAR EN SQL DEVELOPER

ALTER SESSION SET "_ORACLE_SCRIPT" = true;

SET SERVEROUTPUT ON;

-- SENTENCIAS PARA ELIMINAR TABLAS Y OBJETOS 

DROP TYPE obj_usuario;

DROP TYPE obj_polideportivo;

DROP TYPE obj_pista;

DROP TYPE obj_reserva;

DROP TABLE usuarios;

DROP TABLE polideportivos;

DROP TABLE pistas;

DROP TABLE reservas;

-- 1:CREACION DE LA BASE DE DATOS OBJETO RELACIONAL

    -- OBJETOS Y TABLAS
        
        --USUARIOS

CREATE OR REPLACE TYPE obj_usuario AS OBJECT (
    id               INTEGER,
    nombre           VARCHAR2(50),
    apellidos        VARCHAR2(70),
    email            VARCHAR2(50),
    telefono         VARCHAR2(40),
    fecha_nacimiento DATE
);

CREATE TABLE usuarios OF obj_usuario (
    id PRIMARY KEY
);

        --POLIDEPORTIVOS

CREATE OR REPLACE TYPE obj_polideportivo AS OBJECT (
    id        INTEGER,
    nombre    VARCHAR2(50),
    direccion VARCHAR2(70),
    extension NUMBER(8, 2)
);

CREATE TABLE polideportivos OF obj_polideportivo (
    id PRIMARY KEY
);

        --PISTAS
CREATE OR REPLACE TYPE obj_pista AS OBJECT (
    id            INTEGER,
    tipo          VARCHAR2(255),
    operativa     VARCHAR(2),
    precio        NUMBER(8, 2),
    polideportivo REF obj_polideportivo
);

CREATE TABLE pistas OF obj_pista (
    id PRIMARY KEY,
    polideportivo
        REFERENCES polideportivos
);

        --RESERVAS
CREATE OR REPLACE TYPE obj_reserva AS OBJECT (
    id            INTEGER,
    usuario       REF obj_usuario,
    pista         REF obj_pista,
    fecha_reserva DATE,
    fecha_uso     DATE
);

CREATE TABLE reservas OF obj_reserva (
    id PRIMARY KEY,
    usuario
        REFERENCES usuarios,
    pista
        REFERENCES pistas
);

-- 2: HACEMOS LAS INSERCIONES

    -- USUARIOS
INSERT INTO usuarios VALUES (
    1,
    'Pablo',
    'González',
    'pablo@gmail.com',
    '123456789',
    '01-01-1990'
);

INSERT INTO usuarios VALUES (
    2,
    'Isabel',
    'Fernandez',
    'isabel@gmail.com',
    '123456789',
    '25-11-1998'
);

INSERT INTO usuarios VALUES (
    3,
    'Juan',
    'Pérez',
    'juan@gmail.com',
    '111111111',
    '01-03-1985'
);

INSERT INTO usuarios VALUES (
    4,
    'María',
    'García',
    'maria@gmail.com',
    '222222222',
    '01-04-1975'
);

INSERT INTO usuarios VALUES (
    5,
    'Isabel',
    'Fernandez',
    'isabel@gmail.com',
    '123456789',
    '25-11-1998'
);

    -- POLIDEPORTIVOS 

INSERT INTO polideportivos VALUES (
    1,
    'Polideportivo Sur',
    'Calle Falsa 123',
    1000
);

INSERT INTO polideportivos VALUES (
    2,
    'Polideportivo Norte',
    'Calle Real 456',
    2000
);

INSERT INTO polideportivos VALUES (
    3,
    'Polideportivo Este',
    'Calle Fantasma 789',
    3000
);

INSERT INTO polideportivos VALUES (
    4,
    'Polideportivo Oeste',
    'Calle Invisible 147',
    4000
);
    --PISTAS

INSERT INTO pistas (
    id,
    tipo,
    operativa,
    precio,
    polideportivo
) VALUES (
    1,
    'Tenis',
    'Si',
    20,
    (
        SELECT
            ref(p)
        FROM
            polideportivos p
        WHERE
            p.id = 1
    )
);

INSERT INTO pistas (
    id,
    tipo,
    operativa,
    precio,
    polideportivo
) VALUES (
    2,
    'Fútbol',
    'Si',
    15,
    (
        SELECT
            ref(p)
        FROM
            polideportivos p
        WHERE
            p.id = 2
    )
);

INSERT INTO pistas (
    id,
    tipo,
    operativa,
    precio,
    polideportivo
) VALUES (
    3,
    'Pádel',
    'No',
    10,
    (
        SELECT
            ref(p)
        FROM
            polideportivos p
        WHERE
            p.id = 3
    )
);

INSERT INTO pistas (
    id,
    tipo,
    operativa,
    precio,
    polideportivo
) VALUES (
    4,
    'Baloncesto',
    'Si',
    25,
    (
        SELECT
            ref(p)
        FROM
            polideportivos p
        WHERE
            p.id = 4
    )
);

INSERT INTO pistas (
    id,
    tipo,
    operativa,
    precio,
    polideportivo
) VALUES (
    5,
    'Gimnasio',
    'Si',
    45,
    (
        SELECT
            ref(p)
        FROM
            polideportivos p
        WHERE
            p.id = 4
    )
);

    --RESERVAS

INSERT INTO reservas (
    id,
    usuario,
    pista,
    fecha_reserva,
    fecha_uso
) VALUES (
    1,
    (
        SELECT
            ref(u)
        FROM
            usuarios u
        WHERE
            u.id = 1
    ),
    (
        SELECT
            ref(p)
        FROM
            pistas p
        WHERE
            p.id = 1
    ),
    '01-01-2022',
    '01-02-2022'
);

INSERT INTO reservas (
    id,
    usuario,
    pista,
    fecha_reserva,
    fecha_uso
) VALUES (
    2,
    (
        SELECT
            ref(u)
        FROM
            usuarios u
        WHERE
            u.id = 2
    ),
    (
        SELECT
            ref(p)
        FROM
            pistas p
        WHERE
            p.id = 2
    ),
    '01-03-2022',
    '01-04-2022'
);

INSERT INTO reservas (
    id,
    usuario,
    pista,
    fecha_reserva,
    fecha_uso
) VALUES (
    3,
    (
        SELECT
            ref(u)
        FROM
            usuarios u
        WHERE
            u.id = 3
    ),
    (
        SELECT
            ref(p)
        FROM
            pistas p
        WHERE
            p.id = 3
    ),
    '01-05-2022',
    '01-06-2022'
);

INSERT INTO reservas (
    id,
    usuario,
    pista,
    fecha_reserva,
    fecha_uso
) VALUES (
    4,
    (
        SELECT
            ref(u)
        FROM
            usuarios u
        WHERE
            u.id = 4
    ),
    (
        SELECT
            ref(p)
        FROM
            pistas p
        WHERE
            p.id = 4
    ),
    '01-07-2022',
    '01-08-2022'
);

INSERT INTO reservas (
    id,
    usuario,
    pista,
    fecha_reserva,
    fecha_uso
) VALUES (
    5,
    (
        SELECT
            ref(u)
        FROM
            usuarios u
        WHERE
            u.id = 5
    ),
    (
        SELECT
            ref(p)
        FROM
            pistas p
        WHERE
            p.id = 1
    ),
    '24-01-2022',
    '30-01-2022'
);

-- COMPROBACIONES

SELECT
    *
FROM
    user_types;
    
    --USUARIOS

SELECT
    *
FROM
    usuarios;

    --POLIDEPORTIVOS

SELECT
    *
FROM
    polideportivos;

    --PISTAS

SELECT
    *
FROM
    pistas;

    --RESERVAS
SELECT
    *
FROM
    reservas;

-- CONSULTAS

-- 3: CONSULTA DE TODAS LAS RESERVAS

SELECT
    *
FROM
    reservas;

-- 4: CONSULTA DE LAS RESERVAS DE UN DETERMINADO USUARIO

SELECT
    deref(pista).tipo                 AS pista,
    deref(pista).polideportivo.nombre AS polideportivo,
    fecha_reserva                     AS "Fecha de Reserva",
    fecha_uso                         AS "Fecha de Uso"
FROM
    reservas t
WHERE
    deref(usuario).nombre = 'Isabel';

-- 5: CONSULTA DE LAS RESERVAS DE UN DETERMINADO MES

-- UPDATE PARA QUE HAYA 2 RESERVAS EN EL MISMO MES

UPDATE reservas
SET
    fecha_reserva = '12-03-2022'
WHERE
    deref(usuario).id = 4;

SELECT
    deref(usuario).nombre  AS "Usuario",
    deref(pista).tipo      AS pista,
    deref(pista).operativa AS "Operativa?",
    fecha_reserva          AS "Fecha de Reserva"
FROM
    reservas t
WHERE
    EXTRACT(MONTH FROM fecha_reserva) = 3;
    
-- O tambien con WHERE fecha = BETWEEN TO_DATE('01-03-2022', 'DD-MM-YYYY') AND TO_DATE('31-03-2022', 'DD-MM-YYYY');

-- 6: Consulta de las pistas de un polideportivo

SELECT
    tipo                AS pista,
    concat(precio, '€') AS precio
FROM
    pistas t
WHERE
    deref(polideportivo).id = 4;
    
-- 7: Comprobacion de la integridad referencial

DELETE FROM usuarios
WHERE
    usuarios.id = 1;