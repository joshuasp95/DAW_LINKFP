<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>M07: Actividad 1 - Joshua Sainz</title>
</head>

<body>

    <div class="apartados apartado1">
        <h2 class="titulo">Ejercicio 1</h2>
        <?php
        //APARTADO 1
        //Declaramos e inicializamos las variables

        $euros = 120;
        $dolares = 0;
        $yenes = 0;

        //2 maneras de declarar constantes

        const EURO_DOLAR = 0.98;

        define("EURO_YEN", 142.54);

        //hacemos las operaciones

        $dolares = $euros * EURO_DOLAR;
        $yenes = $euros * EURO_YEN;

        //convertimos a un formato de 2 decimales

        $dolares = sprintf("%.2f", $dolares);

        $yenes = sprintf("%.2f", $yenes);

        echo "$euros € equivalen a $dolares dolares y " . $yenes . " yenes.";

        ?>
    </div>

    <div class="apartados apartado2">
        <h2 class="titulo">Ejercicio 2</h2>
        <?php
        //APARTADO 2
        //salto de linea

        //Declaramos e inicializamos las variables
        $numAlumnos1 = 20;
        $numAlumnos2 = 13;
        $numAlumnos3 = 16;
        echo "Hay $numAlumnos1 en la primera clase, $numAlumnos2 en la segunda clase y $numAlumnos3 en la tercera clase.<br>";
        //hacemos las operaciones, suma de alumnos y 2 alumnos por pupitre
        $numPupitres = ($numAlumnos1 + $numAlumnos2 + $numAlumnos3) / 2;
        //Redondeamos el numero hacia arriba para que sobren pupitres
        $numPupitres = round($numPupitres, 0, PHP_ROUND_HALF_UP);
        echo "El numero de pupitres necesarios para los alumnos de las 3 clases es " . $numPupitres;
        ?>
    </div>

    <div class="apartados apartado3">
        <h2 class="titulo">Ejercicio 3</h2>
        <?php
        //APARTADO 3

        //Declaramos e inicializamos las variables, ax2+bx+c=0
        $a = -7;
        $b = 4;
        $c = 0;
        echo "La ecuacion de segundo grado es: $a x^2 + $b x + $c = 0. <br>";
        $result1 = 0;
        $result2 = 0;
        //Hacemos las operaciones, (-b+sqrt(b2-4ac))/2a y -b-sqrt(b2-4ac))/2a
        //operacion para resultado 1
        $result1 = (- ($b) + sqrt((pow($b, 2)) - (4 * $a * $c))) / (2 * $a);
        $result2 = (- ($b) - sqrt((pow($b, 2)) - (4 * $a * $c))) / (2 * $a);
        //para que no saque por pantalla un -0
        if ($result1 == -0) {
            $result1 = 0;
        }
        if ($result2 == -0) {
            $result2 = 0;
        }
        //redondeamos para que solo salgan 2 decimales
        echo "El primer resultado es: " . round($result1, 2);
        echo "<br>";
        echo "El segundo resultado es: " . round($result2, 4);
        ?>
    </div>

    <div class="apartados apartado4">
        <h2 class="titulo">Ejercicio 4</h2>
        <?php
        //APARTADO 4

        $diametroPizza = 30; //en cm
        $areaPizza = 0; //en cm2
        $longBorde = 0;
        $radioPizza = 0;
        //Operaciones
        $radioPizza = $diametroPizza / 2;
        $areaPizza = pow($radioPizza, 2) * M_PI;
        $longBorde = 2 * $radioPizza * M_PI;
        echo "La pizza tiene un diametro de $diametroPizza cm, un area de " . round($areaPizza, 2) . " cm2 y el borde tiene de longitud " . round($longBorde, 2) . " cm.";
        ?>
    </div>


    <div class="apartados apartado5">
        <h2 class="titulo">Ejercicio 5</h2>
        <?php
        //APARTADO 5
        //Declaramos e inicializamos las variables
        define("SALARIO_HORA_NORMAL", 30); //€/hora
        define("INCREMENTO", 0.5);  //50% extra
        $horasTrabajoNormal = 40;
        $horasExtras = 0;
        $horasTotales = 0;
        $sueldoNormal = 0;
        $sueldoExtra = 0;
        $sueldoFinal = 0;
        //Definimos un valor de horas extra para comprobar que el programa funciona correctamente
        $horasExtras = 8;
        //Hacemos las operaciones
        $horasTotales = $horasTrabajoNormal + $horasExtras;
        $sueldoNormal = $horasTrabajoNormal * SALARIO_HORA_NORMAL;
        echo "Un trabajador en horario normal; trabaja $horasTrabajoNormal horas y cobra $sueldoNormal €. <br>";
        $sueldoExtra = $horasExtras * (SALARIO_HORA_NORMAL * INCREMENTO);
        $sueldoFinal = $sueldoNormal + $sueldoExtra;
        if ($horasExtras > 0) {
            echo "En este caso, el trabajador ha hecho $horasExtras horas extra. ";

            echo "En total ha trabajado $horasTotales horas y su sueldo es $sueldoFinal €.";
        } else {
            echo "El trabajador no ha hecho horas extras. ";

            echo "Su sueldo es " . $horasTrabajoNormal * SALARIO_HORA_NORMAL . " €.";
        }
        ?>
    </div>


</body>

</html>