
package actividad03_asixciber.operaciones.aritmeticas;

import static actividad03_asixciber.introduceDatos.Pregunta.pideDouble;

public class Operaciones {//clase con funciones que si devuelven valores correspondiente a operaciones matematicas con decimales

    public static double muestraLogaritmo(double valor_jsp) {//funcion que devolvera el resultado que es el logaritmo neperiano de un numero decimal
        //System.out.println(Math.log(valor_jsp)); Esta linea tambien podria usarse para una funcion con void sin retorno de variable (al contrario que en este caso)
        return Math.log(valor_jsp);
    }

    public static double calculaPotencia(double valor1_jsp, double valor2_jsp) {// funcion que devuelve el resultado de elevar un numero a otro 
        /* double resultado_pow_jsp = Math.pow(valor1_jsp, valor2_jsp);
        System.out.println(resultado_pow_jsp);
        return resultado_pow_jsp;
        Se podia haber hecho tambien con este codigo:
        return Mat.pow(valor1_jsp, valor2_jsp); y haberlo guardado en el main con otra variable y realizar un System.out.print en el mismo main
         */
        valor1_jsp = pideDouble("");
        valor2_jsp = pideDouble("");

        double resultado_pow_jsp = Math.pow(valor1_jsp, valor2_jsp);
        System.out.println("El resultado de elevar " + valor1_jsp + " a la " + valor2_jsp + " es: " + resultado_pow_jsp);
        return resultado_pow_jsp;
    }

}
