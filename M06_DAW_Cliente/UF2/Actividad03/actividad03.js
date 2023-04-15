//Esta variable servira para meter un id a la imagen y darle un tamaño en el CSS
let idCss = '"imagen"';

/*Creamos la clase extra con 2 atributos y 1 metodo para obtener sus propiedades en el HTML
aunque no aparece por defecto se crea un constructor vacio para esta clase */
class Extra {
    constructor(precio, url) {
        this.precio = precio;
        this.url = url;
    }
    getHTML() {
        return "<span><img id=" + idCss + " src=" + this.url + ">" + this.precio + "</span>";
    }
}
/*Creamos la clase coche con 3 atributos y 2 metodos, uno para añadir un objeto de tipo extra
al array creado como atributo de la clase "extras" y otro metodo para devolver los valores del coche
de nombre y velocidad al HTML*/
class Coche {
    constructor(nombre, velocidad) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.extras = [];
    }
    addExtra(extra) {
        this.extras.push(extra);
    }
    getHTML() {
        return "<span><p> " + this.nombre + " " + this.velocidad + " </p></span>";
    }
}

/*Creamos 2 objetos de tipo extra para que aparezcan al cargar el HTML con window onload 
les damos unos atributos, aunque en Java se suele realizar con setters
 */
let extra = new Extra();
extra.precio = "10€";
extra.url = '"imgs/concha_azul.png"';

let extra2 = new Extra();
extra2.precio = "20€";
extra2.url = '"imgs/estrella.png"';

// console.log(extra.getHTML()); para comprobar que funciona el metodo getHTML()

//Realizamos lo mismo con los coches, creamos 2 objetos coche con unos valores definidos
let coche = new Coche();
coche.nombre = "Carricoche";
coche.velocidad = "10km/h";

let coche2 = new Coche();
coche2.nombre = "Batmowli";
coche2.velocidad = "20km/h";

console.log(coche.getHTML());

//Creamos un array para guardar todos los objetos tipo extra que se vayan creando
let extrasDisponibles = [];
extrasDisponibles.push(extra);
extrasDisponibles.push(extra2);

/*Tambien añadimos los extras creados como ejemplo al array de objetos tipo extra
que esta dentro de la clase coche*/
coche.addExtra(extra);
coche.addExtra(extra2);
coche2.addExtra(extra2);

// console.log("ARRAY EXTRAS --> "); Para comprobar que se han guardado los extras correctamente
// console.log(extrasDisponibles);

/**Ahora creamos un array que almacenara objetos de tipo coche con un array
 * asociativo que usara como clave el nombre del coche
 */
let cochesDisponibles = new Array();
cochesDisponibles[coche.nombre] = coche;
cochesDisponibles[coche2.nombre] = coche2;

// console.log(cochesDisponibles); Comprobamos que el array se ha creado correctamente

/**Ahora definimos las 2 funciones que se deben ejecutar al cargar todo el HTML
 * con window onload, las funciones van a mostrar los extras y coches disponibles 
 * tanto en el div correspondiente del HTML como en el select del ultimo div
 */
window.onload = mostrarExtras();
function mostrarExtras() {
    for (let i = 0; i < extrasDisponibles.length; i++) {
        document.getElementById("extras").innerHTML += extrasDisponibles[i].getHTML();
    }
    //este bucle creara los options value en el select del html y la posicion del extra seleccionado
    for (let i = 0; i < extrasDisponibles.length; i++) {
        document.getElementById("selectExtras").innerHTML += "<option value=" + i + ">" + i + "</option>";
    }
}

window.onload = mostrarCoches();
function mostrarCoches() {
    //necesitamos declarar una variable que actue como valor para los options del select
    let j = 0;
    for (let key in cochesDisponibles) {
        //debemos acceder a los elementos del array de objetos tipo coche con su clave
        document.getElementById("cochesElemento").innerHTML += cochesDisponibles[key].getHTML();
        /*mostramos sus extras con otro bucle anidado, funciona de tal manera que
        primero con el primer objeto de tipo coche accedido por su clave tendra una 
        serie de extras en su interior que se mostraran con el segundo bucle que 
        se repetira teniendo en cuenta la longitud de ese array
        */

        for (let i = 0; i < cochesDisponibles[key].extras.length; i++) {
            document.getElementById("cochesElemento").innerHTML += "<img id=" + idCss + " src=" + cochesDisponibles[key].extras[i].url + " />" + cochesDisponibles[key].extras[i].precio;
        }
    }
    for (let key in cochesDisponibles) {
        document.getElementById("selectCoches").innerHTML += "<option value=" + j + ">" + cochesDisponibles[key].nombre + "</option>";
        j++;
    }
}

