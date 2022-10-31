/*Se ejecuta la función cuando se clique encima del boton HTML que lo que hara sera abrir una nueva ventana
con la url pasada en el primer HTML con el input y posteriormente la cookie, con una expresion REGEX, se carga como
url solo con el valor de la cookie, sin su nombre,.
*/

function cargarWeb() {
    //se carga con una posicion determinada con top y left y un tamaño determinado con width y height
    window.open(document.cookie.replace(/(?:(?:^|.*;\s*)url_usuario\s*\=\s*([^;]*).*$)|^.*$/, "$1"), "", "menubar=no, width=400, height=400, top=150, left=550");
}
