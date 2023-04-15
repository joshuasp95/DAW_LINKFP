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

if (isset($_POST["usuario_selecc"])) {
    $usuarioId = $_POST["usuario_selecc"];
}

//2-Crear objeto


//3-Conectar BBDD

$db = new DB_Helper();

$conn = $db->conectarBD();

//4-Operaciones con BBDD


//5-Desconectar BBDD

$db->borrarUsuario($conn, $usuarioId);

//6-Devolver Datos

//No se devuelve nada

//7-Redireccionar

if ($usuarioLogeado->getTipoUsuario() == 0) {
    $ruta = " ../Read/MostrarDatos.php";
} elseif ($usuarioLogeado->getTipoUsuario() == 1) {
    $ruta = " ../../../Vista/bienvenida.php";
} else {
    $ruta = "../../../Vista/index.php";
}

header("Location: $ruta ");
