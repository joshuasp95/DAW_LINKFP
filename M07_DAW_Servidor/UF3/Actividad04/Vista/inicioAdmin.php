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
    
    include("plantilla.html");

    if (isset($_POST["salir"])) {
        salirSesion($_POST["salir"]);
    }
    if (isset($_POST["volver"])) {
        volverInicio($_POST["volver"]);
    }

    $usuario = $_SESSION["usuario_comprobado"];

    echo "<h2>Bienvenido " . $usuario->getNombre() . "</h2>";
    if ($_SESSION["lista_usuarios"] != null && count($_SESSION["lista_usuarios"]) > 0) {

        $listaUsuarios = $_SESSION["lista_usuarios"];

    ?>
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Tipo de Usuario</th>
                <th colspan="3">Opciones</th>
            </tr>
            <?php
            for ($i = 0, $j = 1; $i < count($listaUsuarios); $i++, $j++) {
                echo "<tr><td>" . $j . "</td>";
                echo "<td>" . $listaUsuarios[$i]->getNombre() . "</td>";
                echo "<td>" . convTipoUsuarioTexto($listaUsuarios[$i]->getTipoUsuario()) . "</td>";
                echo "<td>";
            ?>
                <form class="form_Proyecto" action="v_modificar.php" method="post">
                    <input type="hidden" name="usuario_modif" value="<?php echo $listaUsuarios[$i]->getId() ?>" />
                    <input type="submit" value="Modificar">
                </form>

                <?php
                echo  "</td>";
                echo "<td>";
                ?>
                <form class="form_Usuario" action="../Controlador/Controlador/Delete/BorrarUsuarios.php" method="post">
                    <input type="hidden" name="usuario_selecc" value="<?php echo $listaUsuarios[$i]->getId() ?>">
                    <input type="submit" value="Borrar">
                </form>

        <?php
                echo "</td>";
                echo "</tr>";
            }
        } else {
            //Para que no muestre un warning tipo undefined, por que la variable no tenga valor
            echo "<h2>Todavia no hay datos para mostrar</h2";
            $listaUsuarios = 0;
        }
        ?>

        </table>

        <h2>Añadir Usuario</h2>

        <form action="../Controlador/Controlador/Create/InsertarUsuario.php" method="post">

            <label for="new_user">Usuario:
                <input type="text" name="new_user" id="new_user">
            </label>
            <label for="new_pass">Contraseña:
                <input type="password" name="new_pass" id="new_pass">
            </label>

            <input type="radio" name="tipo" id="tipo1" value="0">
            <label for="tipo1">Administrador</label>

            <input type="radio" name="tipo" id="tipo2" value="1">
            <label for="tipo2">Usuario normal</label>

            <input type="submit" value="Añadir">

        </form>
        <?php
        if ($_SESSION["lista_proyectos"] != null && count($_SESSION["lista_proyectos"]) > 0) {
            $listaProyectos = $_SESSION["lista_proyectos"];
        ?>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th colspan="3">Opciones</th>
                </tr>
                <?php
                for ($i = 0, $j = 1; $i < count($listaProyectos); $i++, $j++) {
                    echo "<tr><td>" . $j . "</td>";
                    echo "<td>" . $listaProyectos[$i]->getNombre() . "</td>";
                    echo "<td>";
                ?>
                    <form class="form_Proyecto" action="../Controlador/Controlador/Read/MostrarTareas.php" method="post">
                        <input type="hidden" name="nm_proyecto" value="<?php echo $listaProyectos[$i]->getNombre() ?>" />
                        <input type="submit" value="Ver tareas">
                    </form>

                    <?php
                    echo  "</td>";
                    echo "<td>";
                    ?>
                    <form class="form_Proyecto" action="v_modificar.php" method="post">
                        <input type="hidden" name="proyecto_modif" value="<?php echo $listaProyectos[$i]->getId() ?>" />
                        <input type="submit" value="Modificar">
                    </form>

                    <?php
                    echo  "</td>";
                    echo "<td>";
                    ?>
                    <form class="form_Proyecto" action="../Controlador/Controlador/Delete/BorrarProyectos.php" method="post">
                        <input type="hidden" name="proyecto_selecc" value="<?php echo $listaProyectos[$i]->getId() ?>">
                        <input type="submit" value="Borrar">
                    </form>

            <?php
                    echo "</td>";
                    echo "</tr>";
                }
            } else {
                echo "<h2>Todavia no hay datos para mostrar</h2";
                $listaProyectos = 0;
            }
            ?>
            </table>

            <h2>Añadir Proyecto</h2>

            <form action="../Controlador/Controlador/Create/InsertarProyecto.php" method="post">

                <label for="new_proyecto">Nombre:
                    <input type="text" name="new_proyecto" id="new_proyecto">
                </label>

                <input type="submit" value="Añadir">

            </form>

</body>

</html>