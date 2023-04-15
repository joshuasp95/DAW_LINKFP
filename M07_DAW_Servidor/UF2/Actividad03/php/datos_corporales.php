<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style.css?1.0">
    <link rel="stylesheet" href="../css/ej2.css?4.0">
    <title>Actividad 3 - Joshua Sainz</title>
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Last-Modified" content="0">
    <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
    <meta http-equiv="Pragma" content="no-cache">
</head>

<body>
    <?php
    include '../plantilla.html';
    ?>
    <main id="cuerpo">
        <form action="guardar_datos.php" method="POST">
            <h3>Introduce los siguientes datos</h3>
            <label for="height">Introduce tu altura: (cm)</label>
            <input type="text" name="height" id="height" required pattern="[0-9]{3}">
            <label for="weight">Introduce tu peso: (kg)</label>
            <input type="text" name="weight" id="weight" pattern="[0-9]+" maxlength="3" minlength="2">
            <input type="submit" value="Enviar" name="enviar_datos" required>
            <?php
            session_start();
            //mostramos los mensajes de validacion de datos 
            if (isset($_SESSION["validar_altura"])) {
                echo "<label>" . $_SESSION["validar_altura"] . "</label>";
                echo "<label>" . $_SESSION["validar_peso"] . "</label>";
            }
            //una vez mostrados borramos las sesiones para que no aparezcan 
            //al actualizar la pagina
            unset($_SESSION["validar_altura"]);
            unset($_SESSION["validar_peso"]);
            ?>
        </form>
    </main>
</body>

</html>