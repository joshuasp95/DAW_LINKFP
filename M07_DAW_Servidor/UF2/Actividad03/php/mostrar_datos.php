<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style.css?1.0">
    <link rel="stylesheet" href="../css/ej2.css?3.0">
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
            <div id="resultados">
                <?php
                //En esta pagina, con las validaciones ya hechas unicamente mostramos los resultados
                session_start();
                //debe estar declarada la sesion nombre y las demas sesiones aunque no es necesario ponerlo
                //ya que esta validado previamente
                if (isset($_SESSION["nombre"])) {
                    echo "<p>Hola " . $_SESSION["nombre"] . "</p>";
                    echo "<p>Tu edad es de " . $_SESSION["edad"] . " años</p>";
                    echo "<p>Tu IMC es de " . sprintf("%1\$.2f", $_SESSION["IMC"]) . "</p>";

                    //se inicializan todos los valores con "false" y se va cambiando a true el que corresponda
                    
                    $peso_insuficiente = "false";
                    $peso_normal = "false";
                    $sobrepeso = "false";
                    $obesidad = "false";

                    if ($_SESSION["IMC"] < 18.5) {
                        $peso_insuficiente = "true";
                    } elseif ($_SESSION["IMC"] > 18.5 && $_SESSION["IMC"] < 24.9) {
                        $peso_normal = "true";
                    } elseif ($_SESSION["IMC"] > 25 && $_SESSION["IMC"] < 50) {
                        $sobrepeso = "true";
                    } elseif ($_SESSION["IMC"] > 50) {
                        $obesidad = "true";
                    } else {
                        echo "error";
                    }

                    echo "<p>Peso insuficiente..." . $peso_insuficiente . "</p>";
                    echo "<p>Peso normal..." . $peso_normal . "</p>";
                    echo "<p>Sobrepeso..." . $sobrepeso . "</p>";
                    echo "<p>Obesidad..." . $obesidad . "</p>";
                } else {
                    echo "<p>Introduce tus datos</p>";
                    header("refresh:3; url=ej2.php");
                }
                ?>
            </div>
        </form>
    </main>
</body>

</html>