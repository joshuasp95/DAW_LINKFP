<?php

interface I_conexion
{

    //CONEXION BBDD
    const host = "localhost";
    const user = "root";
    const pass = "";
    const db_name = "EJ4_M07_PHP";
    const puerto = "3306";

    //CONEXION TABLAS

    //TABLA USUARIO

    const tabla_usuario = "usuario";
    const usuario_id = "id";
    const usuario_nombre = "nombre";
    const usuario_pass = "pass";
    const usuario_tipo_usuario = "tipo_usuario";


    const tabla_proyecto = "proyecto";
    const proyecto_id = "id";
    const proyecto_nombre = "nombre";

    const tabla_tarea = "tarea";
    const tarea_fk_proyecto = "fk_proyecto";
    const tarea_fk_usuario = "fk_usuario";
    const tarea_nombre = "nombre";
    const tarea_estado = "estado";

    const tabla_vista_tareas = "vista_tareas";
    const vista_tareas_proyecto = "Proyecto";
    const vista_tareas_usuario = "Usuario";
    const vista_tareas_tarea = "Tarea";
    const vista_tareas_estado = "Estado";
}
