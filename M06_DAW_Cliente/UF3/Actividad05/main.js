//DIVS OCULTOS
//CONTENDRAN LOS INPUTS QUE SE DESPLEGARAN AL CLICAR ENCIMA
let divSiso = document.getElementById("intro_siso");
divSiso.style.display = "none";

let divGen = document.getElementById("intro_gen");
divGen.style.display = "none";

// VALIDAR FORMULARIO

// RECOGER VARIABLES

let formu = document.getElementById("formulario");
let nomUser = document.getElementById("nom_user");
let emailUser = document.getElementById("ema_user");
let telUser = document.getElementById("tel_user");
let passUser = document.getElementById("pass_user");
let passRepUser = document.getElementById("pass_user_rep");
let dateUser = document.getElementById("date_user");

let msjError = document.getElementsByClassName("error");
let iconOK = document.getElementsByClassName("success-icon");
let iconERR = document.getElementsByClassName("failure-icon");

//FUNCION PARA RECORRER EL DOM CON LOS IDs DE LOS INPUTS
//Y LAS CLASES DE LOS ICONOS Y SE IMPRIMIRA UN MENSAJE
//PARA COMPROBAR SI ESTAN VACIOS O NO
//ES VALIDO PARA EL APARTADO 2 DE LA ACTIVIDAD

let detectErr = (idInp, posClaIco, mnsErr) => {
  //Error 1: si no se ha introducido ningun valor
  if (idInp.value.trim() === "") {
    //introducir mensaje de error y cambiar estilo
    msjError[posClaIco].innerHTML = mnsErr;
    idInp.style.border = "2px solid red";

    //mostrar el icono de error
    iconERR[posClaIco].style.opacity = "1";
    iconOK[posClaIco].style.opacity = "0";
    return false;
  } else {
    //Si no hay error no mostamos nada pero si ponemos estilos
    msjError[posClaIco].innerHTML = "";
    idInp.style.border = "2px solid chartreuse";

    //mostrar el icono de error
    iconERR[posClaIco].style.opacity = "0";
    iconOK[posClaIco].style.opacity = "1";
    return true;
  }
};

// VALIDAR NOMBRE USUARIO

nomUser.addEventListener("input", validarNomUser);

function validarNomUser() {
  //recoger la longitud del string con el nombre del usuario
  let longNom = nomUser.value.length;
  //Determinar si esta entre los 2 valores necesarios
  if (longNom < 3 || longNom > 10) {
    //introducir mensaje de error y cambiar estilo
    msjError[0].innerHTML = "El nombre debe tener entre 3 y 10 caracteres";
    nomUser.style.border = "2px solid red";

    //mostrar el icono de error
    iconERR[0].style.opacity = "1";
    iconOK[0].style.opacity = "0";

    return false;
  } else {
    //Si no hay error no mostamos nada pero si ponemos estilos
    msjError[0].innerHTML = "";
    nomUser.style.border = "2px solid chartreuse";

    //mostrar el icono de error
    iconERR[0].style.opacity = "0";
    iconOK[0].style.opacity = "1";

    return true;
  }
}

// VALIDAR EMAIL

emailUser.addEventListener("input", validarEmailUser);

function validarEmailUser() {
  //Recogemos el valor del email introducido por el usuario
  let email = emailUser.value;
  //lo validamos con una regex para ver si tiene el formato xxx@xxx.xxx
  if (/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email) === false) {
    //introducir mensaje de error y cambiar estilo
    msjError[1].innerHTML = "El email debe ajustarse al formato xxx@xxx.xxx";
    emailUser.style.border = "2px solid red";

    //mostrar el icono de error
    iconERR[1].style.opacity = "1";
    iconOK[1].style.opacity = "0";

    return false;
  } else {
    //Si no hay error no mostamos nada pero si ponemos estilos
    msjError[1].innerHTML = "";
    emailUser.style.border = "2px solid chartreuse";

    //mostrar el icono de error
    iconERR[1].style.opacity = "0";
    iconOK[1].style.opacity = "1";

    return true;
  }
}

// VALIDAR TELEFONO
telUser.addEventListener("input", validarTelUser);

