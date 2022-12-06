<?php

include_once("../DAO/DB_Helper.php");
include_once("../DTO/Usuario.php");

session_start();

//1-Obtener parametros

if ($_POST["user"] !== null) {
    $nombre = $_POST["user"];
}

if ($_POST["pass"] !== null) {
    $passwd = $_POST["pass"];
}

//2-Crear objeto

$usuarioDTO = new Usuario();
$usuarioDTO->setNombre($nombre);
$usuarioDTO->setPass($passwd);

//3-Conectar BBDD

$db = new DB_Helper();

$conn = $db->conectarBD();

//4-Operaciones con BBDD
$usuarioRetorno = new Usuario();

$usuarioRetorno = $db->comprobarUsuario($conn, $usuarioDTO);

//5-Desconectar BBDD

$db->desconectarBD($conn);

//6-Devolver Datos

if ($usuarioRetorno->getNombre() != null && $usuarioRetorno->getId() != 0) {

    $_SESSION["usuario_comprobado"] = $usuarioRetorno;
    unset($_SESSION["error_login"]);
} else {
    $_SESSION["error_login"] = "Introduce un usuario y contraseña validos";

    $usuarioRetorno->setTipoUsuario(999);
}

//7-Redireccionar

if ($usuarioRetorno->getTipoUsuario() == 0) {
    $ruta = " Read/MostrarDatos.php";
} elseif ($usuarioRetorno->getTipoUsuario() == 1) {
    $ruta = " Read/MostrarTareasUsuario.php";
} else {
    $ruta = "../../Vista/index.php";
}

header("Location: $ruta ");
