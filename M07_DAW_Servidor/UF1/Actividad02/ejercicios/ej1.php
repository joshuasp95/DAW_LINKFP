<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../estilos/ej1.css">
    <title>Actividad 2 - Joshua Sainz</title>
</head>

<body>


    <section>
        <article>
            <h3>Ejercicio 1</h3>
            <p>
                Crea una aplicación que parta de un formulario en que se pida al usuario dos valores: inicio y fin. El programa mostrará por pantalla todos los valores que hay entre los dos introducidos, ordenados crecientemente.
                <i>Por ejemplo, si el usuario introduce los valores 5 y 10, el programa mostrará 6 7 8 9.</i>
            </p>
        </article>

        <form name="ejercicio1" action="" method="post">

            <ul>

                <!-- Pedimos los valores al usuario con el formulario -->

                <li>
                    <label for="">Introduce un numero de inicio</label>
                    <input type="number" name="numIni">
                </li>

                <li>
                    <label for="">Introduce un numero final</label>
                    <input type="number" name="numFin">
                </li>

                <li><input type="submit" value="Enviar" name="enviar"></li>

                <p>Resultado: </p>

                <?php
                if (isset($_REQUEST["enviar"])) {
                ?>
                    <!--Cerramos bloque de php para que solo salga 
                    el recuadro del elemento de la lista con el resultado 
                    de los numeros si se pulsa el boton Enviar -->

                    <li class="resultNumeros">
                    <?php

                    //En caso de no haber introducido los 2 numeros al pulsar el isset
                    if (empty($_REQUEST["numIni"]) == true || empty($_REQUEST["numFin"]) == true) {
                        echo "Error: Introduce 2 numeros";
                    } else {

                        //Recogemos los valores introducidos por el usuario en el formulario
                        $numInicial = $_REQUEST["numIni"];

                        $numFinal = $_REQUEST["numFin"];

                        //En caso de que el numero final sea menor que el numero inicial

                        if ($numFinal < $numInicial) {
                            echo "Error: El numero final debe ser mayor que el inicial";
                            //No quedaria bien maquetado en caso de mostrar muchos numeros
                        } else if ($numFinal - $numInicial > 15) {
                            echo "Error: El limite de muestra está en 15 numeros";
                        }
                        //Solo se ejecuta si no se cumplen los otros 2 condicionantes
                        else {
                            for ($i = ($numInicial + 1); $i < $numFinal; $i++) {
                                echo "<p class=\"numeros\">[ $i ]</p>";
                            }
                        }
                    }
                }
                    ?>
                    </li>

            </ul>

        </form>

    </section>


</body>

</html>