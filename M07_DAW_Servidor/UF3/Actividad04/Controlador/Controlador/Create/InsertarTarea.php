<?php

include_once("../../DAO/DB_Helper.php");
include_once("../../DTO/Proyecto.php");
include_once("../../DTO/Usuario.php");
include_once("../../DTO/Tarea.php");
include_once("../../DTO/V_Tareas.php");
include("../Funciones.php");

session_start();

//1-Obtener parametros
$listaUsuarios = $_SESSION["lista_usuarios"];

$listaProyectos = $_SESSION["lista_proyectos"];

$usuarioLogeado = $_SESSION["usuario_comprobado"];

if ($_SESSION["proyecto_actual"] != null) {

    $Proyecto = obtenerIdDeLista($listaProyectos, $_SESSION["nombre_proyecto"]);
}

if ($_POST["new_usuario"] != null) {
    $Usuario = obtenerIdDeLista($listaUsuarios, $_POST["new_usuario"]);
}
if ($_POST["new_tarea"] != null) {
    $Tarea = $_POST["new_tarea"];
}
if ($_POST["new_estado"] != null) {
    $Estado = $_POST["new_estado"];
}
//2-Crear objeto

$tareaDTO = new Tarea();
$tareaDTO->setFk_Proyecto($Proyecto);
$tareaDTO->setFk_Usuario($Usuario);
$tareaDTO->setNombre($Tarea);
$tareaDTO->setEstado($Estado);

//3-Conectar BBDD

$db = new DB_Helper();

$conn = $db->conectarBD();

//4-Operaciones con BBDD
$db->insertarTarea($conn, $tareaDTO);

//actualizar valores lista tareas
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
    $ruta = " ../../../Vista/index.php";
}

header("Location: $ruta ");
