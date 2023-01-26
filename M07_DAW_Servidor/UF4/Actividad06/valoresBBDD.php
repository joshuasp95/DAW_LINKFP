<?php
header('Content-type:application/json;charset=utf-8');

session_start();

echo (mostrarValoresSelect());

function mostrarValoresSelect()
{
    //Nos conectamos a la BBDD
    $con = mysqli_connect('localhost', 'root', '', 'actividad_06_maps') or die('Error al conectar a la BBDD');
    //Creamos una variable para guardar los valores que devolveremos al cliente

    $arrayValores = array();

    try {
        //realizamos la consulta
        $query = 'select poblacion, tipo from marcadores';

        //Si la consulta se ha realizado mostramos la siguiente tabla
        if ($result = mysqli_query($con, $query)) {
            // var_dump($result);
            /* Crear el array asociativo mientras haya registros en el resultado de la consulta */
            while ($row = mysqli_fetch_assoc($result)) {
                // echo "<p>Poblacion --> " . $row['poblacion'] . "</p>";
                // echo "<p>Tipo --> " . $row['tipo'] . "</p>";
                $arrayValores[] = array(
                    "poblacion" => $row['poblacion'],
                    "tipo" => $row['tipo']
                );
                // $arrayValores['poblacion'] = $row['poblacion'];
                // $arrayValores['tipo'] = $row['tipo'];

            }
            //guardamos los valores en una variable de sesion
            $_SESSION['valores_bbdd'] = $arrayValores;
            /* liberar el conjunto de resultados */
            mysqli_free_result($result);
        }
    } catch (Exception $e) {
        echo "Error en la consulta realizada: " + $e->getMessage();
    }
    /* cerrar la conexión */
    mysqli_close($con);

    //Comprobamos la variable de sesion
    $json = json_encode($_SESSION['valores_bbdd']);

    //Devolvemos los valores
    return $json;
}


//ESTA FUNCION NO SE EJECUTA

function mostrarValoresBBDD()
{
    //Nos conectamos a la BBDD
    $con = mysqli_connect('localhost', 'root', '', 'actividad_06_maps') or die('Error al conectar a la BBDD');
    //Creamos una variable para guardar los valores que devolveremos al cliente
    $arrayValores = array();

    try {
        //realizamos la consulta
        $query = 'select nombre, coordenadas, poblacion, tipo from marcadores';

        //Si la consulta se ha realizado mostramos la siguiente tabla
        if ($result = mysqli_query($con, $query)) {
            //CODIGO PARA CONTROLAR EL RESULTADO EN EL SERVIDOR
            // var_dump($result);
            // echo "<style> table, td { border: 1px solid black; padding: 10px; border-collapse: collapse;}</style>";
            // echo "<table><tr><th>Nombre</th><th>Coordenadas</th><th>Poblacion</th><th>Tipo</th></tr>";

            /* Crear el array asociativo mientras haya registros en el resultado de la consulta */
            while ($row = mysqli_fetch_assoc($result)) {
                // echo "</tr><td>" . $row['nombre'] . "</td>";
                // echo "<td>" . $row['coordenadas'] . "</td>";
                // echo "<td>" . $row['poblacion'] . "</td>";
                // echo "<td>" . $row['tipo'] . "</td></tr>";
                $arrayValores['nombre'] = $row['nombre'];
                $arrayValores['coordenadas'] = $row['coordenadas'];
                $arrayValores['poblacion'] = $row['poblacion'];
                $arrayValores['tipo'] = $row['tipo'];
            }
            // echo "</table>";
            //guardamos los valores en una variable de sesion
            $_SESSION['valores_bbdd'] = $arrayValores;
            /* liberar el conjunto de resultados */
            mysqli_free_result($result);
        }
    } catch (Exception $e) {
        echo "Error en la consulta realizada: " + $e->getMessage();
    }
    /* cerrar la conexión */
    mysqli_close($con);

    //Comprobamos la variable de sesion
    // var_dump($_SESSION['valores_bbdd']);
    //Devolvemos los valores
    return $_SESSION['valores_bbdd'];
}
