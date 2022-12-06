<?php

interface I_conexion
{

    //CONEXION BBDD
    const host = "localhost";
    const user = "root";
    const pass = "_Workench69*";
    const db_name = "EJ3_M07_PHP";
    const puerto = "3306";

    //CONEXION TABLAS

    //TABLA USUARIO

    const tabla_usuario = "usuario";
    const usuario_id = "id";
    const usuario_nombre = "nombre";
    const usuario_pass = "pass";
    const usuario_tipo_usuario = "tipo_usuario";
}
