<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../estilos/calculadora.css">
    <title>Actividad 2</title>
</head>

<body>
    <main>
        <?php
        if (isset($_POST["operacion"])) {

            //Recogemos los valores introducidos por el usuario en el formulario
            $num1 = $_POST["num1"];

            $num2 = $_POST["num2"];

            $operacion = $_POST["operaciones"];

            $resultado = 0;

            $error = false;
            
            switch ($operacion) {
                case '+':
                    $resultado = $num1 + $num2;
                    break;
                case '-':
                    $resultado = $num1 - $num2;
                    break;
                case '*':
                    $resultado = $num1 * $num2;
                    break;
                case '/':
                    $resultado = $num1 / $num2;
                    break;
                default:
                    echo "No has escogido ninguna opcion valida";
                    $error = true;
                    break;
            }

            if ($error == false) {
                echo "El resultado de $num1 $operacion $num2 = $resultado";
            }
        } else {
            echo "Error";
        }

        ?>
    </main>
</body>

</html>