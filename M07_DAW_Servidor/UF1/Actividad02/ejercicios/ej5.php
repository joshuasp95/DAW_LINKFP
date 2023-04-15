<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../estilos/ej5.css">
    <title>Actividad 2</title>
</head>

<body>
    <section>
        <article>
            <h2>Ejercicio 5</h2>
            <p>
                Escribir un programa que nos pida un número de DNI y nos calcule la letra.
                El cálculo se realiza dividiendo el número entre 23
                y el resto se sustituye por letra que corresponda.
            </p>
        </article>
        <form action="" method="">
            <label for="DNI">
                Introduce el numero de DNI <input type="text" name="numDNI" placeholder="...">
            </label>
            <label for="letra">
                <input type="submit" value="Calcular letra" name="calcular">
            </label>
        </form>

        <?php
        if (isset($_REQUEST["calcular"])) {

            $letrasDNI = [
                0 => "T", 1 => "R", 2 => "W", 3 => "A",
                4 => "G", 5 => "M", 6 => "Y", 7 => "F",
                8 => "P", 9 => "D", 10 => "X", 11 => "B",
                12 => "N", 13 => "J", 14 => "Z", 15 => "S",
                16 => "Q", 17 => "V", 18 => "H", 19 => "L",
                20 => "C", 21 => "K", 22 => "E"
            ];

            $numeroDNI = $_REQUEST["numDNI"];


            $resultado = $numeroDNI % 23;

            $letraDNI = $letrasDNI[$resultado];

            if (is_numeric($numeroDNI) && (strlen($numeroDNI) == 8)) {
                echo "La letra que le corresponde al numero de DNI $numeroDNI es $letraDNI";
            } else {
                echo "NO se ha introducido una cadena de numeros valida<br>";
                echo "Solo debe haber 8 numeros";
            }
        }
        ?>
    </section>
</body>

</html>