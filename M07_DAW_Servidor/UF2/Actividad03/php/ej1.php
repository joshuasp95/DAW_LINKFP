<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style.css?1.0">
    <link rel="stylesheet" href="../css/ej1.css?3.0">
    <title>Actividad 3 - Joshua Sainz</title>
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Last-Modified" content="0">
    <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
    <meta http-equiv="Pragma" content="no-cache">
</head>

<body>
    <?php
    // Incluimos la plantilla del html con la cabecera y navegador del proyecto
    include_once '../plantilla.html';
    ?>
    <main id="cuerpo">
        <form method="POST">
            <h3>Introduce un numero (1 - 100)</h3>
            <!-- En el html validamos tambien los parametros introducidos por el usuario para evitar 
            errores -->
            <input type="number" id="numero" name="numero" min="1" max="100">
            <input type="submit" value="Enviar" name="enviar">
            <input type="submit" value="Reset" name="reset">
            <div id="resultado">
                <?php
                // Iniciamos la sesion para poder utilizar las variables de sesion
                session_start();
                if (isset($_POST["reset"])) {
                    echo "<p>Vuelve a introducir un numero para volver a jugar</p>";
                    session_destroy();
                }
                if (isset($_POST["enviar"])) { //Solo se ejecuta en caso de que se haya pulsado el boton enviar

                    //Ponemos un try/catch para controlar errores
                    try {

                        // He intentado controlar el error que se genera en caso de que no se introduzca 
                        // ningun valor a traves del formulario y por lo tanto el $_POST queda vacio
                        // y aparece un warning que explica que el no esta definida esa variable

                        if (isset($_SESSION["numeroAleatorio"])) { //se entra aqui si existe una 
                            // variable de sesion con el numero aleatorio creado

                            if (!$_POST["numero"]) {
                                //Lanzamos la excepcion en caso de que no se haya introducido un numero
                                throw new Exception('Numero no introducido');
                            } else {
                                if ($_POST["numero"] < 1 || $_POST["numero"] > 100) {
                                    //Validamos que se haya introducido un numero correcto
                                    echo "<p>El numero introducido esta fuera de los parametros </p>";
                                    echo "<p>Debes introducir un numero de 1 a 100</p>";
                                } else {
                                    //En caso contrario declaramos y asignamos el valor del numero a una variable
                                    $numUser = $_POST["numero"];

                                    // Ahora damos indicaciones al usuario de si el numero introducido es 
                                    // mayor o menor que el numero generado por el servidor
                                    if ($numUser > $_SESSION["numeroAleatorio"]) {
                                        echo "<p>El numero introducido es mayor que el numero aleatorio</p>";
                                    } else if ($numUser < $_SESSION["numeroAleatorio"]) {
                                        echo "<p>El numero introducido es menor que el numero aleatorio</p>";
                                    } else if ($_SESSION["numeroAleatorio"] == $numUser) {
                                        //Si el usuario ha acertado le avisamos con un mensaje
                                        //y tambien eliminamos la variable de sesion generada
                                        echo "<p>Has acertado el numero aleatorio era " . $_SESSION["numeroAleatorio"] . "!</p>";
                                        unset($_SESSION["numeroAleatorio"]);
                                    }
                                }
                            }
                        } else {
                            //al iniciar por primera vez se crea una variable session con el valor
                            // de un numero aleatorio generado por el metodo rand y no se vuelve a ejecutar
                            // hasta que se elimine la variable de sesion
                            $_SESSION["numeroAleatorio"] = rand(1, 100);
                        }
                    } catch (Exception $e) {
                        echo "<p>Error detectado: ", $e->getMessage(), "</p>";
                    }
                } else {
                    echo "";
                }
                ?>
            </div>
        </form>
    </main>


</body>

</html>