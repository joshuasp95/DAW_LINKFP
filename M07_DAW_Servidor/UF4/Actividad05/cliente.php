<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css?3.0">
    <title>Actividad 05 - Joshua Sainz</title>
</head>

<body>
    <?php
    //INCLUIMOS LOS ARCHIVOS NECESARIOS; LA LIBRERIA NUSOAP Y UNA PLANTILLA
    include("plantilla.html");
    require_once("lib/nusoap.php");
    $cont = 0;
    //CREAMOS EL CLIENTE CON LA URL DEL SERVIDOR QUE PROPORCIONARA LOS METODOS NECESARIOS
    $cliente = new soapclient('http://localhost/M07/UF4/servidor.php?wsdl');

    //MOSTRAMOS LAS FUNCIONES EXISTENTES
    echo "<h2>Funciones del servidor</h2>";
    $functions = $cliente->__getFunctions();

    foreach ($functions as $function) {
        echo "<p>$function</p>";
    }

    //MOSTRAMOS LA CATEGORIA DE LOS PRODUCTOS PARA QUE EL USUARIO SEPA QUE CATEGORIAS 
    //PUEDE USAR PARA AÑADIR PRODUCTOS
    echo "<h2>Categorias de los productos:</h2>";
    $categorias = $cliente->mostrarCategorias();
    ?>
    <table>
        <tr>
            <th>Id</th>
            <th>Nombre</th>
        </tr>

        <?php

        foreach ($categorias as $categoria) {
            echo "<tr><td>$categoria->id</td>
                <td>$categoria->nombre</td></tr>";
        }
        ?>
    </table>
    <?php
    // MOSTRAMOS LOS PRODUCTOS DE UNA CATEGORIA ESCOGIDA POR EL USUARIO
    ?>
    <h2>Elige una categoria para conocer los productos disponibles:</h2><br>
    <form action="" method="post">
        <select name="categ" id="categ">
            <?php
            foreach ($categorias as $categoria) {
                echo "<option value='$categoria->nombre'>$categoria->nombre</option>";
            }
            ?>
        </select>
        <input type="submit" value="Enviar" name="enviar">
    </form>
    <?php
    if (isset($_POST["enviar"])) {
        $productos = $cliente->productosCategoria($_POST["categ"]);
    ?>
        <table>
            <tr>
                <th>Producto</th>
                <th>Categoria</th>
            </tr>
            <?php
            foreach ($productos as $producto) {
                echo "<tr><td>$producto->Productos</td>
                <td>$producto->Categorias</td></tr>";
            }
            ?>
        </table>

    <?php
    }
    // AHORA DAMOS LA OPCION AL USUARIO DE AÑADIR UN NUEVO PRODUCTO EN BASE A LAS CATEGORIAS EXISTENTES
    ?>

    <h2>Añade un nuevo producto:</h2>
    <form action="" method="post" id="producto_new">
        <p>Nombre: </p>
        <input type="text" name="nom_prod">
        <p>Id de la categoria:</p>
        <select name="categ_prod" id="categ_prod">
            <?php
            $categorias2 = $cliente->mostrarCategorias();
            foreach ($categorias2 as $categoria) {
                echo "<option value='$categoria->id'>$categoria->id</option>";
            }
            ?>

        </select>
        <input type="submit" value="Enviar" name="enviar_prod">
    </form>
    <?php
    if (isset($_POST["enviar_prod"])) {
        $mensaje = $cliente->insertarProducto($_POST["nom_prod"], $_POST["categ_prod"]);
        echo "<p>Resultado de la operacion: $mensaje</p>";
    }
    ?>

</body>

</html>