<?php
include_once("../Controlador/DTO/Usuario.php");
include_once("../Controlador/DTO/Proyecto.php");
include_once("../Controlador/DTO/Tarea.php");
include_once("../Controlador/DTO/V_Tareas.php");
include("../Controlador/Controlador/Funciones.php");

// echo $_POST["proyect_modif"];




session_start();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actividad 4 M07 - Joshua Sainz</title>
    <link rel="stylesheet" href="style.css?9.0">
</head>

<body>
    <form class="salir" action="#" method="post"><input type="submit" value="Salir" name="salir"></form>
    <form class="volver" action="#" method="post"><input type="submit" value="Volver" name="volver"></form>

    <?php

    if (isset($_SESSION["usuario_comprobado"])) {
        comprobarAdmin($_SESSION["usuario_comprobado"]);
    } else {
        header("Location: index.php");
    }

    if (isset($_POST["salir"])) {
        salirSesion($_POST["salir"]);
    }

    if (isset($_POST["volver"])) {
        volverInicio($_POST["volver"]);
    }

    //obtener parametros
    $listaUsuarios = $_SESSION["lista_usuarios"];

    $listaProyectos = $_SESSION["lista_proyectos"];

    //hay que controlarlo porque a no ser que se pase primero por tareas.php no se genera la lista
    if (isset($_SESSION["lista_tareas"])) {
        $listaTareas = $_SESSION["lista_tareas"];
    }

    include("plantilla.html");

    $usuario = $_SESSION["usuario_comprobado"];

    echo "<h2>Bienvenido " . $usuario->getNombre() . "</h2>";

    //en este caso no seguido el esquema de por cada dto un archivo crud para ver como quedaba

    //si es para modificar un usuario
    if (isset($_POST["usuario_modif"])) {

        $usuarioObj = obtenerObjetoDeId($listaUsuarios, $_POST["usuario_modif"]);

        $_SESSION["usuario_id"] = $usuarioObj->getId();
    ?>


        <h2>Modificar</h2>

        <h3>Usuario: <strong><?php echo $usuarioObj->getNombre() ?></strong></h3>

        <form action="../Controlador/Controlador/Update/Modificar.php" method="post">

            <label for="usuario_mod">Nuevo Nombre:
                <input type="text" name="usuario_mod" id="usuario_mod" placeholder="Nombre..." required>
            </label>
            <label for="pass_mod">Contraseña:
                <input type="text" name="pass_mod" id="pass_mod" value="<?php echo $usuarioObj->getPass() ?>" required>
            </label>

            <input type="radio" name="tipo_mod" id="tipo_mod1" value="0">
            <label for="tipo_mod1">Administrador</label>

            <input type="radio" name="tipo_mod" id="tipo_mod2" value="1" checked>
            <label for="tipo_mod2">Usuario normal</label>

            <input type="submit" value="Modificar">

        </form>

    <?php
    }
//si es para modificar un proyecto
    if (isset($_POST["proyecto_modif"])) {

        $proyectoObj = obtenerObjetoDeId($listaProyectos, $_POST["proyecto_modif"]);

        $_SESSION["proyecto_id"] = $proyectoObj->getId();

    ?>


        <h2>Modificar</h2>

        <h3>Proyecto: <strong><?php echo $proyectoObj->getNombre() ?></strong></h3>

        <form action="../Controlador/Controlador/Update/Modificar.php" method="post">

            <label for="proyecto_mod">Nuevo Nombre:
                <input type="text" name="proyecto_mod" id="proyecto_mod" placeholder="Nombre...">
            </label>

            <input type="submit" value="Modificar">

        </form>

    <?php
    }
    
    //si es para modificar una tarea

    if (isset($_POST["tarea_modif_usuario"])) {

        $_SESSION["nom_usuario"] = $_POST["tarea_modif_usuario"];

    ?>


        <h2>Modificar</h2>
        <h3>Proyecto: <strong><?php echo $_SESSION["proyecto_actual"] ?></strong></h3>
        <h3>Usuario: <strong><?php echo $_POST["tarea_modif_usuario"] ?></strong></h3>
        <h3>Tarea: <strong><?php echo $_POST["tarea_nombre"] ?></strong></h3>

        <form action="../Controlador/Controlador/Update/Modificar.php" method="post">

            <input type="hidden" name="proyecto_mod" value="<?php echo $_SESSION["proyecto_actual"] ?>">
            <input type="hidden" name="usuario_mod" value="<?php echo $_SESSION["nom_usuario"] ?>">

            <label for="tarea_mod">Nueva Tarea:
                <input type="text" name="tarea_mod" id="tarea_mod" pattern="[A-Za-z0-9 ]*" required placeholder="Nombre...">
            </label>

            <p class="estado_tarea">Estado:</p>

            <input type="radio" name="estado_mod" id="estado1" value="1" checked>
            <label for="estado1">Pendiente</label>

            <input type="radio" name="estado_mod" id="estado2" value="2">
            <label for="estado2">En progreso</label>

            <input type="radio" name="estado_mod" id="estado3" value="3">
            <label for="estado3">Finalizada</label>


            <input type="submit" value="Modificar">

        </form>

    <?php
    }
    ?>


</body>

</html>