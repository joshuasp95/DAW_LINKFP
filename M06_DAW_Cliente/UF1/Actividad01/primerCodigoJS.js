console.log("éste mensaje se muestra por consola");

//accedemos al elemento con id "ordenCorrecto" para que contenga el texto "ERR01: defer obliga al JS a ejecutarse al final" 
let mensaje = "<br/><b>ERR01</b>: defer obliga al JS a ejecutarse al final</br>";
document.getElementById("ordenCorrecto").innerHTML = mensaje;


/**Al ejecutar la función saluda, introduce en #mensaje
 *  el valor pasado por parametro
 * 
 * @param {type} texto
 */
function saluda(texto) {
    //CODIGO ERROR cuidado mayusculas
    document.getElementById("mensaje").innerHTML = texto;

}
/**
 * Muestra un prompt en el que el usuario introduce texto y luego muestra ese texto en mensajePrompt
 */
function pideDatoPrompt() {
    //CODIGO ERROR
    let respuesta = window.prompt("Esta es la pregunta del prompt");

    document.getElementById("mensajePrompt").innerHTML = `<b>ERR03</b>:no confundir 
                                                                    con windowS<br/>` + respuesta;
}
/**Lee el texto del input y luego lo muestra en "mensajeInput"
 * 
 */
function pideDatoInput() {
    let texto = document.getElementById("entradaInfo").value;
    //CODIGO ERROR
    document.getElementById("mensajeInput").innerHTML = `<b>ERR04</b>: los DIV no tienen value,  pero tienen innerHTML</br>` + texto
}

function suma10() {
    let texto = document.getElementById("entradaSuma10").value;
    //CODIGO INCORRECTO
    texto = parseInt(texto) + 10;
    document.getElementById("mensajeSuma10").innerHTML = `<b>ERR05</b>: sin pasar a numero la suma concatena: </br>` + texto

}
/** SOLUCION  calculaOperacion() */


function calculaOperacion() {

    let num1 = document.getElementById("primerNumero").value;

    let num2 = document.getElementById("segundoNumero").value;

    let operacion = document.getElementById("operacion").value;

    let resultado = 0;

    switch (operacion) {

        case "+":
            resultado = parseFloat(num1) + parseFloat(num2);
            document.getElementById("mensajeOperacion").innerHTML = "El resultado es: " + resultado;
            break;
        case "-":
            //resultado = parseFloat(num1) - parseFloat(num2);
            document.getElementById("mensajeOperacion").innerHTML = "Selecciona un operador correcto";
            break;
        case "*":
            resultado = parseFloat(num1) * parseFloat(num2);
            document.getElementById("mensajeOperacion").innerHTML = "El resultado es: " + resultado;
            break;
        case "/":
            document.getElementById("mensajeOperacion").innerHTML = "Selecciona un operador correcto";
            //resultado = parseFloat(num1) / parseFloat(num2);
            break;
        default:
            document.getElementById("mensajeOperacion").innerHTML = "Selecciona un operador correcto";
            break;
    }

}