function validarTelUser() {
  let telefono = telUser.value;

  if (/^\d{3}[ ](\d{2}[ ]?){3}$/.test(telefono) == false) {
    //introducir mensaje de error y cambiar estilo
    msjError[2].innerHTML =
      "Ajustese al formato solicitado, e.j: '944 34 45 54'";
    telUser.style.border = "2px solid red";

    //mostrar el icono de error
    iconERR[2].style.opacity = "1";
    iconOK[2].style.opacity = "0";
    return false;
  } else {
    //Si no hay error no mostamos nada pero si ponemos estilos
    msjError[2].innerHTML = "";
    telUser.style.border = "2px solid chartreuse";

    //mostrar el icono de error
    iconERR[2].style.opacity = "0";
    iconOK[2].style.opacity = "1";
    return true;
  }
}

// VALIDAR CONTRASEÑA

passUser.addEventListener("input", validarPassUser);

function validarPassUser() {
  let longPass = passUser.value.length;
  if (longPass < 4 || longPass > 10) {
    //introducir mensaje de error y cambiar estilo
    msjError[3].innerHTML = "La contraseña debe tener entre 4 y 10 caracteres";
    passUser.style.border = "2px solid red";

    //mostrar el icono de error
    iconERR[3].style.opacity = "1";
    iconOK[3].style.opacity = "0";
    return false;
  } else {
    //Si no hay error no mostamos nada pero si ponemos estilos
    msjError[3].innerHTML = "";
    passUser.style.border = "2px solid chartreuse";

    //mostrar el icono de error
    iconERR[3].style.opacity = "0";
    iconOK[3].style.opacity = "1";
    return true;
  }
}

// VALIDAR CONTRASEÑA REPETIDA

passRepUser.addEventListener("blur", validarPassRepUser);

function validarPassRepUser() {
  let passRep = passRepUser.value;
  let passOri = passUser.value;
  //si se queda vacia se pone color verde, se da un aviso al
  //usuario cambiando de color solo, se controla tambien con
  //la funcion
  if (passRep == "") {
    passRepUser.style.border = "2px solid red";
  } else {
    if (passRep !== passOri) {
      //introducir mensaje de error y cambiar estilo
      msjError[4].innerHTML = "Las contraseñas no son iguales";
      passRepUser.style.border = "2px solid red";

      //mostrar el icono de error
      iconERR[4].style.opacity = "1";
      iconOK[4].style.opacity = "0";
      return false;
    } else {
      //Si no hay error no mostamos nada pero si ponemos estilos
      msjError[4].innerHTML = "";
      passRepUser.style.border = "2px solid chartreuse";

      //mostrar el icono de error
      iconERR[4].style.opacity = "0";
      iconOK[4].style.opacity = "1";
      return true;
    }
  }
}

// VALIDAR FECHA

dateUser.addEventListener("input", validarDateUser);

function validarDateUser() {
  //Construimos la fecha actual con un nuevo objeto de la Clase Date
  let fechaNueva = new Date();
  let dia = fechaNueva.getDate();
  let mes = fechaNueva.getMonth() + 1;
  let ano = fechaNueva.getFullYear();
  //Lo pasamos a un string
  let fecha_str = ano + "-" + mes + "-" + dia;
  //convertimos el string en un objeto de la clase date para poder
  //comparar ambos valores
  let fechaAct = new Date(fecha_str);
  let fechaUser = new Date(dateUser.value);

  if (fechaUser >= fechaAct) {
    //introducir mensaje de error y cambiar estilo
    msjError[5].innerHTML = "Tu fecha debe ser menor a la fecha actual";
    dateUser.style.border = "2px solid red";

    //mostrar el icono de error
    iconERR[5].style.opacity = "1";
    iconOK[5].style.opacity = "0";
    return false;
  } else {
    //Si no hay error no mostamos nada pero si ponemos estilos
    msjError[5].innerHTML = "";
    dateUser.style.border = "2px solid chartreuse";

    //mostrar el icono de error
    iconERR[5].style.opacity = "0";
    iconOK[5].style.opacity = "1";
    return true;
  }
}

//CREAR NUEVO SO

