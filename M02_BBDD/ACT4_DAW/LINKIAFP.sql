--1	Crear un tablespace de 400MB llamado empresa donde se almacenar�n todos los datos.
CREATE TABLESPACE empresa
    DATAFILE 'empresa.dbf' SIZE 400M
    AUTOEXTEND ON;
--Info del programa
define;
--Mostrar tablespaces del sistema creados
SELECT
    *
FROM
    dba_tablespaces;
-- Muestra info relativa a un tablespace en concreto definido por el name
SELECT
    *
FROM
    sys.dba_data_files
WHERE
    tablespace_name = 'EMPRESA';
--Mostramos informacion del tama�o de las tablespaces en bytes
SELECT
    tablespace_name,
    SUM(bytes) total
FROM
    dba_data_files
GROUP BY
    tablespace_name;
--2.	Crea un usuario llamado �tecnico� que tenga todos los privilegios en el sistema Oracle. Comprobar que realmente tiene asignados estos permisos.
--activamos la variable de sesion para poder crear usuarios
ALTER SESSION SET "_oracle_script" = true;
-- Creamos el usuario definiendo su nombre, su contrase�a y el tablespace de trabajo (se le podrian definir mas caracteristicas a un usuario)
CREATE USER tecnico IDENTIFIED BY "4321"
    DEFAULT TABLESPACE empresa;
-- le damos permisos de administrador 
GRANT dba TO tecnico;
--Comprobamos los permisos del usuario, system y users en general
SELECT
    *
FROM
    dba_role_privs
WHERE
    grantee = upper('tecnico');

SELECT
    *
FROM
    dba_role_privs
WHERE
    grantee = upper('system');

SELECT
    username,
    account_status,
    default_tablespace,
    created,
    authentication_type
FROM
    dba_users
ORDER BY
    username DESC;

--5 Dar permiso al user1 de crear usuarios 
GRANT
    CREATE USER
TO user1 CONTAINER = ALL;


--10.	Con el usuario system borra todos los usuarios, perfiles y roles que has creado en el sistema.
--quitamos el perfil del usuario que lo mantiene para poder eliminar 
ALTER USER user3
    PROFILE default;

--Se borra con el usuario tecnico
--drop profile perfilusuario;

--Borrar el usuario tecnico desde System

-- hay que activar la variable de sesion para poder borrar al usuario desde SYSTEM
ALTER SESSION SET "_oracle_script" = true;

DROP USER tecnico CASCADE;

SELECT
    *
FROM
    tecnico.incidencias;