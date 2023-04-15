<?php
// INCLUIMOS LA LIBRERIA NUSOAP PARA CREAR EL SERVICIO WEB Y AÑADIMOS TAMBIEN LOS DATOS DE CONEXION A LA BBDD

require_once "lib/nusoap.php";
require_once("datos_conexion.php");

//CONFIGURAMOS EL SERVIDOR CON EL NOMBRE, CODIFICACION DE CARACTERES...
$namespace = "http://localhost/M07/UF4/servidor.php";
$server = new soap_server();
$server->configureWSDL("ServicioWebJoshua", $namespace);
$server->schemTargetNamespace = $namespace;
$server->soap_defencoding = 'UTF-8';

//IMPLEMENTAMOS LOS METODOS
//EN ESTE CASO EL PRIMERO SERA PARA INSERTAR PRODUCTOS EN LA BBDD PASANDO
//COMO PARAMETROS EL NOMBRE DEL NUEVO PRODUCTO Y EL ID DE LA CATEGORIA A LA QUE PERTENECE
//DEVOLVERA UN MENSAJE CONFIRMANDO LA OPERACION REALIZADA
//EL ID DEL PRODUCTO SE AUTOINCREMENTA COMO SE HA ESTABLECIDO EN LA BBDD
//POR LO QUE NUNCA SERA EL MISMO
function insertarProducto($nom_prod, $id_cat)
{
    $msj = "";
    $conn = mysqli_connect($GLOBALS['host'], $GLOBALS['user'], $GLOBALS['pass'], $GLOBALS['db_name'])
        or die("Error al conectar a la BBDD");

    $query = "insert into producto(nombre, fk_id_categoria) values ('" . $nom_prod . "', " . $id_cat . ");";
    if ($conn->query($query) === true) {
        $msj = "Se ha insertado correctamente en la BBDD";
    } else {
        $msj = "Error no se ha podido insertar en la BBDD";
    }


    mysqli_close($conn);
    return $msj;
}

//OTRO METODO QUE MOSTRARA LAS CATEGORIAS EXISTENTES
//EN ESTE CASO DEVUELVE UN TIPO DE DATO COMPLEJO COMO ES EL ARRAY
function mostrarCategorias()
{
    $arrCategorias = array();
    $conn = mysqli_connect($GLOBALS['host'], $GLOBALS['user'], $GLOBALS['pass'], $GLOBALS['db_name'])
        or die("Error al conectar a la BBDD");

    try {
        $query = "select * from categoria;";
        $categorias = mysqli_query($conn, $query);
        while ($categoria = mysqli_fetch_assoc($categorias)) {
            $arrCategorias[] = $categoria;
        }
    } catch (Exception $e) {

        return "Error";
    }
    mysqli_close($conn);
    return $arrCategorias;
}

//ESECIFICAMOS QUE TIPO DE DATO TIENE QUE DEVOLVER 
$server->wsdl->addComplexType(
    'Categoria',
    'complexType',
    'struct',
    'sequence',
    '',
    array(
        'id' => array('name' => 'id', 'type' => 'xsd:int'),
        'nombre' => array('name' => 'nombre', 'type' => 'xsd:string')
    )
);

//ESPECIFICAMOS EL ARRAY CON EL TIPO DE DATOS QUE CONTENDRA
$server->wsdl->addComplexType(
    'ArrayCategorias',
    'complexType',
    'array',
    'sequence',
    'SOAP-ENC:Array',
    array(),
    array(
        array(
            'ref' => 'SOAP-ENC:arrayType',
            'wsdl:arrayType' => 'tns:Categoria[]'
        )
    ),
    'tns:Categoria'
);

//REGISTRAMOS LOS METODOS PARA QUE PUEDAN SER UTILIZADOS POR EL CLIENTE

$server->register(
    'mostrarCategorias',
    array(),
    array('return' => 'tns:ArrayCategorias'),
    $namespace,
    false,
    'rpc',
    'encoded',
    'Funcion que devuelve un array de las categorias existentes en la base de datos.'
);

//FUNCION PARA MOSTRAR LOS PRODUCTOS DE UNA CATEGORIA DETERMINADA
function productosCategoria($categoria)
{
    $conn = mysqli_connect($GLOBALS['host'], $GLOBALS['user'], $GLOBALS['pass'], $GLOBALS['db_name'])
        or die("Error al conectar a la BBDD");

    $arrProductos = array();

    try {
        $query = "select producto.nombre AS Productos, categoria.nombre AS Categorias from producto
        inner JOIN categoria ON categoria.id = producto.fk_id_categoria
        where categoria.nombre = '" . $categoria . "';";
        $productos = mysqli_query($conn, $query);
        while ($producto = mysqli_fetch_assoc($productos)) {
            $arrProductos[] = $producto;
        }
    } catch (Exception $e) {
        return "Error";
    }

    mysqli_close($conn);
    return $arrProductos;
}


$server->wsdl->addComplexType(
    'Producto',
    'complexType',
    'struct',
    'sequence',
    '',
    array(
        'Productos' => array('name' => 'Productos', 'type' => 'xsd:string'),
        'Categorias' => array('name' => 'Categorias', 'type' => 'xsd:string')
    )
);


$server->wsdl->addComplexType(
    'ArrayProductos',
    'complexType',
    'array',
    'sequence',
    'SOAP-ENC:Array',
    array(),
    array(
        array(
            'ref' => 'SOAP-ENC:arrayType',
            'wsdl:arrayType' => 'tns:Producto[]'
        )
    ),
    'tns:Producto'
);


$server->register(
    'productosCategoria',
    array('categoria' => 'xsd:string'),
    array('return' => 'tns:ArrayProductos'),
    $namespace,
    false,
    'rpc',
    'encoded',
    'Funcion que devuelve un array de los productos que existen de una determinada categoria
     en la base de datos.'
);

$server->register(
    'insertarProducto',
    array('nom_prod' => 'xsd:string', 'id_cat' => 'xsd:int'),
    array('return' => 'xsd:string'),
    $namespace,
    false,
    'rpc',
    'encoded',
    'Funcion que devuelve un mensaje validando la insercion de un nuevo registro en la base de datos.'
);
//INICIAMOS EL SERVICIO Y LO DEJAMOS A LA ESPERA DE LAS PETICIONES DEL CLIENTE
$server->service(file_get_contents("php://input"));
