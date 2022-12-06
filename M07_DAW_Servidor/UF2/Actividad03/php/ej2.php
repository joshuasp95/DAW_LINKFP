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
            <label for="name">Nombre usuario: </label>
            <!-- Validamos los datos con un pattern con una expresion regular que solo permita letras 
            y tendra un tamaño minimo de 5 y maximo de 15 caracteres -->
            <input type="text" name="name" id="name" required maxlength="15" minlength="5" pattern="[A-Za-z]+">
            <label for="birth">Año nacimiento: </label>
            <!-- En este caso validamos el año de nacimiento con numeros y un tamaño de 4 -->
            <input type="text" name="birth" id="birth" required pattern="[0-9]{4}">
            <input type="submit" value="Enviar" name="enviar">
        </form>
    </main>
</body>

</html>