//posicion en el checkbox a crear
let numSO = 2;
//Obtenemos el elemento del HTML que servira para que al clicar encima se
//despliegue el formulario

let divClic = document.getElementById("sis_op");

//Obtenemos el elemento HTML que contendra los checkbox generados
let selectSO = document.getElementById("checkbox1");

//Creamos el formulario y los inputs con sus atributos
let form_siso = document.createElement("form");
form_siso.setAttribute("id", "formulario_siso");
form_siso.setAttribute("method", "get");
form_siso.setAttribute("action", "#");

let input_siso = document.createElement("input");
input_siso.setAttribute("id", "inUser");
input_siso.setAttribute("type", "text");

let submit = document.createElement("input");
submit.setAttribute("type", "submit");
submit.setAttribute("value", "Añadir");

//Decimos que cuando se clique encima se ejecute la funcion añadirSO
divClic.onclick = añadirSO;

//añadimos el formulario al divSiso

divSiso.appendChild(form_siso);

//añadimos al formulario los inputs
form_siso.appendChild(input_siso);

form_siso.appendChild(submit);

//Escondemos el formulario para que solo se vea al ejecutar la funcion
// form.style.visibility = "hidden";

//Declaramos los elementos a crear fuera de la funcion, ambito global

let newCheckBox, newLabel;

function añadirSO() {
  divSiso.style.display = "inline";
  //creamos el nuevo checkbox
  newCheckBox = document.createElement("input");
  //le asignamos atributos
  newCheckBox.setAttribute("type", "checkbox");
  newCheckBox.setAttribute("name", "siso");
  newCheckBox.setAttribute("id", `siso${numSO}`);
  newCheckBox.setAttribute("value", `${numSO}`);

  //Creamos un nuevo label
  newLabel = document.createElement("LABEL");
  newLabel.setAttribute("for", `siso${numSO}`);

  //Cuando se pulse el submit se ejecutara la siguiente funcion
  form_siso.addEventListener("submit", añadirCheckBox);
}

//introducimos los checkbox en el div correspondiente
function añadirCheckBox() {
  //verificamos que el usuario ha introducido algun valor
  if (input_siso.value == "" || input_siso.value == null) {
    alert("Introduce algun sistema operativo");
    return 0;
  } else {
    //asignamos el valor del input al nuevo div
    newLabel.innerHTML = input_siso.value;

    //lo añadimos al div final
    selectSO.appendChild(newCheckBox);
    selectSO.appendChild(newLabel);

    //vaciamos el valor del input
    input_siso.value = "";
    divSiso.style.display = "none";

    numSO++;
  }
}

// CREAR NUEVO GENERO MUSICAL

//posicion en el checkbox a crear
let numGen = 3;
//Obtenemos el elemento del HTML que servira para que al clicar encima se
//despliegue el formulario

let divClic2 = document.getElementById("gen_mus");

//Obtenemos el elemento HTML que contendra los checkbox generados
let selectSO2 = document.getElementById("checkbox2");

//Creamos el formulario y los inputs con sus atributos
let form_gen = document.createElement("form");
form_gen.setAttribute("id", "formulario_gen");
form_gen.setAttribute("method", "get");
form_gen.setAttribute("action", "#");

let input_gen = document.createElement("input");
input_gen.setAttribute("id", "inUser2");
input_gen.setAttribute("type", "text");

let submit_gen = document.createElement("input");
submit_gen.setAttribute("type", "submit");
submit_gen.setAttribute("value", "Añadir");

//Decimos que cuando se clique encima se ejecute la funcion añadirGen
divClic2.onclick = añadirGen;

//añadimos el formulario al divGen
divGen.appendChild(form_gen);

//añadimos al formulario los inputs
form_gen.appendChild(input_gen);
form_gen.appendChild(submit_gen);

let newCheckBox2, newLabel2;

