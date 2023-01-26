<?php

session_start();

//Recogemos las variables

//El valor del input
$num = $_GET["val"];

//El numero del elemento del HTML que se esta validando
$key = $_GET["key"];

//Controlamos si el numero esta o no dentro del rango y mandamos un mensaje 
$msj = ($num > 0 && $num < 10) ? "Correcto" : "Error: [1-9]*";

//Devolvemos un objeto de tipo JSON
echo (json_encode(devolverArray($msj)));

//Creamos una funcion que devolvera un array con el mensaje que servira para convertirlo en un JSON
function devolverArray($msj)
{
    $data = array(
        "mensaje" => $msj
    );

    return $data;
}

//Creamos las variables de Sesion para cada input si el valor del input esta dentro del rango

//Le damos el valor del input a la variable de sesion (una por cada input)
if ($msj == "Correcto") {
    switch ($key) {
        case 1:
            $_SESSION['input1'] = $num;
            break;
        case 2:
            $_SESSION['input2'] = $num;
            break;
        case 3:
            $_SESSION['input3'] = $num;
            break;
        case 4:
            $_SESSION['input4'] = $num;
            break;
        default:
            break;
    }
}
