<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style.css?1.0">
    <link rel="stylesheet" href="../css/ej2.css?2.0">
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
        <form>
            <?php
            session_start();

            //Se ha inciado sesion y en caso de que las variables de sesion nombre y peso sean false
            // es decir no tengan asignado un valor se redirige a la pagina correspondiente para 
            // introducir los valores en el formulario
            if (!$_SESSION["nombre"] || !$_SESSION["peso"]) {
                echo "<h3>ERROR: FALTAN VARIABLES POR DEFINIR</h3>";
                if (!$_SESSION["nombre"]) {
                    header("refresh:2; url=ej2.php");
                }
                if (!$_SESSION["peso"]) {
                    header("refresh:2; url=datos_corporales.php");
                }
            }
            //Si ya se ha pasado por el formulario de nombre, es decir, si la variable sesion de nombre
            // ya tiene un valor asignado se recogen los valores del formulario datos_corporales.php
            // y se redirige a la pagina final de mostrar datos

            if (isset($_SESSION["nombre"])) {
                //Si ya se ha pulsado enviar datos se recogen los mismos
                if (isset($_POST["enviar_datos"])) {
                    //Pasamos a validar la altura y el peso 
                    $altura = (int)$_POST["height"];
                    $peso = (int)$_POST["weight"];
                    if (($altura > 100 && $altura < 250) && ($peso > 40 && $peso < 200)) {

                        //se pasa a un valor decimal la altura para poder dividirlo luego
                        $_SESSION["altura"] = floatval($_POST["height"]);

                        $_SESSION["peso"] = (int)$_POST["weight"];

                        $denominador =  $_SESSION["altura"] / 100;

                        $_SESSION["IMC"] = $_SESSION["peso"] / pow($denominador, 2);

                        header("Location: mostrar_datos.php");
                    } else {

                        $_SESSION["validar_altura"] = "Debes introducir un valor entre 100 y 250 cm";

                        $_SESSION["validar_peso"] = "Debes introducir un valor entre 40 y 200 kg";

                        header("Location: datos_corporales.php");
                    }
                } else {
                    //si no se ha pulsado el boton de enviar datos se redirige a la pagina datos_corporales.php
                    //debido a que puede ser que ya se haya establecido la sesion con el nombre del usuario
                    //y antes de validar los datos se muestren los mensajes de validacion de los mismos
                    //para evitar eso forzamos a que haya que pulsar el boton de enviar los datos corporales
                    //para validar esos datos y no solo con el isset de la sesion nombre
                    header("Location: datos_corporales.php");
                }
            } else {

                $_SESSION["nombre"] = $_POST["name"];

                $anoNacimiento = (int)$_POST["birth"];

                //con date("Y") se obtiene el año actual y se pasa a un int para poder hacer operaciones
                $anoActual = (int)date("Y");

                $_SESSION["edad"] = $anoActual - $anoNacimiento;

                $edad = $anoActual - $anoNacimiento;

                header("Location: datos_corporales.php");
            }
            ?>
        </form>
    </main>
</body>

</html>