function añadirGen() {
  divGen.style.display = "inline";

  //creamos el nuevo checkbox
  newCheckBox2 = document.createElement("input");

  //le asignamos atributos
  newCheckBox2.setAttribute("type", "checkbox");
  newCheckBox2.setAttribute("name", "music");
  newCheckBox2.setAttribute("id", `music${numGen}`);
  newCheckBox2.setAttribute("value", `${numGen}`);

  //Creamos un nuevo label
  newLabel2 = document.createElement("LABEL");
  newLabel2.setAttribute("for", `music${numGen}`);

  //Cuando se pulse el submit se ejecutara la siguiente funcion
  form_gen.addEventListener("submit", añadirCheckBoxMus);
}

//introducimos los checkbox en el div correspondiente
function añadirCheckBoxMus() {
  //verificamos que el usuario ha introducido algun valor
  if (input_gen.value == "" || input_gen.value == null) {
    alert("Introduce algun genero musical");
    return 0;
  } else {
    //asignamos el valor del input al nuevo div
    newLabel2.innerHTML = input_gen.value;

    //lo añadimos al div final
    selectSO2.appendChild(newCheckBox2);
    selectSO2.appendChild(newLabel2);

    //vaciamos el valor del input
    input_gen.value = "";
    divGen.style.display = "none";

    numGen++;
  }
}

// VALIDACION TOTAL
//EN CASO DE PULSAR EL BOTON ENVIAR

formu.addEventListener("submit", (enviar) => {
  let errores = [];
  let contador = 0;

  errores[0] = detectErr(
    nomUser,
    0,
    "El nombre del usuario no puede estar vacío"
  );
  errores[1] = detectErr(
    emailUser,
    1,
    "El email del usuario no puede estar vacío"
  );
  errores[2] = detectErr(
    telUser,
    2,
    "El telefono del usuario no puede estar vacío"
  );
  errores[3] = detectErr(
    passUser,
    3,
    "La contraseña del usuario no puede estar vacía"
  );

  errores[4] = detectErr(passRepUser, 4, "Repite la contraseña introducida");

  errores[6] = validarNomUser();

  errores[7] = validarEmailUser();

  errores[8] = validarTelUser();

  errores[9] = validarPassUser();

  errores[10] = validarPassRepUser();

  errores[11] = validarDateUser();

  //habia que cambiar este aqui para que detectase que esta vacio y se pusiese en rojo
  //sino al validar date con el if mayor que la fecha actual coge la cadena vacia como correcta
  errores[5] = detectErr(dateUser, 5, "Falta rellenar este apartado");

  detectCheckSO();

  console.log(errores);

  for (let i = 0; i < errores.length; i++) {
    if (errores[i] === false || isCheck == false) {
      contador++;
      enviar.preventDefault();
    }
  }

  if (contador < 1) {
    devolverUsuario();
  }
});

function devolverUsuario() {
  document.cookie = "username=" + nomUser.value;
}

let isCheck;
let numChecks = document.querySelectorAll("input[type='checkbox']").length;

console.log(numChecks);

let cont = numChecks;

function contarChecks() {
  cont = numChecks;
  formulario = document.getElementById("formulario");
  for (var i = 0; i < formulario.elements.length; i++) {
    var elemento = formulario.elements[i];
    if (elemento.type == "checkbox") {
      if (elemento.checked) {
        cont--;
        console.log(cont);
      }
    }
  }
  if (numChecks - cont >= 2) {
    isCheck = true;
    document.getElementById("area").value =
      "Genial";
  } else {
    isCheck = false;
    document.getElementById("area").value =
      "Selecciona al menos dos casillas";
  }
  console.log(cont);
  console.log(isCheck);
}

console.log(isCheck);

//activar los eventos de todos los checkbox con un bucle y array de los mismos
let arrChecks = [];

let longSisos = document.querySelectorAll("input[name='siso']").length;

let longGens = document.querySelectorAll("input[name='music']").length;

function detectCheckSO() {
  for (let i = 1; i <= longSisos; i++) {
    arrChecks[i] = document.getElementById(`siso${i}`);
  }
  for (let i = 1; i <= longGens; i++) {
    arrChecks.push(document.getElementById(`music${i}`));
  }
  console.log(arrChecks);
  for (let i = 1; i < arrChecks.length; i++) {
    arrChecks[i].addEventListener("change", contarChecks);
  }
}

//la ejecutamos una vez

detectCheckSO();
