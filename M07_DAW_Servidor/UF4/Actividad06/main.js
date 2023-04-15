//CAPTURAMOS EL NODO DEL HTML QUE CONTENDRA EL SELECT CON LAS OPCIONES PARA BUSCAR POR
// LOCALIDAD Y EL TIPO DE LOCAL EXISTENTE EN LA BBDD
let selLocalidades = document.getElementById("localidad");
let selLocales = document.getElementById("tipoLocal");

// PETICION A SERVIDOR PHP CON FETCH PARA VALORES DE TABLA

//Creamos la varibale map de forma global
let map;

//FUNCION PARA MOSTRAR LOS VALORES DE LOCALIDADES Y TIPOS DE LOCALES EN LOS SELECT

function mostrarValoresBBDD() {
  let configFetch = {
    method: "GET",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
  };

  let promesa = fetch("valoresBBDD.php", configFetch);

  promesa
    .then(function (response) {
      if (response.ok) {
        console.log("Se ha realizado la peticion correctamente");
      }
      response.json().then(function (resultJSON) {
        console.log(resultJSON);
        //SI SE HA HECHO LA PETICION Y SE HA DEVUELTO EL OBJETO LLAMAMOS A LA FUNCION PARA GENERAR LOS OPTION
        //DEL SELECT EN EL HTML
        generarSelectHTML(resultJSON);
      });
    })
    .catch(function (error) {
      console.log("Error al procesar la peticion. " + error.message);
    });
}

//FUNCION PARA CARGAR EL MAPA
function initMap() {
  console.log("cargando mapa");
  const myLatLng = {
    lat: 43.32,
    lng: -2.9,
  };
  // Crea un mapa y lo muestra en el elemento con ID "map"
  map = new google.maps.Map(document.getElementById("map"), {
    zoom: 11,
    center: myLatLng,
  });
  console.log("mapa----");
  console.log(map);
}

//EJECUTAMOS LA FUNCION PARA CARGAR EL MAPA EN EL HTML
window.init = initMap;

//AL CARGAR LA PAGINA SE MUESTRAN LOS VALORES DE LA BBDD EN EL SERVIDOR
window.onload = mostrarValoresBBDD();

//AL HACER CLIC EN EL BOTON DE BUSCAR SE EJECUTA LA FUNCION
function buscarEnMapa() {
  //RECOGEMOS LOS VALORES DEL SELECT ELEGIDOS POR EL USUARIO
  let localidad = document.getElementById("localidad").value;
  let tipoLocal = document.getElementById("tipoLocal").value;

  //COMPROBAMOS QUE SE RECOGEN CORRECTAMENTE
  // console.log(localidad + "---" + tipoLocal);

  //LE DAMOS FORMATO A LA PETICION FETCH AL SERVIDOR
  let configFetch = {
    method: "GET",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
  };

  //HACEMOS LA PETICION AL SERVIDOR PASANDOLE 2 PARAMETROS
  let promesa = fetch(
    "valoresMapa.php?" + "localidad=" + localidad + "&tipoLocal=" + tipoLocal,
    configFetch
  );

  //CONTROLAMOS QUE SE RETORNE UN OBJETO JSON DEL SERVER
  promesa
    .then(function (response) {
      if (response.ok) {
        console.log("Se ha realizado la peticion correctamente");
      }
      response.json().then(function (resultJSON) {
        console.log(resultJSON);
        //AL RECOGER EL JSON EJECUTAMOS UNA FUNCION PARA GENERAR LOS MARCADORES
        generarMarcadores(resultJSON);
      });
    })
    .catch(function (error) {
      console.log("Error al procesar la peticion. " + error.message);
    });
}

function generarMarcadores(objeto) {
  //CREAMOS LA VENTANA QUE SE GENERARA AL HACER CLICK ENCIMA DEL MARCADOR
  const infowindow = new google.maps.InfoWindow();

  //RECORREMOS EL OBJETO CON UN BUCLE FOR...IN
  for (let keys in objeto) {
    //TRANSFORMAMOS LAS COORDENADAS A UN FORMATO ACEPTABLE PARA LA API DE GOOGLE MAPS DE JS
    let position = getLatLngFromString(objeto[keys].coordenadas);
    console.log("position");

    //GENERAMOS EL MARCADOR CON UN ICONO QUE SE GENERA CON LA SIGUIENTE FUNCION
    let icon = generarIcono(objeto[keys].tipo);

    //CREAMOS EL MARCADOR EN EL MAPA CON LA POSICION Y TITULO RECOGIDOS EN EL JSON
    const marker = new google.maps.Marker({
      position: position,
      map: map,
      title: objeto[keys].nombre,
      icon: icon,
    });

    //EJECUTAMOS UNA FUNCION PARA OBTENER EL TIEMPO QUE CUANDO SE RESUELVA GENERARA LA VENTANA
    //QUE DEBE APARECER AL HACER CLICK EN EL MARCADOR
    obtenerTiempo(position).then((tiempo) => {
      //CONTROLAMOS QUE SEA LA VARIABLE SEA LA ADECUADA
      console.log(tiempo);
      console.log(typeof tiempo);

      //GENERAMOS EL EVENTO QUE RECOGERA QUE CUANDO SE HAGA CLICK EN EL MARCADOR
      //DESPLIEGUE UNA VENTANA CON EL NOMBRE DEL ESTABLECIMIENTO Y EL TIEMPO EN LA ZONA
      marker.addListener("click", () => {
        infowindow.setContent(
          `<h3>Establecimiento: ${objeto[keys].nombre}</h3><span id="icon-weather">Tiempo</span>
            <img src="http://openweathermap.org/img/w/${tiempo}.png" class="weather-icon"/>`
        );
        infowindow.open({
          anchor: marker,
          map,
        });
      });
    });
  }
}

