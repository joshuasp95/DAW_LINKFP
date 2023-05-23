--MOSTRAMOS LA TABLA INCIDENCIASS
SELECT
    *
FROM
    incidencias;
    
--cambiamos el formato que tiene el campo fecha
ALTER SESSION SET nls_date_format = 'DD-MM-YYYY HH24:MI:SS';

--Necesitamos activar la salida de mensajes por consola
SET SERVEROUTPUT ON;
--Quitamos la salida por consola de valores que adquiere por teclado el procedimiento
SET VERIFY OFF;

DECLARE
    p_codi_incidencia incidencias.cod_incidencia%TYPE := '&p_codi_incidencia';
    p_equipo_inc      VARCHAR(10) NOT NULL := '&p_equipo_inc';
    p_descripcion     VARCHAR(40) := '&p_descripcion';
    p_usuario         VARCHAR(30) NOT NULL := '&p_usuario';
    p_fecha           incidencias.fecha%TYPE := '&p_fecha';
    p_solucionada     NCHAR(1) := '&p_solucionada';
    p_coste           NUMBER(8, 2) := &p_coste;
BEGIN
    insertar_incidencia(p_codi_incidencia, p_equipo_inc, p_descripcion, p_usuario, p_fecha,
                       p_solucionada, p_coste);
END;

--Procedimiento para que el usuario pueda insertar una nueva incidencia
--si no se especifica si el parametro es 'in', 'out' o 'in out', se deja 'in' por defecto
CREATE OR REPLACE PROCEDURE insertar_incidencia (
    p_codi_incidencia VARCHAR2,
    p_equipo_inc      VARCHAR2,
    p_descripcion     VARCHAR2,
    p_usuario         VARCHAR2,
    p_fecha           DATE,
    p_solucionada     NCHAR,
    p_coste           NUMBER
) IS
    clave_primaria_duplicada EXCEPTION;
    msj_err VARCHAR2(255);
    cont    NUMBER(2, 0);
BEGIN
    SELECT
        COUNT(*)
    INTO cont
    FROM
        incidencias
    WHERE
        cod_incidencia = p_codi_incidencia;

    IF ( cont = 0 ) THEN
        dbms_output.put_line('No coincide con ningun registro. Se procede a insertar datos');
        INSERT INTO incidencias VALUES (
            p_codi_incidencia,
            p_equipo_inc,
            p_descripcion,
            p_usuario,
            p_fecha,
            p_solucionada,
            p_coste
        );

        dbms_output.put_line('Los datos se han insertado correctamente');
    ELSE
        RAISE clave_primaria_duplicada;
    END IF;

EXCEPTION
--se podria haber controlado con el error predefinido DUP_VAL_ON_INDEX
    WHEN clave_primaria_duplicada THEN
        dbms_output.put_line('Se ha intentado insertar un registro con una clave primaria ya existente');
    WHEN no_data_found THEN
        dbms_output.put_line('No se ha encontrado ningun registro que coincida con la consulta realizada');
    WHEN OTHERS THEN
        msj_err := sqlerrm;
        dbms_output.put_line(msj_err);
END insertar_incidencia;

ACCEPT a_usuario PROMPT 'Escribe el nombre del usuario del que quieras obtener las incidencias asociadas';

DECLARE
    usuario VARCHAR(30) NOT NULL := '&a_usuario';
BEGIN
    mostrar_incidencias(usuario);
END;

--Procedimiento para mosttrar incidencias en función del usuario pasado como parametro por el usuario
CREATE OR REPLACE PROCEDURE mostrar_incidencias (
    p_usuario VARCHAR2
) IS

    msj_err           VARCHAR2(255);
    cont              NUMBER(2, 0) := 0;
    p_codi_incidencia incidencias.cod_incidencia%TYPE;
    p_equipo_inc      VARCHAR(10);
    p_descripcion     VARCHAR(40);
    p_fecha           incidencias.fecha%TYPE;
    p_solucionada     NCHAR(1);
    p_coste           NUMBER(8, 2);
    CURSOR m_incidencias (
        m_usuario VARCHAR2
    ) IS
    SELECT
    --En  este caso comentare algunos campos que no son interesantes para devolver como resultado
