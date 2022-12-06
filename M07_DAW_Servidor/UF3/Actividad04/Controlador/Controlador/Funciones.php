<?php
function validarUsuario($user)
{
    if (preg_match("/^[a-zA-Z0-9\-_ ]{3,25}$/", $user)) {
        echo "El nombre de usuario $user es correcto<br>";
        return true;
    } else {
        echo "El nombre de usuario $user no es válido<br>";
        return false;
    }
}

function validarPasswd($pass)
{
    if (strlen($pass) < 3 || strlen($pass) > 8) {
        echo "Contraseña no valida";
        return false;
    }

    $caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    for ($i = 0; $i < strlen($pass); $i++) {
        if (strpos($caracteres, substr($pass, $i, 1)) === false) {
            echo "Contraseña no valida";
        } else {
            echo "Contraseña valida";
            return true;
        }
    }
}

function validarTipoUser($tipoUser)
{
    if ($tipoUser == 1 || $tipoUser == 0) {
        echo "Tipo de usuario valido";
        return true;
    } else {
        echo "Tipo de usuario no valido";
        return false;
    }
}

function validarProyecto($proyecto)
{
    if (strlen($proyecto) < 5 || strlen($proyecto) > 35) {
        echo "Proyecto no valido";
        return false;
    }

    $caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()";

    for ($i = 0; $i < strlen($proyecto); $i++) {
        if (strpos($caracteres, substr($proyecto, $i, 1)) === false) {
            echo "Proyecto no valido";
        } else {
            echo "Proyecto valido";
            return true;
        }
    }
}

function validarListas($variableRecogida)
{
    if ($variableRecogida != null && count($variableRecogida) > 0) {
        return $variableRecogida;
    } else {
        return null;
    }
}


function convEstadoTexto($numEstado)
{
    switch ($numEstado) {
        case 1:
            return "Pendiente";
        case 2:
            return "En progreso";
        case 3:
            return "Finalizada";
        default:
            echo "Valor no correcto";
            break;
    }
}


function obtenerIdDeLista($listaBuscar, $valorBuscado)
{
    for ($i = 0; $i < count($listaBuscar); $i++) {
        if ($listaBuscar[$i]->getNombre() == $valorBuscado) {
            return $listaBuscar[$i]->getId();
        }
    }
}

function obtenerObjetoDeId($listaBuscar, $valorBuscado)
{
    for ($i = 0; $i < count($listaBuscar); $i++) {
        if ($listaBuscar[$i]->getId() == $valorBuscado) {
            return $listaBuscar[$i];
        }
    }
}


function convTipoUsuarioTexto($tipoUsuario)
{
    switch ($tipoUsuario) {
        case 0:
            return "Administrador";
        case 1:
            return "Usuario normal";
        default:
            echo "Valor no correcto";
            break;
    }
}

function salirSesion($boton)
{
    session_destroy();
    $host = $_SERVER['HTTP_HOST'];
    $ruta = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
    $url = "http://$host$ruta";
    header("Location: $url");
}

function volverInicio($boton)
{
    $host = $_SERVER['HTTP_HOST'];
    $ruta = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
    $archivo = "inicioAdmin.php";
    $url = "http://$host$ruta/$archivo";
    header("Location: $url");
}


function comprobarNormalUsu($usuario)
{
    if ($usuario->getTipoUsuario() != 1) {
        $_SESSION["error_url"] = "Esa no era tu url";
        $host = $_SERVER['HTTP_HOST'];
        $ruta = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
        $archivo = "index.php";
        $url = "http://$host$ruta/$archivo";
        header("Location: $url");
    }
}

function comprobarAdmin($usuario)
{
    if ($usuario->getTipoUsuario() != 0) {
        $_SESSION["error_url"] = "Esa no era tu url";
        $host = $_SERVER['HTTP_HOST'];
        $ruta = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
        $archivo = "index.php";
        $url = "http://$host$ruta/$archivo";
        header("Location: $url");
    }
}
