//APARTADO 1

//Obtenemos el elemento del HTML que contendra el texto que queremos mostrar
let textoUser = document.getElementById("addText");

//Decimos que cuando se clique encima se ejecute la funcion definida
textoUser.onclick = añadirTextoUser;

let form1 = document.createElement("form");
form1.setAttribute("id", "formulario1");
form1.setAttribute("method", "get");
form1.setAttribute("action", "#");

//Creamos una variable para que la funcion solo se ejecute una vez
let breakpoint = 0;

function añadirTextoUser() {
  //solo entrara una vez
  if (breakpoint <= 0) {
    //reemplazamos el contenido del div con el formulario definimos en la funcion
    textoUser.appendChild(form1);
    form1.innerHTML = añadirInput("text", "inUser1");

    //creamos un evento para que cuando se pulse la tecla submit se ejecute otra funcion
    document
      .getElementById("formulario1")
      .addEventListener("submit", cambiarTexto);

    breakpoint = 1;
  } else {
    //mandamos un mensaje a consola para avisar de que no es posible insertar mas texto
    console.log("Ya has insertado una cadena de texto");
  }
}

//esta funcion sirve para reemplazar el contenido del input del formulario
function cambiarTexto() {
  txt = document.getElementById("inUser1").value;

  //se reemplaza en el div original
  textoUser.innerHTML = txt;
}

function añadirInput(tipo, id) {
  return `<input type=${tipo} id=${id}>
    <input type="submit" value="Enviar">`;
}

//APARTADO 2

//Obtenemos el div con la opcion de cambiar el color
let colorUser = document.querySelector("#setColor");

//Ejecutamos la funcion una vez se haga click en el nodo mencionado
colorUser.addEventListener("click", elegirColor);

//creamos el input de tipo color con un id
let clrInput = document.createElement("input");

clrInput.setAttribute("id", "inUser2");
clrInput.setAttribute("type", "color");

//No sirve esta variable al final
// let inputTime;

function elegirColor() {
  //Adjuntamos el input al nodo padre solo cuando se haga clic en el mismo
  colorUser.appendChild(clrInput);

  //Cuando seleccione un color lo estableceremos como fondo con la funcion cambiarColor

  clrInput.addEventListener("input", cambiarColor);

  //Al final no sirve
  //inputTime = setTimeout(eliminarInput, 10000);
}

function cambiarColor() {
  //Establecemos el color de fondo con el valor en hexadecimal del input
  colorUser.style.backgroundColor = clrInput.value;
}

//No funciona porque elimina la posibilidad de volver a cambiar el color de fondo
// function eliminarInput() {
//   let padre = clrInput.parentNode;

//   padre.removeChild(clrInput);
// }

//APARTADO 3

//Obtenemos el elemento del HTML que contendra el input para meter el texto y enviar
let divUser = document.getElementById("addDiv");

//Obtenemos el elemento HTML que contendra los divs generados
let nodosDiv = document.getElementById("domNodes");

//Creamos el formulario, los inputs con sus atributos
let form2 = document.createElement("form");
form2.setAttribute("id", "formulario2");
form2.setAttribute("method", "get");
form2.setAttribute("action", "#");

let input = document.createElement("input");
input.setAttribute("id", "inUser3");
input.setAttribute("type", "text");

let submit = document.createElement("input");
submit.setAttribute("type", "submit");

//Decimos que cuando se clique encima se ejecute la funcion definida de añadir DIV
divUser.onclick = añadirDiv;

//añadimos el formulario al divuser (si no me daba error, dentro de la funcion)
divUser.appendChild(form2);
//añadimos al formulario los inputs
form2.appendChild(input);
form2.appendChild(submit);

//Escondemos el formulario para que solo se vea al ejecutar la funcion
form2.style.visibility = "hidden";

//Declaramos el nuevo div fuera de la funcion
let newDiv;

function añadirDiv() {
  //creamos el nuevo div cuando el usuario clique encimad e addDiv
  newDiv = document.createElement("div");
  //le asignamos una clase
  newDiv.setAttribute("class", "addDiv");

  //mostramos el formulario
  form2.style.visibility = "visible";

  //Cuando se pulse el submit se ejecutara la siguiente funcion
  form2.addEventListener("submit", cambiarTextoDiv);
}

