<?php

header('Content-type:application/json;charset=utf-8');

session_start();

//RECOGEMOS LOS PARAMETROS DEL CLIENTE PARA FILTRAR EN LA BBDD DONDE SE CUMPLAN ESAS CONDICIONES
$localidad = $_GET['localidad'];

$tipoLocal = $_GET['tipoLocal'];

echo (devolverMarcador($localidad, $tipoLocal));

function devolverMarcador($localidad, $tipoLocal)
{
    //Nos conectamos a la BBDD
    $con = mysqli_connect('localhost', 'root', '', 'actividad_06_maps') or die('Error al conectar a la BBDD');
    //Creamos una variable para guardar los valores que devolveremos al cliente
    $arrayValores = array();

    try {
        //realizamos la consulta
        $query = "select nombre, coordenadas, tipo from marcadores where poblacion = '" . $localidad . "' and tipo = '" . $tipoLocal . "'";

        //Si la consulta se ha realizado mostramos la siguiente tabla
        if ($result = mysqli_query($con, $query)) {
            // var_dump($result);
            /* Crear el array asociativo mientras haya registros en el resultado de la consulta */
            while ($row = mysqli_fetch_array($result)) {
                // echo "<p>Poblacion --> " . $row['poblacion'] . "</p>";
                // echo "<p>Tipo --> " . $row['tipo'] . "</p>";
                $arrayValores[] = array(
                    "nombre" => $row['nombre'],
                    "coordenadas" => $row['coordenadas'],
                    "tipo" => $row['tipo']
                );
                // $arrayValores['poblacion'] = $row['poblacion'];
                // $arrayValores['tipo'] = $row['tipo'];

            }
            //guardamos los valores en una variable de sesion
            $_SESSION['valores_mapa'] = $arrayValores;
            /* liberar el conjunto de resultados */
            mysqli_free_result($result);
        }
    } catch (Exception $e) {
        echo "Error en la consulta realizada: " + $e->getMessage();
    }
    /* cerrar la conexión */
    mysqli_close($con);

    //Comprobamos la variable de sesion
    $json = json_encode($_SESSION['valores_mapa']);

    //Devolvemos los valores
    return $json;
}
