/*Cuando se carguen todos los elementos del navegador (HTML, imagenes, CSS, scripts,...)
Se ejecutara la funcion mostrar*/
window.onload = mostrar();

function mostrar() {

    //La estructura del HTML se añadira dentro del div con el id "listaPropiedades" con innerHTML
    //El objeto de JS nativo Number tiene un valor maximo que se obtiene con el atributo MAX_VALUE

    document.getElementById("listaPropiedades").innerHTML = "<ul><li><strong>Maximo valor de una variable tipo Number:</strong> " + Number.MAX_VALUE + "</li></ul>";

    //Añadirmos el += al innerHTML para que no borre el contenido anterior sino que lo sume

    //El objeto Screen permite acceder a información de la pantalla del equipo que visualiza la web

    document.getElementById("listaPropiedades").innerHTML += "<ul><li><strong>Altura total de la pantalla:</strong> " + screen.height + "</li></ul>";

    document.getElementById("listaPropiedades").innerHTML += "<ul><li><strong>Anchura total de la pantalla:</strong> " + screen.width + "</li></ul>";

    //El objeto Window incluye el DOM y la pestaña donde se muestra su contenido
    document.getElementById("listaPropiedades").innerHTML += "<ul><li><strong>Altura interna de la ventana:</strong> " + window.innerHeight + "</li></ul>";

    document.getElementById("listaPropiedades").innerHTML += "<ul><li><strong>Anchura interna de la ventana:</strong> " + window.innerWidth + "</li></ul>";

    //El objeto document se refiere al DOM y todos sus elementos
    document.getElementById("listaPropiedades").innerHTML += "<ul><li><strong>URL de la página web:</strong> " + document.URL + "</li></ul>";

    document.getElementById("listaPropiedades").innerHTML += "<ul><li><strong>Titulo de la página web:</strong> " + document.title + "</li></ul>";

    document.getElementById("listaPropiedades").innerHTML += "<ul><li><strong>Numero aleatorio (entre 0-200):</strong> " + Math.floor(Math.random() * (200 - 0) + 0) + "</li></ul>";

    //Date es una clase JS que sirve para obtener o crear información de valores relacionados con fechas

    //Creamos un objeto de tipo Date y accedemos a sus méetodos

    let fechaActual = new Date();

    // Otro metodo de obtener la fecha document.getElementById("listaPropiedades").innerHTML += "<ul><li>Fecha actual: " + fechaActual.toLocaleDateString('es-ES') + "</li></ul>";

    document.getElementById("listaPropiedades").innerHTML += "<ul><li><strong>Fecha actual:</strong> " + fechaActual.getDate() + "-" + (fechaActual.getMonth() + 1) + "-" + fechaActual.getFullYear() + "</li></ul>";

    document.getElementById("listaPropiedades").innerHTML += "<ul><li><strong>Hora actual:</strong> " + fechaActual.getHours() + ":" + fechaActual.getMinutes() + "h" + "</li></ul>";

};

function muestraTexto() {

    let texto = "Estoy aprendiendo JavaScript";

    //Muestra en el elemento HTML con id="textoJS" la variable texto con el valor mencionado

    document.getElementById("textoJS").innerHTML = texto;

}

function muestraNumero() {

    //Se hace uso de la clase Math con sus metodos para manipular numeros
    document.getElementById("numAleatorio").innerHTML = Math.floor(Math.random() * (10 - 0) + 0);

}

function setCookie() {
    //Para establecer una cookie, primerpo se obtiene el valor del input con id "url"
    let valorCookie = document.getElementById("url").value;
    /*Se establece la cookie con document.cookie y se le da nombre a la cookie con "url_usuario" y se
    asigna el valor pasado en el input*/
    document.cookie = "url_usuario = " + valorCookie;
}

//Creamos una funcion que abrira una ventana nueva
function openWindow() {

    //Declaramos una variable que tendrá el valor de la una ventana que ejecutará un HTML(nueva_ventana.html)

    //Le pasamos una serie de valores como alto y ancho, la posicion, etc.
    let opWind = window.open("nueva_ventana.html", "", "menubar=no,scrollbar=yes, width=400, height=400, top=250, left=550");

    //tambien con el metodo setTimeout que cerrara la ventana una vez que se haya abierto en 7 segundos
    window.setTimeout(function () {
        opWind.close();
    }, 7000);
}

