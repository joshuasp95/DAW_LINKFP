/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Actividad02;

import java.text.DecimalFormat;

public class Ejercicio02 {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        double[][] punt_pelis_jsp = {{8, 5, 10, 4}, {3, 8, 9, 7}, {9, 7, 6, 8}};
        DecimalFormat df_jsp = new DecimalFormat("###.##");//Dar formato de 2 decimales al double

        System.out.println("Longitud de la 1ª dimension:" + punt_pelis_jsp.length);
        System.out.println("Longitud de la 2ª dimension: " + punt_pelis_jsp[0].length);
        System.out.println(" ");
        //Bucle para recorrer todos los valores del array bidimensional
/*
        for (int p_jsp = 0; p_jsp < punt_pelis_jsp.length; p_jsp++) {

            for (int k = 0; k < punt_pelis_jsp[0].length; k++) {
                System.out.println("El valor de la posicion " + p_jsp + "," + k);
                System.out.println("Valor: " + punt_pelis_jsp[p_jsp][k]);
            }
        }*/
        
        System.out.println("");

            System.out.println("Las notas de la primera pelicula son: ");
            for (int k_jsp = 0; k_jsp < punt_pelis_jsp[0].length; k_jsp++) {
                int p_jsp = 0;
                System.out.print("El valor de la posicion " + p_jsp + "," + k_jsp);
                System.out.println(" es: " + punt_pelis_jsp[p_jsp][k_jsp]);
            }

            double suma_jsp = 0;//la suma se actualiza con el bucle 
            double valor_jsp = 0;
            int c_jsp = 0;//variable que servira para calcular el numero de valores dentro del array

            System.out.println("");
            System.out.print("Las media de la segunda pelicula es: ");
            for (int k_jsp = 0; k_jsp < punt_pelis_jsp[0].length; k_jsp++) {
                c_jsp++;
                int p_jsp = 1;
                valor_jsp = c_jsp;
                suma_jsp += punt_pelis_jsp[p_jsp][k_jsp];
            }
            double media = suma_jsp / valor_jsp;
            System.out.println("" + df_jsp.format(media));
            System.out.println("");
            
            int p_jsp = 2;//variable para indicar que sera la posicion 2 del primer array, la lista de valores sobre la que se va a operar
            double valor_mayor_jsp=punt_pelis_jsp[p_jsp][0];

            System.out.print("El valor mas alto de la tercera pelicula es: ");
            for(int k_jsp=0; k_jsp<punt_pelis_jsp[0].length;k_jsp++){
                
                if(punt_pelis_jsp[p_jsp][k_jsp]>valor_mayor_jsp){
                    valor_mayor_jsp = punt_pelis_jsp[p_jsp][k_jsp];
                }
            }
            System.out.println(valor_mayor_jsp);
        }
    }