//esta funcion sirve para introducir el div en el apartado correcto dentro de domNodes
function cambiarTextoDiv() {
  //asignamos el valor del input al nuevo div
  newDiv.innerHTML = input.value;

  //lo añadimos al div final
  nodosDiv.appendChild(newDiv);

  //volvemos a esconder el formulario
  form2.style.visibility = "hidden";

  //vaciamos el valor del input
  input.value = "";
}

//APARTADO 4

//Obtenemos el elemento del HTML que contendra el input para meter el texto y enviar
let spanUser = document.getElementById("addSpan");

//Obtenemos el elemento HTML que contendra los span generados
let nodosSpan = document.getElementById("domNodes");

//Creamos el formulario, los inputs con sus atributos
let form3 = document.createElement("form");
form3.setAttribute("id", "formulario3");
form3.setAttribute("method", "get");
form3.setAttribute("action", "#");

let input3 = document.createElement("input");
input3.setAttribute("id", "inUser3");
input3.setAttribute("type", "text");

let submit3 = document.createElement("input");
submit3.setAttribute("type", "submit");

//Decimos que cuando se clique encima se ejecute la funcion definida de añadir Span
spanUser.onclick = añadirSpan;

//añadimos el formulario al spaUser
spanUser.appendChild(form3);
//añadimos al formulario los inputs
form3.appendChild(input3);
form3.appendChild(submit3);

//Escondemos el formulario para que solo se vea al ejecutar la funcion
form3.style.visibility = "hidden";

//Declaramos el nuevo div fuera de la funcion
let newSpan;

function añadirSpan() {
  //creamos el nuevo span cuando el usuario clique encimad e addSpan
  newSpan = document.createElement("span");

  //le asignamos una clase
  newSpan.setAttribute("class", "addSpan");

  //mostramos el formulario
  form3.style.visibility = "visible";

  //Cuando se pulse el submit se ejecutara la siguiente funcion
  form3.addEventListener("submit", cambiarTextoSpan);
}

//esta funcion sirve para introducir el span en el apartado correcto dentro de domNodes
function cambiarTextoSpan() {
  //asignamos el valor del input al nuevo span
  newSpan.innerHTML = input3.value;

  //lo añadimos a los nodos con los otros divs
  nodosSpan.appendChild(newSpan);

  //volvemos a esconder el formulario
  form3.style.visibility = "hidden";

  //vaciamos el valor del input
  input3.value = "";
}

//APARTADO 5

//Obtenemos el elemento donde se clicara
let div5 = document.querySelector("#setContentPrev");

//Preparamos la funcion a ejecutar con el evento click
div5.addEventListener("click", añadirDiv5);

//declaramos las variables necesarias (texto de usuario y el nuevo div apartado 1_5)
let txtUser, div_1_5;

function añadirDiv5() {
  //creamos el elemento div
  div_1_5 = document.createElement("div");

  //añadimos contenido
  div_1_5.innerHTML = "setContentPrev";
  div_1_5.setAttribute("class", "setContentPrev");

  //lo añadimos a los nodos del dom con los otros divs y spans
  document.getElementById("domNodes").appendChild(div_1_5);

  //programamos otro evento que aparezca al clicar en el div creado
  div_1_5.addEventListener("click", promptUsuario);
}

function promptUsuario() {
  //pedimos otro texto al usuario esta vez con el prompt, es mas sencillo en este caso,
  //aunque para el usuario sea menos amigable
  txtUser = prompt(
    "Introduce un texto",
    "Programando en JS y sin morir en el intento"
  );

  //obtenemos el elemento hermano del div creado
  let elemtHerm = div_1_5.previousSibling;

  //le añadimos el texto introducido por el usuario
  elemtHerm.innerHTML += "<br>" + txtUser;
}

//APARTADO 6

//Obtenemos el elemento donde se clicara
let div6 = document.querySelector("#delPrevNode");

//Preparamos la funcion a ejecutar con el evento click
div6.addEventListener("click", crearDiv1_6);

//declaramos el nuevo div, apartado 1_6
let div_1_6;

