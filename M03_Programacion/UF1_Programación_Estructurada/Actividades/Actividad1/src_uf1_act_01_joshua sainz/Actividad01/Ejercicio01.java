
package Actividad01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio01 {


    public static void main(String[] args) throws IOException {
        
        System.out.println("Tienda de manzanas: ");
        
        int jsp_manzanas_tienda;
        int jsp_manzanas_queremos_comprar;
        double jsp_precio_manzana;
        double jsp_dinero_pagado;

        jsp_manzanas_tienda = 20;
        jsp_manzanas_queremos_comprar = 2;
        jsp_precio_manzana = 0.40;

        System.out.println("Vamos a comprar " + jsp_manzanas_queremos_comprar + " manzanas");
        // modificamos el contenido de las variables numericas para representar la
        // compra de manzanas
        jsp_dinero_pagado = jsp_precio_manzana * jsp_manzanas_queremos_comprar;
        jsp_manzanas_tienda = jsp_manzanas_tienda - jsp_manzanas_queremos_comprar;

        // mostramos por consola el valor de las variables numericas. De forma implícita
        // se convierte de de numero a string.
        System.out.println("Nos han costado:" + jsp_dinero_pagado);
        System.out.println("En la tienda quedan " + jsp_manzanas_tienda + " manzanas");

        System.out.println("Quantas mazanas más quieres comprar?");
        // Declaramos una variable compleja de nombre "br" y de tipo "BufferedReader".
        // Las variables del tipo "BufferedReader" contienen funciones para leer datos
        // por consola
        BufferedReader jsp_br_manzanas = new BufferedReader(new InputStreamReader(System.in));
        // utilizamos la función "readLine" de la variable "br" para leer un dato por
        // consola. Guardamos el número introducido en la variable "valor_escrito"
        String jsp_valor_escrito_manzanas = jsp_br_manzanas.readLine();

        /*
         * Convertimos mediante una conversion explicita la variable "valor_escrito" a
         * un valor entero para poder operar con él y lo almacenamos en la variable
         */
        jsp_manzanas_queremos_comprar = Integer.parseInt(jsp_valor_escrito_manzanas);

        System.out.println("Vamos a comprar " + jsp_manzanas_queremos_comprar + " manzanas");
        // modificamos el contenido de las variables numericas para representar la
        // compra de manzanas
        jsp_dinero_pagado = jsp_precio_manzana * jsp_manzanas_queremos_comprar;
        jsp_manzanas_tienda = jsp_manzanas_tienda - jsp_manzanas_queremos_comprar;
        // mostramos por consola el valor de las variables numericas. De forma implícita
        // se convierte de de numero a string.
        System.out.println("Nos han costado:" + jsp_dinero_pagado);
        System.out.println("En la tienda quedan " + jsp_manzanas_tienda + " manzanas");
        
        System.out.println("Tienda de melones: ");
        //Definimos las variables que vamos a usar y los tipos de datos que les corresponden
            int jsp_melones_tienda;
            int jsp_melones_queremos_comprar;
            double jsp_precio_melon;
            double jsp_dinero_pagado_melones;
        //Ahora dotamos con valores (del enunciado) a esas variables 
            jsp_melones_tienda = 20;
            jsp_precio_melon= 2.30;
        //Requerimos al usuario que ingrese un valor mediante un texto emitido por pantalla
            System.out.println("Cuantos melones vas a comprar?");
        //Definimos una nueva variable con una función que permite leer datos    
            BufferedReader jsp_br_melones = new BufferedReader(new InputStreamReader(System.in));
        //Con la función readLine permitimos a la variable leer un dato introducido por el usuario por consola
        //Se almacena ese valor introducido en la variable creada de tipo string
            String jsp_valor_escrito_melones = jsp_br_melones.readLine();
        //Convertimos la variable anterior a un numero entero tipo int con la función parse y se almacena en otra variable 
            jsp_melones_queremos_comprar = Integer.parseInt(jsp_valor_escrito_melones);
        //Una vez introducido el valor por usuario se muestra el mensaje siguiente por pantalla    
            System.out.println("Vamos a comprar " + jsp_melones_queremos_comprar + " melones");
        //Se definen y modifican variables mediante operaciones aritméticas para mostrar el resultado del proceso de compra de melones   
            jsp_dinero_pagado_melones = jsp_precio_melon * jsp_melones_queremos_comprar;
            jsp_melones_tienda = jsp_melones_tienda - jsp_melones_queremos_comprar;
        //Se muestra el resultado de las operaciones anteriores por pantalla    
            System.out.println("En la tienda quedan " + jsp_melones_tienda + " melones");
            System.out.println("La compra ha costado:" + jsp_dinero_pagado_melones);
            
    }

}
