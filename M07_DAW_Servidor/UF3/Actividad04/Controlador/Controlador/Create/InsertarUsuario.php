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

if (validarUsuario($_POST["new_user"])) {
    $nombre = $_POST["new_user"];
}

if (validarPasswd($_POST["new_pass"])) {
    $passwd = $_POST["new_pass"];
}
if (validarTipoUser($_POST["tipo"])) {
    $tipoUsuario = $_POST["tipo"];
}

//2-Crear objeto

$usuarioDTO = new Usuario();
$usuarioDTO->setNombre($nombre);
$usuarioDTO->setPass($passwd);
$usuarioDTO->setTipoUsuario($tipoUsuario);

//3-Conectar BBDD

$db = new DB_Helper();

$conn = $db->conectarBD();

//4-Operaciones con BBDD
if (validarUsuario($nombre) && validarPasswd($passwd) && validarTipoUser($tipoUsuario)) {
    $db->insertarUsuario($conn, $usuarioDTO);
}

//5-Desconectar BBDD

$db->desconectarBD($conn);

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
