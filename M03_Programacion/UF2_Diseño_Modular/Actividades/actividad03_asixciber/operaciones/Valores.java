package actividad03_asixciber.operaciones;

import static actividad03_asixciber.introduceDatos.Pregunta.pideDouble;
import static actividad03_asixciber.introduceDatos.Pregunta.pideEntero;

public class Valores {//clase donde las funciones muestran valores no devuelven ningun valor

    public static void muestraE() {//funcion que muestra el valor del numero E
        System.out.println("El valor del numero 'e' es: " + Math.E);
    }

    public static void muestraValorAbsoluto() {//funcion que muestra el valor absoluto del numero ingresado por el usuario 
        double resultado = Math.abs(pideDouble(""));
        System.out.println("El valor absoluto del numero introducido es: " + resultado) ;
    }

    public static void muestraValorAleatorio() {//funcion que muestra un numero aleatorio entre el 0 y el numero introducido por el usuario
        System.out.println("Introduce un numero entero ");
        System.out.println("(Se mostrara un numero entero entre el 0 y el numero introducido)");
        int valor_min_jsp = 0;
        int valor_aleatorio_jsp = (int) Math.floor(Math.random() * (pideEntero("") - valor_min_jsp + 1) + valor_min_jsp);
        System.out.print("El valor aleatorio es --> ");
        System.out.println(valor_aleatorio_jsp);

    }

}
