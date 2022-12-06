<?php
include_once("../../DAO/DB_Helper.php");
include_once("../../DTO/Proyecto.php");
include_once("../../DTO/Usuario.php");
include_once("../../DTO/Tarea.php");
include_once("../../DTO/V_Tareas.php");
include("../Funciones.php");

session_start();


$listaUsuarios = $_SESSION["lista_usuarios"];

$listaProyectos = $_SESSION["lista_proyectos"];


//1-Obtener parametros

$usuarioLogeado = $_SESSION["usuario_comprobado"];

if (isset($_POST["pass_mod"])) {

    //1-Obtener parametros

    if (validarUsuario($_POST["usuario_mod"])) {
        $nombre = $_POST["usuario_mod"];
    }
    if (validarPasswd($_POST["pass_mod"])) {
        $passwd = $_POST["pass_mod"];
    }
    if (validarTipoUser($_POST["tipo_mod"])) {
        $tipoUsuario = $_POST["tipo_mod"];
    }


    //2-Crear objeto

    $usuarioDTO = new Usuario();
    $usuarioDTO->setId($_SESSION["usuario_id"]);
    $usuarioDTO->setNombre($nombre);
    $usuarioDTO->setPass($passwd);
    $usuarioDTO->setTipoUsuario($tipoUsuario);

    //3-Conectar BBDD

    $db = new DB_Helper();

    $conn = $db->conectarBD();

    //4-Operaciones con BBDD
    if (validarUsuario($nombre) && validarPasswd($passwd) && validarTipoUser($tipoUsuario)) {
        $db->modificarUsuario($conn, $usuarioDTO);
    }
    $db->actualizarVista($conn);

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
}

if (isset($_POST["proyecto_mod"])) {

    //1-Obtener parametros

    if (validarProyecto($_POST["proyecto_mod"])) {
        $nom_proyecto = $_POST["proyecto_mod"];
    }

    //2-Crear objeto

    $proyectoDTO = new Proyecto();
    $proyectoDTO->setId($_SESSION["proyecto_id"]);
    $proyectoDTO->setNombre($nom_proyecto);


    //3-Conectar BBDD

    $db = new DB_Helper();

    $conn = $db->conectarBD();

    //4-Operaciones con BBDD
    if (validarProyecto($nom_proyecto)) {
        $db->modificarProyecto($conn, $proyectoDTO);
    }

    $db->actualizarVista($conn);

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
}

if (isset($_POST["tarea_mod"])) {

    //1-Obtener parametros
    $listaUsuarios = $_SESSION["lista_usuarios"];

    $listaProyectos = $_SESSION["lista_proyectos"];

    $usuarioLogeado = $_SESSION["usuario_comprobado"];

    if ($_SESSION["proyecto_actual"] != null) {

        $Proyecto = obtenerIdDeLista($listaProyectos, $_SESSION["proyecto_actual"]);
    }

    if ($_SESSION["nom_usuario"] != null) {
        $Usuario = obtenerIdDeLista($listaUsuarios, $_SESSION["nom_usuario"]);
    }
    if ($_POST["tarea_mod"] != null) {
        $Tarea = $_POST["tarea_mod"];
    }
    if ($_POST["estado_mod"] != null) {
        $Estado = $_POST["estado_mod"];
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
    $db->modificarTarea($conn, $tareaDTO);

    $db->actualizarVista($conn);

    //actualizar valores lista tareas para el HTML
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
}
