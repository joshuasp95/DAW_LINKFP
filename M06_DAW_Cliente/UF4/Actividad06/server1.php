<?php

// phpinfo();

session_start();

//Recogemos el array
$arr = $_GET["arr"];

//Establecemos una variable de control
$control = 0;

//Guardamos la variable recogida en un formato de array adecuado para iterar sobre el
$arr = json_decode($arr);

for ($i = 0; $i < count($arr); $i++) {
    if ($arr[$i] < 1 || $arr[$i] > 9) {
        //Si pasa por aqui es que hay algun numero fuera de rango
        $control++;
        $msj = "<p>Error: Hay numeros fuera del rango [1-9]</p>";
    }
}

if ($control <= 0) {
    //Si pasa por aqui es que los numeros son correcto
    $msj = "<p>Se han guardado los numeros correctamente</p>";

    //Se guarda el array en una sesion para los siguientes apartados poder compararla
    $_SESSION['numerosGuard'] = $arr;

    //Creamos un array para poder devolverlo al cliente
    $data = array(
        "mensaje" => $msj,
        "num1" => $arr[0],
        "num2" => $arr[1],
        "num3" => $arr[2],
        "num4" => $arr[3]
    );

    //lo convertimos a formato JSON
    echo (json_encode($data));
} else {
    $data = array(
        "mensaje" => $msj
    );
    echo (json_encode($data));
}
