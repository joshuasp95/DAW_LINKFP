<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../estilos/ej2.css">
    <title>Actividad 2 - Joshua Sainz</title>
</head>

<body>
    <section>
        <article>
            <h3>Ejercicio 2</h3>
            <p>
                Escribe un programa en el que se declare un array asociativo para guardar las notas de unos alumnos. Las claves del array serán los nombres de los alumnos y los valores serán las notas de cada uno.
                El programa deberá hacer las operaciones necesarias para mostrar los siguientes mensajes (los datos que se muestran son de ejemplo, los tuyos cambiarán en función de los valores del array):
            <ul>
                <li>La nota más alta es la de David con un 9.</li>
                <li>La nota más baja es la de Sandra con un 3.</li>
                <li>La nota media de la clase es 6.4.</li>
            </ul>
            Además, se mostrarán los nombres de los alumnos, acompañados de su nota, ordenados por esta última (ascendentemente).
            </p>
        </article>

        <div class="listaOrdenada">
            <?php

            //Declaramos y damos valores al array
            $alumnos = [
                "Emilio" => 5.2,
                "Juan" => 8.5,
                "Paloma" => 9.1,
                "Belen" => 3.4,
                "Andres" => 2.4,
                "Isabel" => 4.1,
                "Lucia" => 6.3
            ];

            //Ordenamos el array en funcion de la nota de cada alumno (nota mas baja primero)

            asort($alumnos);

            //mostramos los valores de las claves del array asociativo y sus valores (alumnos => notas)

            foreach ($alumnos as $key => $value) {
                echo "<p>$key tiene una nota de: $value</p>";
            }

            ?>
        </div>

        <div class="resultados">
            <?php
            //Declaramos la variable maxima nota con un valor minimo
            $maxNota = 0;
            /*Si al iniciar el array la nota del primer alumno es mayor que la nota maxima previamente establecida
            se guardara ese nuevo valor como la nota maxima y si la siguiente nota es mas alta se guardara
            nuevamente con ese valor*/
            foreach ($alumnos as $notas) {
                if ($notas > $maxNota) {
                    $maxNota = $notas;
                }
            }
            echo "<p>La nota mas alta es: $maxNota</p>";

            //Mismo procedimiento anterior pero para la nota minima
            $minNota = $maxNota;
            foreach ($alumnos as $notas) {
                if ($notas < $minNota) {
                    $minNota = $notas;
                }
            }
            echo "<p>La nota mas baja es: $minNota<p/>";

            //Para calcular la media sumamos las notas de todos los alumnos / numero de alumnos
            $suma = 0;

            foreach ($alumnos as $notas) {
                $suma += $notas;
            }

            $media = $suma / count($alumnos);

            $media = sprintf("%.2f", $media);

            echo "<p>La nota media es: $media</p>";

            ?>
        </div>

    </section>

</body>

</html>