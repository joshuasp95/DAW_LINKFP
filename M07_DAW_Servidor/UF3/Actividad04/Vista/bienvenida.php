<?php
include_once("../Controlador/DTO/Usuario.php");
include_once("../Controlador/DTO/Proyecto.php");
include_once("../Controlador/DTO/Tarea.php");
include_once("../Controlador/DTO/V_Tareas.php");
include("../Controlador/Controlador/Funciones.php");
session_start();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actividad 4 M07 - Joshua Sainz</title>
    <link rel="stylesheet" href="style.css?4.0">
</head>

<body>
    <form class="salir" action="#" method="post"><input type="submit" value="Salir" name="salir"></form>
    <?php
    if (isset($_SESSION["usuario_comprobado"])) {
        comprobarNormalUsu($_SESSION["usuario_comprobado"]);
    } else {
        header("Location: index.php");
    }

    include("plantilla.html");
    if (isset($_POST["salir"])) {
        salirSesion($_POST["salir"]);
    }

    $usuario = $_SESSION["usuario_comprobado"];

    echo "<h2>Bienvenido " . $usuario->getNombre() . "</h2>";
    ?>
    <h2>Consulta el estado de tus tareas</h2>

    <?php
    if (isset($_SESSION["lista_tareas_usuario"])) {

    ?>
        <table>
            <tr>
                <th>ID</th>
                <th>Proyecto</th>
                <th>Tarea</th>
                <th>Estado</th>
            </tr>
        <?php

        $listaTareasUsuario = $_SESSION["lista_tareas_usuario"];

        for ($i = 0, $j = 1; $i < count($listaTareasUsuario); $i++, $j++) {
            echo "<tr><td>" . $j . "</td>";
            echo "<td>" . $listaTareasUsuario[$i]->getProyecto() . "</td>";
            echo "<td> " . $listaTareasUsuario[$i]->getTarea() . " </td>";
            echo "<td> " . convEstadoTexto($listaTareasUsuario[$i]->getEstado()) . " </td></tr>";
        }
    } else {
        //Para que no muestre un warning tipo undefined, por que la variable no tenga valor
        echo "<h2>Todavia no hay datos para mostrar</h2";
        $_SESSION["lista_tareas_usuario"] = 0;
    }
        ?>

        </table>

</body>

</html>