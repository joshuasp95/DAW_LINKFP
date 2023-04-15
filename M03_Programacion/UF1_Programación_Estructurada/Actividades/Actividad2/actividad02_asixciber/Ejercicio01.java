/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Actividad02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Ejercicio01 {
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Introduce 5 valores decimales(e.j: 5.25) del 0 al 100: ");

        DecimalFormat df_jsp = new DecimalFormat("###.##");//Dar formato de 2 decimales al double

        //System.out.println("Rounded Double value (DecimalFormat): "+df.format(val1));(ejemplo Internet)
        BufferedReader br_jsp = new BufferedReader(new InputStreamReader(System.in));//No sirve un Scanner por error con InputMismatchException, coge el valor introducido por el usuario

        double[] arr_valores_jsp = new double[5];

        int reset_jsp = 0;//servira para mantener el bucle while
        String user_in_jsp;
        double conv_double_jsp;

        while (reset_jsp == 0) {
            try {
                for (int i_jsp = 0; i_jsp < arr_valores_jsp.length; i_jsp++) {

                    int p_jsp = 1;
                    int s_jsp = p_jsp + i_jsp;//Variables para mostrar por pantalla (1º Valor del array, ...)

                    user_in_jsp = br_jsp.readLine();
                    conv_double_jsp = Double.parseDouble(user_in_jsp);
//Bucle while para que se ejecute en caso de que el valor introducido sea menor de 0 o mayor de 100
                    while ((conv_double_jsp <= 0) || (conv_double_jsp >= 100)) {
                        System.out.println("El valor no es correcto. Introducelo de nuevo");
                        BufferedReader br3_jsp = new BufferedReader(new InputStreamReader(System.in));
                        user_in_jsp = br3_jsp.readLine();
                        conv_double_jsp = Double.parseDouble(user_in_jsp);
                        //Condicional para que si se introduce bien el dato se salga del bucle con un break
                        if ((conv_double_jsp >= 0) && (conv_double_jsp <= 100)) {
                            break;
                        }
                    }
//Dentro del bucle for se almacenan los valores dentro del aray, posicion por posicion, segun el valor introducido por el usuario
                    arr_valores_jsp[i_jsp] = conv_double_jsp;
                    System.out.print(s_jsp + "º Valor = " + df_jsp.format(arr_valores_jsp[i_jsp]) + " ");
                    System.out.println(" ");

                }
                reset_jsp = 1;//valor para salir del while
//Si no se especifica bien el fallo en el Catch el while se ejecuta siempre por el catch y no vuelve al try
            } catch (NumberFormatException e) {
                System.out.println("Por favor comienza de nuevo desde el primer valor");
                System.out.println("*Los valores deben: estar en un rango de 0 a 100 y tener esta forma '5.54'");
                reset_jsp = 0;
            }

        }
        //Mostrar por pantalla posiciones y valores dentro del array con un bucle for para recorrerlo
        System.out.println("Los valores introducidos son: ");
        for (int b_jsp = 0; b_jsp < arr_valores_jsp.length; b_jsp++) {
            System.out.println("- " + arr_valores_jsp[b_jsp] + " su posicion es la: " + b_jsp);
        }
        
        int opcion_jsp = 0;
        double suma_jsp=0;
                
        do{
            System.out.println(" ");
            System.out.println("Introduzca la operacion a realizar del siguiente menu de opciones: ");
            System.out.println("1- Modificar el valor almacenado en una posicion");
            System.out.println("2- Mostrar el resultado de sumar todos los numeros");
            System.out.println("3- Mostrar el numero mas alto y mas bajo");
            System.out.println("4- Ordenar el array situando primero todos los numeros pares y luego los impares");
            System.out.println("0- Finalizar");

            Scanner sc_jsp = new Scanner(System.in);

            opcion_jsp = sc_jsp.nextInt();

            switch (opcion_jsp) {
                case 1:
                    System.out.print("Valores Almacenados: ");
                    for (double valor_array_jsp : arr_valores_jsp) {
                        System.out.print(valor_array_jsp + ", ");
                    }
                    
                    System.out.println("\nIntroduce una posicion de un valor almacenado(0 a 4)");
                    Scanner sc_1_jsp = new Scanner(System.in);
                    int b_jsp = sc_1_jsp.nextInt();
                    
                    while((b_jsp<0)||(b_jsp>4)){
                        System.out.println("El valor introducido no es correcto");
                        System.out.println("Introduce un valor del 1 al 4");
                        b_jsp = sc_1_jsp.nextInt();
                        if((b_jsp>=0)&&(b_jsp<=4)){
                            break;
                        }
                    }

                    System.out.println("Y ahora introduce el nuevo valor que deseas ubicar en esa posicion (e.j 5,25)");
                    arr_valores_jsp[b_jsp] = sc_1_jsp.nextDouble();
                    
                    while((arr_valores_jsp[b_jsp]<0) || (arr_valores_jsp[b_jsp]>100)){
                        System.out.println("El valor introducido no es correcto");
                        System.out.println("Introduce un valor entre 0 y 100");
                        arr_valores_jsp[b_jsp] = sc_1_jsp.nextDouble();
                        if((arr_valores_jsp[b_jsp]>=0) && (arr_valores_jsp[b_jsp]<=100)){
                            break;
                        }
                    }

                    System.out.println("Valores Almacenados: ");
                    for (double valor_array_jsp : arr_valores_jsp) {
                        System.out.print(valor_array_jsp + ", ");
                    }
                    
                    break;
                    
                case 2:

                    System.out.print("Valores Almacenados: ");
                    for (double valor_array_jsp : arr_valores_jsp) {
                        System.out.print(valor_array_jsp + ", ");
                    }
                    
                    for(int j_jsp=0;j_jsp<arr_valores_jsp.length; j_jsp++){
                        suma_jsp += arr_valores_jsp[j_jsp];
                        
                    }System.out.println("\nEl valor de la suma es: " + suma_jsp);
                    
                    suma_jsp=0;//por si se quiere repetir la suma, la vuelve a poner a 0
                    break;

                case 3:
                    System.out.print("Valores Almacenados: ");
                    for (double valor_array_jsp : arr_valores_jsp) {
                        System.out.print(valor_array_jsp + ", ");
                    }
                    double num_mayor_jsp;
                    double num_menor_jsp;
                    num_mayor_jsp=arr_valores_jsp[0];
                    num_menor_jsp=arr_valores_jsp[0];
                    
                    for(int t_jsp=0; t_jsp<arr_valores_jsp.length;t_jsp++){
                        if(arr_valores_jsp[t_jsp]>num_mayor_jsp){
                            num_mayor_jsp = arr_valores_jsp[t_jsp];
                        }
                    }

                    for (int t = 0; t < arr_valores_jsp.length; t++) {
                        if (arr_valores_jsp[t] < num_menor_jsp) {
                            num_menor_jsp = arr_valores_jsp[t];
                        }
                    }
                    System.out.println("\nEl numero mas alto es: " + num_mayor_jsp);
                    System.out.println("El numero mas bajo es: " + num_menor_jsp);
                    break;



                case 4:
                    System.out.print("Valores Almacenados:");
                    for (double valor_array_jsp : arr_valores_jsp) {
                        System.out.print(valor_array_jsp + ", ");
                    }
                    int v_jsp=0;
                    int m_jsp=0;
                    
                    System.out.println(" ");
                    for(int z_jsp=0;z_jsp<arr_valores_jsp.length;z_jsp++){
                        if(arr_valores_jsp[z_jsp]%2==0){
                            System.out.print(z_jsp +"ºValor Par: "+arr_valores_jsp[z_jsp]+" ");
                            System.out.println(" ");
                        }else{//en caso de que sean todos impares saldria este mensaje
                            v_jsp++;
                            if(v_jsp>4){
                                System.out.println("Parece que todos son impares");
                            }
                        }
                    }
                    for(int f_jsp=0;f_jsp<arr_valores_jsp.length;f_jsp++){
                        if(arr_valores_jsp[f_jsp]%2!=0){
                            System.out.print(f_jsp+ "ºValor Impar: " +arr_valores_jsp[f_jsp]+" ");
                            System.out.println(" ");
                        } else {//si todos son pares saldría este mensaje
                            m_jsp++;
                            if (m_jsp > 4) {
                                System.out.println("Parece que todos son pares");
                            }
                        }
                    }
                        
                    break;

                case 0:
                    //Finaliza el programa
                    break;
                    
                default:
                    System.out.println("\nEl valor introducido es incorrecto. Vuelva a elegir una opcion del menu: ");

            }
        }while(opcion_jsp != 0);
        
        
    }
    
}
