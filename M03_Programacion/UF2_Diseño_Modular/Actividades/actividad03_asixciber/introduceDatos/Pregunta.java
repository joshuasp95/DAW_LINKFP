package actividad03_asixciber.introduceDatos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pregunta {//Clase que contiene las funciones que pediran ingresar valores al usuario

    public static int pideEntero(String pregunta) {//la funcion pide valor entero al usuario

        BufferedReader br1_jsp = new BufferedReader(new InputStreamReader(System.in));
        String var_convert_jsp;
        int valor_introd_jsp = 1;//este valor servirá para mantener el bucle
// se mete un do while para que si hay un error de tipo string y el programa va por el catch vuelva a pedir el numero
        do {
            try {
                var_convert_jsp = br1_jsp.readLine();
                valor_introd_jsp = Integer.parseInt(var_convert_jsp);
                break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Por favor introduce un numero entero");
            }

        } while (valor_introd_jsp != 0);
        return valor_introd_jsp;
    }

    public static double pideDouble(String pregunta) {// la funcion pide valor decimal al usuario
        BufferedReader br2_jsp = new BufferedReader(new InputStreamReader(System.in));
        String var_double_convert_jsp;
        double valor_double_introd_jsp = 1;
        System.out.println("Introduce un numero decimal");
        do {
            try {
                var_double_convert_jsp = br2_jsp.readLine();
                valor_double_introd_jsp = Double.parseDouble(var_double_convert_jsp);
                System.out.println("El valor introducido es: "+valor_double_introd_jsp);
                break;
            } catch (IOException | NumberFormatException e ) {
                System.out.println("Por favor introduce un numero decimal valido");
                
            } 

        } while (valor_double_introd_jsp != 0);
        return valor_double_introd_jsp;

    }

}
