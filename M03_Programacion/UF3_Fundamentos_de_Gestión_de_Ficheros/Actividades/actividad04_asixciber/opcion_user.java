
package actividad04_asixciber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class opcion_user {//Clase que contiene las funciones que pediran ingresar valores al usuario

    public static int pideEntero(String valor) {//la funcion pide valor entero al usuario

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


}
