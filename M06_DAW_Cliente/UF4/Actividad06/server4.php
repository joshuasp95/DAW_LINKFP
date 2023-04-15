<?php

session_start();

$num = $_GET["val"];

$key = $_GET["key"];

if (validarSesiones()) {
    switch ($key) {
        case 1:
            $msj = ($num == $_SESSION['input1']) ? "Coincide" : "No Coincide";
            break;
        case 2:
            $msj = ($num == $_SESSION['input2']) ? "Coincide" : "No Coincide";
            break;
        case 3:
            $msj = ($num == $_SESSION['input3']) ? "Coincide" : "No Coincide";
            break;
        case 4:
            $msj = ($num == $_SESSION['input4']) ? "Coincide" : "No Coincide";
            break;
        default:
            break;
    }
    echo (json_encode(devolverArray($msj)));
} else {
    echo (json_encode(devolverArray("No tienes ningun numero guardado")));
}

function devolverArray($msj)
{
    $data = array(
        "mensaje" => $msj
    );

    return $data;
}

function validarSesiones()
{
    if (isset($_SESSION['input1']) && isset($_SESSION['input2']) && isset($_SESSION['input3']) && isset($_SESSION['input4'])) {
        return true;
    }
}
