<?php

// include_once("server2.php"); Parece que da error cuando le intento incluir otro fichero php

session_start();

//Se controla si tiene valor la variable de sesion del array de numeros guardado en el server 1 

if (isset($_SESSION['numerosGuard'])) {
    //Guardamos el valor de la sesion en otra variable
    $arrSave = $_SESSION['numerosGuard'];
    //Recogemos el valor del array que se esta validando
    $arrCheck = $_POST["arr"];
    //Lo convertimos a un formato adecuado
    $arrCheck = json_decode($arrCheck);
    //Lo comparamos y establecemos un mensaje dependiendo de si cumple la condicion o no
    $msj = ($arrSave == $arrCheck) ? "Correcto: Los valores coinciden" : "Error: Los valores no coinciden";

    echo (json_encode(devolverArray($msj)));
} else {
    echo (json_encode(devolverArray("No se ha guardado ningun numero")));
}

function devolverArray($msj)
{
    $data = array(
        "mensaje" => $msj
    );

    return $data;
}