--        incidencias.cod_incidencia,
--        incidencias.equipo_inc,
        incidencias.descripcion,
        incidencias.fecha,
        incidencias.solucionada,
        incidencias.coste
    FROM
        incidencias
    WHERE
        incidencias.usuario = m_usuario;

BEGIN
    OPEN m_incidencias(p_usuario);
    dbms_output.put_line('Incidencias del usuario: ' || p_usuario);
    LOOP
        cont := cont + 1;
        FETCH m_incidencias INTO
--            p_codi_incidencia,
--            p_equipo_inc,
            p_descripcion,
            p_fecha,
            p_solucionada,
            p_coste;
        EXIT WHEN m_incidencias%notfound;
        dbms_output.put_line(cont
                             || '- Incidencia: '
                             || p_descripcion
                             || '; Fecha: '
                             || p_fecha
                             || '; Solucionada(S/N): '
                             || p_solucionada
                             || '; Coste: '
                             || p_coste
                             || 'ï¿½');

    END LOOP;

    CLOSE m_incidencias;
EXCEPTION
    WHEN no_data_found THEN
        dbms_output.put_line('No se ha encontrado ningun registro que coincida con la consulta realizada');
    WHEN OTHERS THEN
        msj_err := sqlerrm;
        dbms_output.put_line(msj_err);
END mostrar_incidencias;

--3: Crear Trigger para cuando se inserte o se modifique la tabla incidencias
CREATE OR REPLACE TRIGGER estado_incidencias AFTER
    INSERT OR UPDATE ON incidencias
DECLARE BEGIN
    mostrar_incidencias_nosol;
END;

--Procedimiento para mostrar incidencias no solucionadas
CREATE OR REPLACE PROCEDURE mostrar_incidencias_nosol IS

    msj_err           VARCHAR2(255);
    cont              NUMBER(2, 0) := 0;
    p_codi_incidencia incidencias.cod_incidencia%TYPE;
    p_equipo_inc      VARCHAR(10);
    p_descripcion     VARCHAR(40);
    p_usuario         VARCHAR(30);
    p_fecha           incidencias.fecha%TYPE;
    p_coste           NUMBER(8, 2);
    CURSOR m_incidencias IS
    SELECT
        incidencias.cod_incidencia,
        incidencias.equipo_inc,
        incidencias.descripcion,
        incidencias.usuario,
        incidencias.fecha,
        incidencias.coste
    FROM
        incidencias
    WHERE
        incidencias.solucionada = 'N';

BEGIN
    OPEN m_incidencias;
    dbms_output.put_line('Incidencias no solucionadas: ');
    LOOP
        cont := cont + 1;
        FETCH m_incidencias INTO
            p_codi_incidencia,
            p_equipo_inc,
            p_descripcion,
            p_usuario,
            p_fecha,
            p_coste;
        EXIT WHEN m_incidencias%notfound;
        dbms_output.put_line(cont
                             || '- '
                             || 'Codigo incidencia: '
                             || p_codi_incidencia
                             || '; Equipo afectado: '
                             || p_equipo_inc
                             || ' Problema encontrado: '
                             || p_descripcion
                             || '; Usuario: '
                             || p_usuario
                             || '; Fecha: '
                             || p_fecha
                             || '; Coste: '
                             || p_coste
                             || 'ï¿½');

    END LOOP;

    CLOSE m_incidencias;
EXCEPTION
    WHEN no_data_found THEN
        dbms_output.put_line('No se ha encontrado ningun registro que coincida con la consulta realizada');
    WHEN OTHERS THEN
        msj_err := sqlerrm;
        dbms_output.put_line(msj_err);
END mostrar_incidencias_nosol;

--Comprobar que funciona el trigger
UPDATE incidencias
SET
    incidencias.fecha = '01-12-2022 11:28:01'
WHERE
    incidencias.usuario = 'kevin_fer';