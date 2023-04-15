<?php

include_once("../../DAO/DB_Helper.php");
include("../Funciones.php");
include_once("../../DTO/Proyecto.php");
include_once("../../DTO/Usuario.php");
include_once("../../DTO/Tarea.php");
include_once("../../DTO/V_Tareas.php");

session_start();

//1-Obtener parametros

if (isset($_SESSION["usuario_comprobado"])) {
    $usuarioLogeado = $_SESSION["usuario_comprobado"];
}

//2-Crear objeto

//en este caso ya le pasamos un objeto que servira para hacer la consulta en la BBDD
$usuarioDTO = $usuarioLogeado;

//3-Conectar BBDD

$db = new DB_Helper();

$conn = $db->conectarBD();

//4-Operaciones con BBDD
$listaTareasUsuario = array();

$listaTareasUsuario = $db->mostrarTareasUsuario($conn, $usuarioDTO);

//5-Desconectar BBDD

$db->desconectarBD($conn);

//6-Devolver Datos

$_SESSION["lista_tareas_usuario"] = validarListas($listaTareasUsuario);

//7-Redireccionar

if ($usuarioLogeado->getTipoUsuario() == 0) {
    $ruta = " ../../../Vista/tareas.php";
} elseif ($usuarioLogeado->getTipoUsuario() == 1) {
    $ruta = " ../../../Vista/bienvenida.php";
} else {
    $ruta = "../../../Vista/index.php";
}

header("Location: $ruta ");
