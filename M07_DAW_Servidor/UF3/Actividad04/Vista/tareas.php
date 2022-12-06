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

    //volvemos a vaciar la sesion para reiniciar su valor
    unset($_SESSION["usuarios_sin_tareas_act"]);

    include("plantilla.html");

    $usuario = $_SESSION["usuario_comprobado"];

    $listaUsuarios = $_SESSION["lista_usuarios"];

    echo "<h2>Bienvenido " . $usuario->getNombre() . "</h2>";

    echo "<h3>Proyecto Seleccionado: <u>" . $_SESSION["nombre_proyecto"] . "</u></h3>";

    if ($_SESSION["lista_tareas"] != null && count($_SESSION["lista_tareas"]) > 0) {

        $listaTareas = $_SESSION["lista_tareas"];

        $_SESSION["proyecto_actual"] = $listaTareas[0]->getProyecto();
        $arrUsuariosTareas = array();

    ?>

        <table>
            <tr>
                <th>ID</th>
                <th>Usuario</th>
                <th>Tarea</th>
                <th>Estado</th>
                <th colspan="2">Opciones</th>
            </tr>
            <?php
            for ($i = 0, $j = 1; $i < count($listaTareas); $i++, $j++) {

                echo "<tr><td>" . $j . "</td>";
                echo "<td>" . $listaTareas[$i]->getUsuario() . "</td>";

                array_push($arrUsuariosTareas, $listaTareas[$i]->getUsuario());

                echo "<td> " . $listaTareas[$i]->getTarea() . " </td>";
                echo "<td> " . convEstadoTexto($listaTareas[$i]->getEstado()) . " </td>";
                echo "<td>";
            ?>
                <form class="form_Tarea" action="v_modificar.php" method="post">
                    <input type="hidden" name="tarea_nombre" value="<?php echo $listaTareas[$i]->getTarea() ?>" />
                    <input type="hidden" name="tarea_modif_usuario" value="<?php echo $listaTareas[$i]->getUsuario() ?>" />
                    <input type="submit" value="Modificar">
                </form>

                <?php
                echo  "</td>";
                echo "<td>";
                ?>
                <form class="form_Tarea" action="../Controlador/Controlador/Delete/BorrarTareas.php" method="post">
                    <input type="hidden" name="tarea_usuario_selecc" value="<?php echo $listaTareas[$i]->getUsuario() ?>">
                    <input type="submit" value="Borrar">
                </form>

        <?php
                echo  "</td></tr>";
            }
        } else {
            //Para que no muestre un warning tipo undefined, por que la variable no tenga valor
            echo "<h2>Todavia no hay datos para mostrar</h2";
            $listaTareas = 0;
        }
        ?>

        </table>

        <h2>Añadir Tarea</h2>

        <form action="../Controlador/Controlador/Create/InsertarTarea.php" method="post">

            <p>Usuario:</p>
            <select name="new_usuario">
                <?php
                if (isset($_SESSION["usuarios_sin_tareas_act"])) {
                    // $longitud_lista_act = count($_SESSION["usuarios_sin_tareas_act"]);
                    $lista_usuarios_tareas_act = $_SESSION["usuarios_sin_tareas_act"];
                } else {
                    $_SESSION["usuarios_sin_tareas_act"] = $listaUsuarios;
                    $longitud_lista_act = count($listaUsuarios);
                    $lista_usuarios_tareas_act = $listaUsuarios;
                }
                if (isset($arrUsuariosTareas)) {
                    for ($i = 0; $i < count($arrUsuariosTareas); $i++) {
                        for ($z = 0; $z < $longitud_lista_act; $z++) {
                            //coge el valor del array de los usuarios con tareas y recorre
                            //la lista de usuarios existentes para mostrar solo
                            //aquellos que no tienen tareas (hay 2 PK en la BBDD) y no
                            //deja insertar valores duplicados es decir un mismo usuario con 2 tareas
                            if ($arrUsuariosTareas[$i] == $listaUsuarios[$z]->getNombre()) {
                                unset($_SESSION["usuarios_sin_tareas_act"][$z]);
                                //actualizamos las posiciones del array
                                // $_SESSION["usuarios_sin_tareas_act"] = array_values($_SESSION["usuarios_sin_tareas_act"]);
                            }
                        }
                    }
                }
                if (isset($_SESSION["usuarios_sin_tareas_act"])) {
                    $_SESSION["usuarios_sin_tareas_act"] = array_values($_SESSION["usuarios_sin_tareas_act"]);
                    for ($i = 0; $i < count($_SESSION["usuarios_sin_tareas_act"]); $i++) {
                        echo "<option value='" . $_SESSION["usuarios_sin_tareas_act"][$i]->getNombre() . "'> " .
                            $_SESSION["usuarios_sin_tareas_act"][$i]->getNombre() . "</option>";
                    }
                }

                ?>
            </select>

            <label for="new_tarea">Tarea:
                <input type="text" name="new_tarea" id="new_tarea" pattern="[A-Za-z0-9 ]*" required>
            </label>

            <p class="estado_tarea">Estado:</p>

            <input type="radio" name="new_estado" id="estado1" value="1">
            <label for="estado1">Pendiente</label>

            <input type="radio" name="new_estado" id="estado2" value="2">
            <label for="estado2">En progreso</label>

            <input type="radio" name="new_estado" id="estado3" value="3">
            <label for="estado3">Finalizada</label>


            <input type="submit" value="Añadir">

        </form>

</body>

</html>