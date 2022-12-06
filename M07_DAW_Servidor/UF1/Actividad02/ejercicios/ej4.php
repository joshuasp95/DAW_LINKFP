<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../estilos/ej4.css">
    <title>Document</title>
</head>

<body>
    <section>
        <h2>Candado</h2>
        <article>En nuestro programa, el candado vendrá representado por un array de 8 posiciones, todas ellas con el valor “false” inicialmente. Nuestro candado empezará con el botón 0 y acabará con el 7.
            El usuario introducirá en un formulario la secuencia de botones que pulsa, separados por coma. Por ejemplo, una secuencia correcta seria: 1,2,3,4,4,4,4,3,5.
            El programa mostrará el estado del candado después de que el usuario haya introducido la secuencia. Para la secuencia anterior, el resultado sería: false, true, true, false, false, true, false, false.
            Quizás esta función te puede ser útil: <u>explode.</u> </article>
        <form name="ejercicio4" action="" method="post">

            <label for="candado">Introduce la combinación de numeros (separados con coma ",")</label>
            <input type="text" name="combinacion">

            <input type="submit" value="Enviar" name="enviar">

        </form>
        <p>Resultado:
            <?php

            //Ponemos un isset para que el codigo solo se ejecute cuando se pulse enviar

            if (isset($_REQUEST["enviar"])) {

                /*Declaramos 2 arrays, uno que contendra los numeros del candado
y otro array que recogera la combinacion de numeros introducida por el usuario
que sera de tipo STRING*/
                $valoresCandado = array();
                $combinacionUsuario = array();
                //Asignamos los valores al Candado del 0 al 7
                for ($i = 0; $i < 8; $i++) {
                    $valoresCandado[$i] = "false";
                    $valoresCandadoFinales[$i] = "false";
                }
                //Recogemos los valores introducidos por el usuario

                $combinacionUsuario = explode(",", $_REQUEST["combinacion"]);

                /*Con 2 for vamos recorriendos ambos arrays y definiendo que cuando
se cumpla que el valor introducido por el usuario es igual al boton del candado (en este caso, el valor asociativo del array)
se cambia de valor a true o false segun el estado en el que se encuentre el candado, si estaba en false se cambia a true
y si estaba en true a false*/

                for ($i = 0; $i < count($combinacionUsuario); $i++) {
                    foreach ($valoresCandado as $claveCandado => $valorCandado) {
                        if ($combinacionUsuario[$i] == $claveCandado && $valorCandado == "true") {
                            $valoresCandado[$claveCandado] = "false";
                            break;
                        }
                        if ($combinacionUsuario[$i] == $claveCandado && $valorCandado == "false") {
                            $valoresCandado[$claveCandado] = "true";
                            break;
                        }
                    }
                }

                //Mostramos el resultado de los valores del candado despues de ser modificado por el usuario

                foreach ($valoresCandado as $key => $value) {
                    echo "[$value]";
                }
            }

            ?>
        </p>
    </section>



</body>

</html>