//Creamos una funcion para añadir objetos de tipo extra
function addExtras() {
    let extraNuevo = new Extra();
    //obtenemos el valor del input html y lo convertimos a un numero entero
    let precio = parseInt(document.getElementById("precio").value);
    // console.log(precio); comprobamos que es correcto
    //le asignamos ese valor como atributo
    extraNuevo.precio = validarPrecio(precio);
    //hacemos lo mismo con la url
    let url = document.getElementById("listaExtras").value;
    // console.log(url); comprobamos que es correcto
    extraNuevo.url = validarURL(url);
    //lo añadimos al array de extras disponibles
    extrasDisponibles.push(extraNuevo);
    //para que no se añadan a los elementos anteriores vaciamos el div del HTML de contenido
    document.getElementById("extras").innerHTML = "<h2>EXTRAS DISPONIBLES</h2>";
    //y del select tambien para que no se añadan al select
    document.getElementById("selectExtras").innerHTML = "";
    //volvemos a llamar a la funcion mostrar extras para que se actulice con los nuevos objetos
    mostrarExtras();
}

//Llamamos a esta funcion con el onclick del button del HTML
function delExtras() {
    //Obtenemos la posicion del input del HTML
    let posicion = document.getElementById("posicion").value;
    //con el metodo splice, eliminamos un valor de la posicion del array 
    extrasDisponibles.splice(posicion, 1);
    //actualizamos la caja del html llamando otra vez a la funcion mostrarExtras
    document.getElementById("extras").innerHTML = "<h2>EXTRAS DISPONIBLES</h2>";
    document.getElementById("selectExtras").innerHTML = "";
    mostrarExtras();
}

//Funcion que es similar a la de crear extras, en este caso no se han hecho validaciones
function newCoche() {
    let cocheNuevo = new Coche();
    let nombre = document.getElementById("nombre").value;
    cocheNuevo.nombre = nombre;
    let velocidad = parseInt(document.getElementById("velocidad").value);
    cocheNuevo.velocidad = velocidad + "km/h";
    cocheNuevo.addExtra(extra2);//les añadimos un extra por defecto para comprobar que funciona
    //añadimos el coche nuevo al array de coches disponibles con su clave correspondiente
    cochesDisponibles[cocheNuevo.nombre] = cocheNuevo;
    //actualizamos el HTML, se puede hacer una funcion con este codigo actualizarHTML()
    document.getElementById("cochesElemento").innerHTML = "";
    document.getElementById("selectCoches").innerHTML = "";
    mostrarCoches();
}
//Es una funcion para simular una validacion del precio asignado en el input
function validarPrecio(valor) {
    if (valor < 100 && valor > 0) {
        return valor + "€";
    } else {
        return "ERROR";
    }
}
/*Funcion que asignara la url correcta al valor introducido
 por el usuario segun el indice del select que tuviera la opcion*/
function validarURL(valor) {
    switch (valor) {
        case "1":
            return '"imgs/concha_azul.png"';
            break;
        case "2":
            return '"imgs/estrella.png"';
            break;
        default:
            break;
    }
}
//funcion que servira para asignar extras cuando se clique el boton del HTML
function asignarExtras() {
    //se recogen los valores de posicion del extra y coche elegidos en el select del HTML
    let numeroExtra = document.getElementById("selectExtras").value;
    let numeroCoche = document.getElementById("selectCoches").value;

    //se obtienen los objetos tipo extra y tipo coche correspondientes
    let cocheExtra = validarCoche(numeroCoche);
    let extraCoche = validarExtra(numeroExtra);
    //se añade el objeto tipo coche con su correspondiente extra elegido por el usuario
    cocheExtra.addExtra(extraCoche);
    //se actualiza el html
    document.getElementById("cochesElemento").innerHTML = "";
    document.getElementById("selectCoches").innerHTML = "";
    mostrarCoches();
}
//Funcion que devolvera el objeto tipo extra elegido por el usuario 
function validarExtra(valor) {
    //con un bucle se hace coincidir el valor de posicion del extra elegido por el usuario
    //y el objeto de tipo extra correspondiente
    for (let i = 0; i < extrasDisponibles.length; i++) {
        if (i == valor) {
            return extrasDisponibles[i];
        }
    }
}
function validarCoche(valor) {
    /*De manera similiar a la funcion anterior se recorren los objetos de tipo coche
    cuando el valor introducido por el usuario sea el mismo que el numero de iteraciones
    que lleva el bucle, se obtiene el objeto de tipo coche con su correspondiente clave*/
    let i = 0;
    for (let key in cochesDisponibles) {
        if (i == valor) {
            return cochesDisponibles[key];
        }
        i++;
    }
}