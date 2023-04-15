<?php
// Incluimos el codigo que contiene la clase padre y las hijas
//asi como las funciones

include("personajes.php");

//iniciamos la sesion para poder utilizar distintas variables de sesion a lo 
// largo de todo el proceso, tiene que ir al principio, si no sale un warning 
//en la consola de depuracion
session_start();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actividad 3 - Joshua Sainz</title>
    <link rel="stylesheet" href="../style.css?3.0">
    <link rel="stylesheet" href="../css/ej3.css?11.0">
</head>

<body>
    <?php
    include '../plantilla.html';
    ?>
    <main id="cuerpo">
        <form method="POST">
            <div id="select_personajes">
                <h3>Selecciona un personaje</h3>

                <!-- Aqui tenemos un select que servira para registrar el unico personaje
                que puede tener el usuario -->

                <select name="personaje_usuario" id="select_personajes">
                    <option value="Guerrero">Guerrero</option>
                    <option value="Mago">Mago</option>
                    <option value="Explorador">Explorador</option>
                </select>
            </div>

            <!-- Aqui tendremos todos los botones que en el caso de ser pulsados por el
            usuario ejecutaran diversas acciones; mostrar npcs, atacar... -->

            <div id="botones">
                <input type="submit" value="Comenzar Juego" name="jugar">
                <input type="submit" value="Crea tu NPC" name="crear">
                <input type="submit" value="Mostrar NPC" name="mostrar">
                <input type="submit" value="Atacar" name="atacar">
                <input type="submit" value="Reset" name="reset">
            </div>

            <!-- Aqui definimos los ataques que son tambien unicos y excluyentes en
            funcion del personaje elegido por el usuario -->

            <select id="funciones" name="accion">
                <option value="0">Elige un ataque</option>
                <option value="Atacar enemigos">Guerrero: Atacar</option>
                <option value="Lanzar hechizos">Mago: Hechizar</option>
                <option value="Descubrir objetivos">Explorador: Localizar</option>
            </select>

        </form>

        <div id="juego">
            <div id="caracteristicas">

                <!-- Definimos las caracteristicas de los personajes disponibles para el usuario -->

                <?php


                //creamos un array con los ejemplos de personajes posibles para escoger
                $ejemplos = array();

                //creamos 3 objetos y los metemos en cada posicion del array
                $ejemplos[0] = new Guerrero("Lei Shen", "Pandarian", 70, 5, 10, 100);
                $ejemplos[1] = new Mago("Medivh", "Humano", 94, 3, 6, 100);
                $ejemplos[2] = new Explorador("Thrall", "Orco", 89, 4, 1, 100);

                //creamos un array con los string que definen los ataques
                $ataques = array(
                    "Enemigos abatidos", "Daño sufrido", "Secretos descubiertos",
                    "Hechizos lanzados", "Objetivos descubiertos", "Veces sin ser descubierto"
                );

                //mostramos las caracteristicas por pantalla en el Navegador

                for ($i = 0, $j = 0; $i < count($ejemplos); $i++, $j++) {

                    echo '<img id="imagen" src=' . $ejemplos[$i]->getURL() . '>';
                    echo  "<p>" . $ejemplos[$i] . "</p>";
                    echo "<p>Ataque: " . $ataques[$j] . " " . $ejemplos[$i]->getAtaque1() .
                        " / " . $ataques[($j + 1)] . " " . $ejemplos[$i]->getAtaque2() . "</p>";
                    $j++;
                }

                //Guardamos ese array de los personajes de ejemplo en una nueva variable de sesion
                $_SESSION["personajes_ejemplo"] = $ejemplos;

                ?>
            </div>

            <div id="acciones_resultados">

                <?php

                //Aqui tenemos el codigo principal

                //Solo entraremos si esta creada la variable de sesion que sera el contenedor
                //del personaje creado por el usuario, si no esta creada ira al else
                if (isset($_SESSION["personajes"])) {

                    //si pulsamos mostrar, se mostraran los personajes creados
                    //Para ello es necesario haber creado un personaje antes
                    if (isset($_POST["mostrar"])) {
                        mostrarPersonajes();
                    }

                    //aqui crearemos el personaje pasando el numero de posicion 
                    //del array donde se debe crear el personaje
                    if (isset($_POST["crear"])) {
                        nuevoPersonaje($_SESSION["numPersonaje"]);

                        // $_SESSION["numPersonaje"]++; habilitar para crear mas personajes (era otro proyecto)

                    }
                    //cuando se seleccione una accion y ademas se pulse el boton de atacar 
                    //se ejecutara lo siguiente
                    if (isset($_POST["accion"]) && isset($_POST["atacar"])) {

                        //guardamos la accion seleccionada en una variable de sesion
                        $_SESSION["accion"] = $_POST["accion"];

                        //ahora atacara el personaje elegido por el usuario a los demas
                        //y mostraremos la vida de los personajes restante y su puntuacion
                        ataquePersonaje($_SESSION["personajes_ejemplo"]);
                        mostrarVida($_SESSION["personajes_ejemplo"]);
                        mostrarPuntuacion($_SESSION["personajes_ejemplo"]);

                        echo '<img id="imagen_batalla" src="../imgs/Batalla.png">';
                    }

                    //si alguna de las vidas de los personajes almacenadas en variables de sesion
                    //llega a 0, destruimos la sesion para empezar de nuevo
                    if ($_SESSION["vida_personaje_0"] == 0 || $_SESSION["vida_personaje_1"] == 0 || $_SESSION["vida_personaje_2"] == 0) {

                        echo "<p> Un personaje ha muerto</p>";
                        echo "<p> Crea un nuevo personaje</p>";

                        session_destroy();
                    }
                } else {
                    //como todavia no esta creada la variable de sesion inicial de personaje
                    //elegido por el usuario, el codigo entrara por aqui

                    //Para comenzar a jugar, se debera pulsar el boton jugar
                    //se muestra una frase de confirmacion

                    if (isset($_POST["jugar"])) {

                        //se crean las variables de sesion con el contenedor que contendra el 
                        //personaje elegido por el usuario, en un principio iban a ser varios 
                        //personajes creados por el usuario pero el codigo se iba a complicar
                        //en exceso, por eso lo he acotado solo a 1 personaje
                        $_SESSION["numPersonaje"] = 0;
                        $_SESSION["personajes"] = [];

                        echo "<p>Empieza el juego</p>";
                        echo '<img id="imagen_inicio" src="../imgs/warcraft.png">';

                        //se registran las variables de sesion con las vidas de los personajes
                        //ya que iran disminuyendo con los ataques
                        $_SESSION["vida_personaje_0"] = $_SESSION["personajes_ejemplo"][0]->getVida();
                        $_SESSION["vida_personaje_1"] = $_SESSION["personajes_ejemplo"][1]->getVida();
                        $_SESSION["vida_personaje_2"] = $_SESSION["personajes_ejemplo"][2]->getVida();

                        //se registran las variables de sesion con los puntos de los personajes
                        //ya que iran aumentando con los ataques
                        $_SESSION["puntos_personaje_0"] = $_SESSION["personajes_ejemplo"][0]->puntosGanados();
                        $_SESSION["puntos_personaje_1"] = $_SESSION["personajes_ejemplo"][1]->puntosGanados();
                        $_SESSION["puntos_personaje_2"] = $_SESSION["personajes_ejemplo"][2]->puntosGanados();
                    }
                }
                if (isset($_POST["reset"])) {
                    //si se pulsa reset se destruira la sesion y se volvera a empezar de nuevo
                    session_destroy();
                }



                //FUNCIONES

                //Mostramos los personajes
                function mostrarPersonajes()
                {
                    //como solo hay 1 personaje no tiene sentido hacer un for, pero lo he dejado
                    //asi ya que funciona igual, mostramos el nombre y la imagen del
                    //personaje creado por el usuario
                    for ($i = 0; $i < count($_SESSION["personajes"]); $i++) {
                        echo "<p>" . $_SESSION["personajes"][$i]->getNombre() . "</p>";
                        echo '<img id="imagen" src=' . $_SESSION["personajes"][$i]->getURL() . '>';
                    }
                }

                //creamos un personaje pasando como parametro el numero que ocupara en el
                //array de personajes, aunque solo habra 1 personaje
                function nuevoPersonaje($numeroPersonaje)
                {
                    //hacemos un switch case con el valor del select elegido por el usuario
                    switch ($_POST["personaje_usuario"]) {
                        case "Guerrero":
                            //Creamos el personaje y lo guardamos en la posicion del array definida
                            $_SESSION["personajes"][$numeroPersonaje] = new Guerrero("Lei Shen", "Pandarian", 70, 1, 1, 100);
                            break;
                        case "Mago":
                            $_SESSION["personajes"][$numeroPersonaje] = new Mago("Medivh", "Humano", 94, 1, 1, 100);
                            break;
                        case "Explorador":
                            $_SESSION["personajes"][$numeroPersonaje] =  new Explorador("Thrall", "Orco", 89, 1, 1, 100);
                            break;
                        default:
                            echo "No has seleccionado ninguna opcion valida";
                            break;
                    }

                    //mostramos el personaje creado
                    echo "<p>" . $_SESSION["personajes"][$numeroPersonaje] . "</p>";
                    echo '<img id="imagen" src=' . $_SESSION["personajes"][$numeroPersonaje]->getURL() . '>';
                }

                //funcion para atacar al personaje
                function ataquePersonaje($personaje)
                {
                    //si el valor de la accion elegida por el usuario coincide con la especie  correspondiente
                    //del personaje elegido por el usuario
                    if ($_SESSION["accion"] == "Atacar enemigos" && $_SESSION["personajes"][0]->getEspecie() == "Pandarian") {
                        //disminuimos el valor de la vida de los personajes a los que ataca
                        $_SESSION["vida_personaje_1"] -= 20;
                        $_SESSION["vida_personaje_2"] -= 20;
                        //aumentamon los puntos del personaje elegido por el usuario
                        $personaje[0]->setEnemigosAbatidos(10);
                        $personaje[0]->setDañoSufrido(5);
                        $_SESSION["puntos_personaje_0"] += $_SESSION["personajes_ejemplo"][0]->puntosGanados();
                    } elseif ($_SESSION["accion"] == "Lanzar hechizos" && $_SESSION["personajes"][0]->getEspecie() == "Humano") {

                        $_SESSION["vida_personaje_0"] -= 20;
                        $_SESSION["vida_personaje_2"] -= 20;

                        $personaje[1]->setSecretosDescubiertos(15);
                        $personaje[1]->setHechizosLanzados(5);

                        $_SESSION["puntos_personaje_1"] += $_SESSION["personajes_ejemplo"][1]->puntosGanados();
                    } elseif ($_SESSION["accion"] == "Descubrir objetivos" && $_SESSION["personajes"][0]->getEspecie() == "Orco") {

                        $_SESSION["vida_personaje_0"] -= 20;
                        $_SESSION["vida_personaje_1"] -= 20;

                        $personaje[2]->setObjetivosDescubiertos(7);
                        $personaje[2]->setVecesSinSerDescubierto(4);

                        $_SESSION["puntos_personaje_2"] += $_SESSION["personajes_ejemplo"][2]->puntosGanados();
                    } else {
                        echo "";
                    }
                }

                function mostrarVida($personaje)
                {
                    //se muestra la vida de los personajes
                    //para mostrarlos en un array se pasa como parametro el array de personajes 
                    //de ejemplo y se actualiza su valor con las variables de sesion que se 
                    //han cambiado de valor durante los ataques
                    echo "<p>Vidas personajes</p><hr><br>";
                    $personaje[0]->setVida($_SESSION["vida_personaje_0"]);
                    $personaje[1]->setVida($_SESSION["vida_personaje_1"]);
                    $personaje[2]->setVida($_SESSION["vida_personaje_2"]);

                    for ($i = 0; $i < count($personaje); $i++) {
                        echo "<p>" . $personaje[$i]->getNombre() . " Vida[" . $personaje[$i]->getVida() . "]</p>";
                    }
                    echo "<br>";
                }

                function mostrarPuntuacion($personaje)
                {

                    //hacemos lo mismo que en la funcion anterior pero con los puntos de los personajes
                    echo "<p>Puntuacion total</p><hr><br>";
                    $personaje[0]->setPuntosGanados($_SESSION["puntos_personaje_0"]);
                    $personaje[1]->setPuntosGanados($_SESSION["puntos_personaje_1"]);
                    $personaje[2]->setPuntosGanados($_SESSION["puntos_personaje_2"]);

                    for ($i = 0; $i < count($personaje); $i++) {
                        echo "<p>" . $personaje[$i]->getNombre() . " Puntos[" . $personaje[$i]->getPuntosGanados() . "]</p>";
                    }
                    echo "<br>";
                }

                ?>
            </div>
        </div>

    </main>

</body>

</html>


<?php


?>