//FUNCION PARA CONVETIR LA VARIABLE STRING CON LAS COORDENADAS EN UN OBJETO
function getLatLngFromString(coordinates) {
  // Elimina las palabras clave lat y lng del string
  coordinates = coordinates
    .replace("{lat: ", "")
    .replace(" lng: ", "")
    .replace("}", "");
  // Divide el string en dos partes, una para la latitud y otra para la longitud
  var latlng = coordinates.split(",");
  // Convierte cada parte a un número utilizando parseFloat
  var lat = parseFloat(latlng[0]);
  var lng = parseFloat(latlng[1]);
  console.log(lng);
  // Crea un objeto con las propiedades lat y lng
  var latLng = { lat: lat, lng: lng };
  console.log(latLng);
  return latLng;
}

//GENERAMOS ICONOS DEPENDIENDO DEL TIPO DE LOCAL
function generarIcono(tipoLocal) {
  switch (tipoLocal) {
    case "Restaurantes":
      return "imgs/restaurante.png";
    case "Bares":
      return "imgs/bar.png";
    case "Discotecas":
      return "imgs/disco.png";
    default:
      return "imgs/error.png";
  }
}

//GENERAMOS LOS SELECT DEL HTML
function generarSelectHTML(objetoJSON) {
  //CREAMOS 2 ARRAYS VACIOS PARA CADA UNA DE LAS OPCIONES DE LOS SELECT
  let arrPoblaciones = [];
  let arrTiposLocales = [];

  for (let keys in objetoJSON) {
    arrPoblaciones.push(objetoJSON[keys]["poblacion"]);
    arrTiposLocales.push(objetoJSON[keys]["tipo"]);
  }

  //COMO SE VAN A DUPLICAR LOS VALORES YA QUE HAY VARIOS REGISTROS EN LAS MISMAS LOCALIDADES
  //CONTROLAMOS EL ANTES Y EL DESPUES DEL FILTRADO DE LOS ARRAYS
  // console.log(arrPoblaciones);
  // console.log(arrTiposLocales);

  let unicosPoblaciones = [];
  let unicosTiposLocales = [];

  //ELIMINAMOS LOS DUPLICADOS
  eliminarDuplicados(arrPoblaciones, unicosPoblaciones);
  eliminarDuplicados(arrTiposLocales, unicosTiposLocales);

  console.log(unicosPoblaciones);
  console.log(unicosTiposLocales);

  //LOS AÑADIMOS AL SELECT CORRESPONDIENTE
  añadirValoresSelect(unicosPoblaciones, selLocalidades);
  añadirValoresSelect(unicosTiposLocales, selLocales);
}

function añadirValoresSelect(arrayValores, select) {
  for (let i = 0; i < arrayValores.length; i++) {
    let option = document.createElement("option");
    option.value = arrayValores[i];
    option.text = arrayValores[i];
    select.add(option);
  }
}

function eliminarDuplicados(array, filtrado) {
  array.forEach((elemento) => {
    if (!filtrado.includes(elemento)) {
      filtrado.push(elemento);
    }
  });
  return filtrado;
}

//FUNCION PARA OBTENER EL TIEMPO DE UN LUGAR EN UNAS COORDENADAS ESPECIFICAS
async function obtenerTiempo(coordenadas) {
  console.log("API OPENWEATHER");
  console.log(coordenadas.lat);
  console.log(coordenadas.lng);

  //RECOGEMOS LAS COORDENADAS DE LAT Y LNG CADA UNA EN UNA VARIABLE
  let lat = coordenadas.lat;
  let lng = coordenadas.lng;

  console.log(lat);
  console.log(lng);

  //DEFINIMOS LA KEY DE LA APO
  let API_KEY = "INSERTATUAPIKEY";

  //Y LA URL PARA HACER LA PETICION
  let URL = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&appid=${API_KEY}`;

  //HACEMOS LA PETICION
  let promesa = fetch(URL);

  //DECLARAMOS UNA VARIABLE QUE CONTENDRA EL TIEMPO EN CASO DE REALIZARSE ADECUADAMENTE LA PETICION
  let tiempo = "";
  try {
    const response = await promesa;
    if (response.ok) {
      console.log("Peticion a la API Open Weather realizada correctamente");
      const objetoJSON = await response.json();
      console.log(objetoJSON);
      console.log(objetoJSON.weather[0].icon);
      //GUARDAMOS LA DESCRIPCION APORTADA POR LA API EN LA VARIABLE TIEMPO
      tiempo = objetoJSON.weather[0].icon;
    }
  } catch (error) {
    console.log("Error al hacer la peticion. " + error.message);
  }

  //DEVOLVEMOS LA VARIABLE
  return tiempo;
}

//RESETEAMOS LA PAGINA PARA BORRAR LOS MARCADORES
function borrarMarcadores() {
  window.location.reload();
}


