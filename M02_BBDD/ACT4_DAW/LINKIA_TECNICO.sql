--3. Utilizando el usuario de nombre “tecnico” crea la tabla incidencias. 
--Utilizar el tipo de campo y la longitud que creáis más adecuados para cada uno de los campos. Introduce datos en las tablas.

CREATE TABLE incidencias (
    cod_incidencia NCHAR(3) PRIMARY KEY,
    equipo_inc     VARCHAR(10) NOT NULL,
    descripcion    VARCHAR(40),
    usuario        VARCHAR(30) NOT NULL,
    fecha          DATE,
    solucionada    NCHAR(1),
    coste          NUMBER(8, 2)
);


--cambiamos el formato que tiene el campo fecha
ALTER SESSION SET nls_date_format = 'DD-MM-YYYY HH24:MI:SS';

--comprobamos que se ha cambiado el formato

SELECT
    sysdate
FROM
    dual;

--comprobamos que la tabla tiene la estructura correcta
SELECT
    *
FROM
    incidencias;

INSERT INTO incidencias VALUES (
    '001',
    'Len_01',
    'No recuerdo la contraseña',
    'kevin_fer',
    '01-12-2022 12:30:45',
    'N',
    200
);

INSERT INTO incidencias VALUES (
    '002',
    'Mac_32',
    'Problemas al abrir Excel',
    'juan_san',
    '21-11-2022 12:30:45',
    'S',
    50.56
);

INSERT INTO incidencias VALUES (
    '003',
    'Ace_14',
    'Se queda congelada la pantalla',
    'claudia_lea',
    '15-11-2022 12:30:45',
    'N',
    750.30
);

INSERT INTO incidencias VALUES (
    '004',
    'Tos_06',
    'No se conecta a internet',
    'monica_aro',
    '28-10-2022 12:30:45',
    'S',
    230.45
);

--4. Crear dos usuarios “user1” y “user2” con password “Abcd1234”, 
--que se encarguen de la gestión de las incidencias (añadir, modificar, borrar, consultar). 
--Comprueba que los privilegios se han asignado de forma correcta y que puede hacer las operaciones asignadas.

--Primero volvemos a activar la variable de sesion para poder crear usuarios
ALTER SESSION SET "_oracle_script" = true;

--Creamos los 2 usuarios
CREATE USER user1 IDENTIFIED BY "Abcd1234"
    DEFAULT TABLESPACE empresa;

CREATE USER user2 IDENTIFIED BY "Abcd1234"
    DEFAULT TABLESPACE empresa;

--Les damos el permiso de sistema para conectarse
GRANT
    CREATE SESSION
TO user1;

GRANT
    CREATE SESSION
TO user2;

GRANT INSERT, UPDATE, DELETE, SELECT ON tecnico.incidencias TO user1;

GRANT INSERT, UPDATE, DELETE, SELECT ON tecnico.incidencias TO user2; 

--Comprobar usuarios conectados
SELECT
    username,
    program
FROM
    v$session
WHERE
    username IS NOT NULL
ORDER BY
    osuser;

--Comrpobar usuarios existentes
SELECT
    username
FROM
    all_users;

--Comprobamos que tienen los permisos adjudicados en las tablas correspondientes
SELECT
    grantee,
    privilege,
    table_name,
    owner,
    grantable
FROM
    dba_tab_privs
WHERE
    grantee = upper('user1')
    OR grantee = upper('user2');

--Comprobar usuarios existentes con estado abierto del sistema
SELECT
    dba_users.username,
    dba_users.default_tablespace
FROM
    dba_users
WHERE
    dba_users.account_status = ( 'OPEN' );

--Comprobamos los privilegios de sistema
SELECT
    grantee,
    privilege
FROM
    dba_sys_privs
WHERE
    grantee = upper('user1')
    OR grantee = upper('user2');


--5 Revocar permisos de borrado de registros user2

REVOKE DELETE ON tecnico.incidencias FROM user2;

--6 Dar permisos de lectura e iniciado de sesion a user2

GRANT
    CREATE SESSION
TO user2 WITH ADMIN OPTION;

GRANT SELECT ON tecnico.incidencias TO user2 WITH GRANT OPTION;

--7 Crea un rol llamado rolincidencias con las siguientes características: 
--Puede iniciar sesión, leer y modificar la tabla de incidencias (no puede ni borrar ni añadir).

CREATE ROLE rolincidencias;

GRANT
    CREATE SESSION
TO rolincidencias;

GRANT SELECT, UPDATE ON tecnico.incidencias TO rolincidencias;

--comprobamos que se ha creado el rol
SELECT
    *
FROM
    dba_roles
WHERE
    role = upper('rolincidencias');

SELECT
    *
FROM
    dba_tab_privs
WHERE
    grantee = upper('rolincidencias');

--8 Crea un usuario nuevo llamado “user3” con password “System1234” y le asignas el rol anterior. Comprueba que tienen los permisos correspondientes
--Comprobamos que tienen los permisos adjudicados en las tablas correspondientes
SELECT
    grantee,
    privilege,
    table_name,
    owner,
    grantable
FROM
    dba_tab_privs
WHERE
    grantee = upper('user3');

SELECT
    grantee,
    privilege
FROM
    dba_sys_privs
WHERE
    grantee = upper('user3');

SELECT
    grantee,
    granted_role
FROM
    dba_role_privs
WHERE
    grantee = upper('user3');
--asignamos el rol a user3
GRANT rolincidencias TO user3;


--9.Crearemos un perfil para los usuarios llamado perfilusuario que tenga un tiempo máximo de conexión 
--de 1 hora, dos conexiones simultáneas y le obligue a cambiar la contraseña cada 30 días. Asigna este perfil al usuario user3


CREATE PROFILE perfilusuario LIMIT
    CONNECT_TIME 60
    SESSIONS_PER_USER 2
    PASSWORD_LIFE_TIME 30;

SELECT
    *
FROM
    dba_profiles
WHERE
        profile = upper('perfilusuario')
    AND limit <> 'DEFAULT';

ALTER USER user3
    PROFILE perfilusuario;

--El perfil permite eliminarlo desde el usuario que lo creo
DROP PROFILE perfilusuario;

--Borramos el rol creado 
DROP ROLE rolincidencias;

DROP USER user1 CASCADE;

DROP USER user2 CASCADE;

DROP USER user3 CASCADE;

DROP USER user4 CASCADE;

--Para borrar al usuario tecnico lo hacemos desde SYSTEM, desconectandolo antes y

SELECT
    *
FROM
    incidencias;

GRANT SELECT ON tecnico.incidencias TO system;