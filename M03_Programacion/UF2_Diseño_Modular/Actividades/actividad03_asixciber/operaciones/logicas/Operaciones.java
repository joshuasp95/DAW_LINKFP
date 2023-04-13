package actividad03_asixciber.operaciones.logicas;

import static actividad03_asixciber.introduceDatos.Pregunta.pideDouble;
import static actividad03_asixciber.introduceDatos.Pregunta.pideEntero;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Operaciones {// clase que contiene funciones que realizan operaciones logicas que se van a llamar en el main

    public static double calculaMayor3Numeros(double num_mayor_jsp) {//funcion que calculara el mayor de 3 numeros decimales introducidos en un array
        double[] valores_array_jsp = new double[3];

        for (int i_jsp = 0; i_jsp < valores_array_jsp.length; i_jsp++) {// primer bucle del array para pedir al usuario que determine sus valores
            valores_array_jsp[i_jsp] = pideDouble("");
        }
        System.out.print("Los valores introducidos son: ");
        for (int i_jsp = 0; i_jsp < valores_array_jsp.length; i_jsp++) {//segundo bucle del array para mostrar los valores introducidos
            System.out.print("[" + valores_array_jsp[i_jsp] + "] ");
        }
        num_mayor_jsp = valores_array_jsp[0];
        for (int i_jsp = 0; i_jsp < valores_array_jsp.length; i_jsp++) {//tercer bucle para saber que valor será el mayor
            if (num_mayor_jsp < valores_array_jsp[i_jsp]) {
                num_mayor_jsp = valores_array_jsp[i_jsp];
            }
        }
        //System.out.println("\nEl valor mas alto es: " + A);
        return num_mayor_jsp;
    }

    public static void muestraNombreMes() {//funcion que mostrara el nombre del mes en funcion del numero ingresado por el usuario
        System.out.println("Introduce un numero del 1 al 12");

        int num_mes_jsp = pideEntero("");
        if ((num_mes_jsp >= 1) && (num_mes_jsp <= 12)) {
            //Codigo reciclado de internet
            GregorianCalendar calendar_jsp = new GregorianCalendar();
            calendar_jsp.set(Calendar.DAY_OF_MONTH, 1);
            calendar_jsp.set(Calendar.MONTH, num_mes_jsp - 1);

            DateFormat formatter = new SimpleDateFormat("MMMM", new Locale("es", "ES"));

            String mes_string_jsp = formatter.format(calendar_jsp.getTime());
            System.out.println("El mes al que corresponde el numero " + num_mes_jsp + " es: " + mes_string_jsp.toUpperCase());
        } else {
            System.out.println("Numero introducido no corresponde a ningun mes");
        }

    }

}