function crearDiv1_6() {
  //creamos el elemento div
  div_1_6 = document.createElement("div");

  //añadimos contenido
  div_1_6.innerHTML = "Remove Preview";
  div_1_6.setAttribute("class", "delPrevNode");

  //lo añadimos a los nodos del dom con los otros divs y spans
  document.getElementById("domNodes").appendChild(div_1_6);

  //programamos otro evento que aparezca al clicar en el div creado
  div_1_6.addEventListener("click", eliminarDivAnt);
}

function eliminarDivAnt() {
  //solo entra si el elemento anterior al div creado es distinto de null
  if (div_1_6.previousSibling !== null) {
    //busca el elemento anterior
    let elemtAnt = div_1_6.previousSibling;
    //obtiene el elemento padre
    let nodoPadre = elemtAnt.parentNode;
    //desde el padre se elimina al anterior al actual
    nodoPadre.removeChild(elemtAnt);
  } else {
    //si no hay mas elementos se avisa por consola
    console.log("No se pueden eliminar mas elementos");
  }
}

//APARTADO 7

//Obtenemos el elemento donde se clicara
let div7 = document.querySelector("#delNextNode");

//Preparamos la funcion a ejecutar con el evento click
div7.addEventListener("click", crearDiv1_7);

//declaramos el nuevo div, apartado 1_6
let div_1_7;

function crearDiv1_7() {
  //creamos el elemento div
  div_1_7 = document.createElement("div");

  //añadimos contenido
  div_1_7.innerHTML = "Remove Next";
  div_1_7.setAttribute("class", "delNextNode");

  //lo añadimos a los nodos del dom con los otros divs y spans
  document.getElementById("domNodes").appendChild(div_1_7);

  //programamos otro evento que aparezca al clicar en el div creado
  div_1_7.addEventListener("click", eliminarDivSig);
}

function eliminarDivSig() {
  //solo entra si el elemento siguiente al div creado es distinto de null

  if (div_1_7.nextSibling !== null) {
    //obtenemos el elemento siguiente al div creado
    let elemtSig = div_1_7.nextSibling;
    //obtenemos el nodo padre
    let nodoPadre = elemtSig.parentNode;
    //eliminamos el siguiente elemento
    nodoPadre.removeChild(elemtSig);
  } else {
    //si no hay mas elementos se avisa por consola
    console.log("No se pueden eliminar mas elementos");
  }
}

//APARTADO 8

let atribUser = document.getElementById("addAttr");

atribUser.addEventListener("click", valoresUser);

let form4 = document.createElement("form");
form4.setAttribute("id", "formulario4");
form4.setAttribute("method", "get");
form4.setAttribute("action", "#");

let input4 = defInput("input4", "number", "inUser4");
let input5 = defInput("input5", "text", "inUser5");
input5.setAttribute("placeholder", "Nombre del atributo");
let input6 = defInput("input6", "text", "inUser6");
input6.setAttribute("placeholder", "Valor del atributo");

let submit4 = document.createElement("input");
submit4.setAttribute("type", "submit");

form4.appendChild(input4);
form4.appendChild(input5);
form4.appendChild(input6);
form4.appendChild(submit4);

atribUser.appendChild(form4);

form4.style.visibility = "hidden";

function valoresUser() {
  form4.style.visibility = "visible";
  form4.addEventListener("submit", recorrerNodos);
}

function recorrerNodos() {
  let nodoActual;

  let i = input4.value;
  let atrib = input5.value;
  let valAtrib = input6.value;

  let nodosDOM = document.querySelector("#domNodes").childNodes;

  let elmtCreado = false;

  for (let index = 0; index < nodosDOM.length; index++) {
    if (nodosDOM[index].nodeType == 1) {
      if (index == i) {
        elmtCreado = true;
        if (nodosDOM[index] == null) {
          let newElmt = document.createElement("div");
          newElmt.setAttribute(atrib, valAtrib);
        } else {
          nodosDOM[index].setAttribute(atrib, valAtrib);
        }
      }
    }
  }
  if (elmtCreado == false) {
    alert("No hay ningun elemento en esa posicion");
  }

  form4.style.visibility = "hidden";
}

function defInput(nomInput, tipo, id) {
  nomInput = document.createElement("input");
  nomInput.setAttribute("id", id);
  nomInput.setAttribute("type", tipo);
  return nomInput;
}
