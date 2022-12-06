<?php

include_once("../../DAO/DB_Helper.php");
include_once("../../DTO/Proyecto.php");
include_once("../../DTO/Usuario.php");
include_once("../../DTO/Tarea.php");
include_once("../../DTO/V_Tareas.php");
include("../Funciones.php");

session_start();

//1-Obtener parametros

$usuarioLogeado = $_SESSION["usuario_comprobado"];

$listaProyectos = $_SESSION["lista_proyectos"];

$listaUsuarios = $_SESSION["lista_usuarios"];

if ($_POST["tarea_usuario_selecc"] != null) {
    $usuarioId = obtenerIdDeLista($listaUsuarios, $_POST["tarea_usuario_selecc"]);
}

$proyectoId = obtenerIdDeLista($listaProyectos, $_SESSION["proyecto_actual"]);

//2-Crear objeto


//3-Conectar BBDD

$db = new DB_Helper();

$conn = $db->conectarBD();

//4-Operaciones con BBDD

$db->borrarTarea($conn, $proyectoId, $usuarioId);

$db->actualizarVista($conn);

$listaTareas = $db->mostrarTareas($conn, $_SESSION["nombre_proyecto"]);

//5-Desconectar BBDD

$db->desconectarBD($conn);

//6-Devolver Datos

$_SESSION["lista_tareas"] = validarListas($listaTareas);

//7-Redireccionar

if ($usuarioLogeado->getTipoUsuario() == 0) {
    $ruta = " ../../../Vista/tareas.php";
} elseif ($usuarioLogeado->getTipoUsuario() == 1) {
    $ruta = " ../../../Vista/bienvenida.php";
} else {
    $ruta = "../../../Vista/index.php";
}

header("Location: $ruta ");
