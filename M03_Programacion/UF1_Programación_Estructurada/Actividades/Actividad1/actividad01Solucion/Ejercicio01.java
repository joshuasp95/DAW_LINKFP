package actividad01;

import java.io.*;

/**
 * Ejercicio01
 */
public class Ejercicio01 {
    public static void main(String[] args) throws IOException {
        int manzanas_tienda;
        int manzanas_queremos_comprar;
        double precio_manzana;
        double dinero_pagado;

        manzanas_tienda = 20;
        manzanas_queremos_comprar = 2;
        precio_manzana = 0.40;

        System.out.println("Vamos a comprar " + manzanas_queremos_comprar + " manzanas");
        // modificamos el contenido de las variables numericas para representar la
        // compra de manzanas
        dinero_pagado = precio_manzana * manzanas_queremos_comprar;
        manzanas_tienda = manzanas_tienda - manzanas_queremos_comprar;

        // mostramos por consola el valor de las variables numericas. De forma implícita
        // se convierte de de numero a string.
        System.out.println("Nos han costado:" + dinero_pagado);
        System.out.println("En la tienda quedan " + manzanas_tienda + " manzanas");

        System.out.println("Quantas mazanas más quieres comprar?");
        // Declaramos una variable compleja de nombre "br" y de tipo "BufferedReader".
        // Las variables del tipo "BufferedReader" contienen funciones para leer datos
        // por consola
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // utilizamos la función "readLine" de la variable "br" para leer un dato por
        // consola. Guardamos el número introducido en la variable "valor_escrito"
        String valor_escrito = br.readLine();

        /*
         * Convertimos mediante una conversion explicita la variable "valor_escrito" a
         * un valor entero para poder operar con él y lo almacenamos en la variable
         */
        manzanas_queremos_comprar = Integer.parseInt(valor_escrito);

        System.out.println("Vamos a comprar " + manzanas_queremos_comprar + " manzanas");
        // modificamos el contenido de las variables numericas para representar la
        // compra de manzanas
        dinero_pagado = precio_manzana * manzanas_queremos_comprar;
        manzanas_tienda = manzanas_tienda - manzanas_queremos_comprar;
        // mostramos por consola el valor de las variables numericas. De forma implícita
        // se convierte de de numero a string.
        System.out.println("Nos han costado:" + dinero_pagado);
        System.out.println("En la tienda quedan " + manzanas_tienda + " manzanas");

        /*
         * 2. Al código anterior (ejercicio01.java) añade después del último
         * System.out.println(); un código que indique que la tienda tiene 15.5 litros
         * de leche a 0.95€/litro y a continuación pida “Cuantos litros de leche vas a
         * comprar”, se reciba un número decimal escrito por el usuario y finalmente se
         * indique cuantos litros quedan a la tienda y cuanto a costado la compra4
         */
        double leche_tienda=15.5;
        double leche_queremos_comprar;
        double precio_leche=0.95;
        System.out.println("Cuanta leche quieres comprar?");
        br = new BufferedReader(new InputStreamReader(System.in));
        valor_escrito = br.readLine();
        leche_queremos_comprar= Double.parseDouble(valor_escrito);

        dinero_pagado= precio_leche*leche_queremos_comprar;
        leche_tienda=leche_tienda- leche_queremos_comprar;

        System.out.println("La leche ha costado:"+dinero_pagado);
        System.out.println("En la tienda quedan:"+leche_tienda);

    }

}