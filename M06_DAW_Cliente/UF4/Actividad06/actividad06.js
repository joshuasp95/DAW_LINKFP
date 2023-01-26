// PARA VALIDAR LOS INPUTS RECOGEMOS SUS NODOS DEL DOM
let conf1 = document.getElementById("s-conf");
let conf2 = document.getElementById("c-conf");

// RECOGEMOS LOS BOTONES QUE ACTIVARAN AMBAS OPERACIONES GUARDADO Y VALIDACION DE COINCIDENCIA

let botonGuardado = document.getElementById("save-bot");
let botonCheck = document.getElementById("check-bot");

// PRIMER APARTADO

botonGuardado.addEventListener("click", enviarPeticionSave);

function enviarPeticionSave() {
  // TAMBIEN RECOGEMOS LOS INPUTS QUE GUARDARAN LA COMBINACION INTRODUCIDA POR EL USUARIO
  let input1 = document.getElementById("s1").value;
  let input2 = document.getElementById("s2").value;
  let input3 = document.getElementById("s3").value;
  let input4 = document.getElementById("s4").value;

  //LOS VALORES SE GUARDARAN EN ARRAYS

  let arrGuardado = [input1, input2, input3, input4];

  //DECLARAMOS EL OBJETO QUE NOS SERVIRA PARA REALIZAR PETICIONES AL SERVIDOR

  let xhr = new XMLHttpRequest();

  //CONVERTIMOS EL ARRAY A UNA CADENA DE TEXTO PARA LUEGO OPERAR EN PHP

  let strArr = JSON.stringify(arrGuardado);

  //ABRIMOS LA CONEXION Y LE PASAMOS LOS PARAMETROS DENTRO AL SER DE TIPO GET
  xhr.open("GET", "server1.php?arr=" + strArr, true);
  //ESTABLECEMOS EL HEADER
  xhr.setRequestHeader("Content-Type", "application/json");

  //CREAMOS UNA FUNCION QUE GESTIONARA EL ESTADO DE LA PETICION
  xhr.onreadystatechange = function () {
    //SI EL ESTADO ES IGUAL A 4 QUIERE DECIR QUE SE HA OBTENIDO LA RESPUESTA
    if (xhr.readyState == 4) {
      console.log("Respuesta recibida correctamente");
      //LLAMAMOS A OTRA FUNCION EN ESTE CASO QUE GESTIONARA LA RESPUESTA DEL SERVIDOR
      enviarRespuestaSave(xhr);
    }
  };

  //ENVIAMOS LA PETICION CON ESTE METODO
  xhr.send();
}

function enviarRespuestaSave(xhr) {
  //SI EQUIVALE A 200 QUIERE DECIR QUE EL RECURSO SE HA ENCONTRADO
  if (xhr.status == 200) {
    console.log("URL de la peticion correcta");
    console.log("Respuesta: " + xhr.responseText);
    var result = JSON.parse(xhr.responseText);

    //IMPRIMIMOS EL MENSAJE EN EL ELEMENTO DEL DOM CORRESPONDIENTE
    if (result["mensaje"]) {
      conf1.innerHTML = result["mensaje"];
    }

    //MOSTRAMOS EL OBJETO TRAIDO DE VUELTA
    for (let keys in result) {
      console.log(keys + "..." + result[keys]);
    }
  }
}

// 2 APARTADO

function comprobarInputSave(n) {
  //RECOGEMOS LOS ELEMENTOS QUE SERVIRAN PARA INSERTAR LOS MENSAJES
  let inputSelec = document.getElementById(`msj${n}`);

  //RECOGEMOS LOS VALORES DE LOS INPUTS A LOS QUE SE LES HAYA MODIFICADO EL VALOR
  let valInputSelec = document.getElementById(`s${n}`).value;

  //HACEMOS AHORA LO MISMO QUE EN EL APARTADO 1 PERO ESTA VEZ EN UNA SOLA FUNCION
  let xhr = new XMLHttpRequest();

  xhr.open("GET", "server2.php?val=" + valInputSelec + "&key=" + n, true);

  xhr.setRequestHeader("Content-Type", "application/json");

  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4 && xhr.status == 200) {
      console.log("URL y Respuesta de la peticion correctas");
      var result = JSON.parse(xhr.responseText);
      inputSelec.innerHTML = result.mensaje;
    }
  };
  xhr.send();
}

//AHORA REPETIMOS LO MISMO PARA LOS 2 APARTADOS SIGUIENTE

//PERO AHORA HAREMOS LAS PETICIONES CON LA API FETCH

// 3 APARTADO

botonCheck.addEventListener("click", enviarPeticionCheck);

function enviarPeticionCheck() {
  let input5 = document.getElementById("c1").value;
  let input6 = document.getElementById("c2").value;
  let input7 = document.getElementById("c3").value;
  let input8 = document.getElementById("c4").value;

  let arrCheck = [input5, input6, input7, input8];

  let arrCheckString = JSON.stringify(arrCheck);

  let configFetch = {
    method: "POST",
    body: "arr=" + arrCheckString,
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
  };

  let promise = fetch("server3.php", configFetch);

  promise
    .then(function (response) {
      if (response.ok) {
        console.log("Se ha realizado la peticion correctamente");
      }
      response.json().then(function (resultJSON) {
        let resp = resultJSON["mensaje"];
        conf2.innerHTML = resp;
      });
    })
    .catch(function (error) {
      console.log(
        "Error: No se ha podido procesar la peticion. " + error.message
      );
    });
}

//4 APARTADO

function comprobarInputCheck(n) {
  let inputSelec = document.getElementById(`c-msj${n}`);
  let valInputSelec = document.getElementById(`c${n}`).value;

  let configFetch = {
    method: "GET",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
  };

  let promesa = fetch(
    "server4.php?" + "val=" + valInputSelec + "&key=" + n,
    configFetch
  );

  promesa
    .then(function (response) {
      if (response.ok) {
        console.log("Se ha realizado la peticion correctamente");
      }
      response.json().then(function (resultJSON) {
        let resp = resultJSON["mensaje"];
        inputSelec.innerHTML = resp;
      });
    })
    .catch(function (error) {
      console.log("Error al procesar la peticion. " + error.message);
    });
}
