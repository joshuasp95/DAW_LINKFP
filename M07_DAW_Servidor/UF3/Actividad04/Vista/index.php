<?php

session_start();

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css?5.0">
    <title>Actividad 4 M07 - Joshua Sainz</title>
</head>

<body>
    <?php
    include("plantilla.html");
    ?>
    <h2>Introduce tus datos</h2>
    <form action="../Controlador/Controlador/LoginUsuario.php" method="post">
        <label for="user">Usuario:
            <input type="text" name="user" id="user">
        </label>
        <label for="pass">Contraseña:
            <input type="password" name="pass" id="pass">
        </label>

        <input type="submit" value="Enviar">

    </form>
    <?php
    if (isset($_SESSION["error_login"])) {
        echo '<h2>' . $_SESSION["error_login"] . '</h2>';
    }
    if (isset($_SESSION["error_url"])) {
        echo '<h2>' . $_SESSION["error_url"] . '</h2>';
        unset($_SESSION["error_url"]);
    }
    ?>
</body>


</html>