
package Actividad01;

import java.util.Scanner;


public class Ejercicio02 {

    
    public static void main(String[] args) {
        
        
    //2.1
        //Variables primitivas o simples:
        
        //Tipo de dato entero de 32 bits de longitud
        int jsp_variableEntera = 10;
        System.out.println("Variable tipo int muestra datos enteros de 32 bits. Ejemplo:" + jsp_variableEntera);
        
        //Tipo de dato que toma 2 posibles valores (true o false)
        boolean jsp_variableBooleana = true;
        System.out.println("Variable tipo boolean que muestra 2 valores (true o false). Ejemplo:" + jsp_variableBooleana);
        
        //Tipo de dato de 8 bits de longitud, números enteros de -128 al 127
        byte jsp_variableByte = 15;
        System.out.println("Variable tipo byte que muestra numeros enteros de 8 bits. Ejemplo:" + jsp_variableByte);
        
        //Tipo de dato que puede representar un caracter perteneciente normalmente a ASCII
        char jsp_variableChar = 'h';
        System.out.println("Variable tipo char que muestra caracteres unicode de 16 bits. Ejemplo:" + jsp_variableChar);
        
        //Tipo de dato que contiene numeros enteros del -32768 al 32767 de 2 bytes (16 bits)
        short jsp_variableShort = 8974;
        System.out.println("Variable tipo short que muestra numeros enteros de 16 bits. Ejemplo:" + jsp_variableShort);
        
        //Tipo de dato que muestra numeros enteros de 64 bits de longitud
        long jsp_variableLong = 887984648;
        System.out.println("Variable tipo long que muestra numeros enteros de 64 bits. Ejemplo:" + jsp_variableLong);
        
        //Tipo de dato que muestra numeros decimales con una precision de 7 digitos
        float jsp_variableFloat = 53.6f;
        System.out.println("Variable tipo float que muestra numeros decimales con precision de 7 digitos de 32 bits. Ejemplo:" + jsp_variableFloat);
        
        //Tipo de dato que representa numeros decimales con precision de 16 digitos (64 bits)
        double jsp_variableDouble = 843.4854;
        System.out.println("Variable tipo double que muestra numeros decimales con precision de 16 digitos de 64 bits. Ejemplo:" + jsp_variableDouble);
        
    //2.2
    
        //Operadores aritméticos
        
        //Definimos 2 variables tipo int con las que vamos a operar
        
        double jsp_A = 2.2;
        double jsp_B = 4.2;
        System.out.println("-- Operadores Aritméticos --");
        System.out.println("El valor de A es: " + jsp_A);
        System.out.println("El valor de B es: " + jsp_B);
        //Suma    
        double jsp_resultado_suma = jsp_A + jsp_B; // Resultado = 6.4
        System.out.println("El resultado de la suma de A+B es: " + jsp_resultado_suma);
        
        //Resta
        double jsp_resultado_resta = jsp_B - jsp_A; //Resultado = 2
        System.out.println("El resultado de la resta B-A es: " + jsp_resultado_resta);
        
        //Definimos otras 2 variables
        int jsp_C = 5;
        int jsp_D = 10;       
        System.out.println("El valor de C es: " + jsp_C);
        System.out.println("El valor de D es: " + jsp_D);
        
        //Multiplicacion
        
        int jsp_resultado_multiplicacion = jsp_C * jsp_D; //Resultado = 50
        System.out.println("El valor de la multiplicacion C*D es: " + jsp_resultado_multiplicacion);
        
        //Division
        int jsp_resultado_division = jsp_D / jsp_C;//Resultado = 2
        System.out.println("El valor de la division D/C es: " + jsp_resultado_division);
        
        //Resto
        int jsp_resultado_resto_division = jsp_D % jsp_C;//Resultado = 0
        System.out.println("El valor del resto D/C es: " + jsp_resultado_resto_division);
        
    //Operadores relacionales
    
    //Definimos las variables que esta vez seran requeridas para introducirse por parte del usuario
    
    System.out.println("-- Operadores Relacionales --");
    System.out.println("Introducir 2 numeros enteros para las variables F y G: ");
    
    Scanner jsp_variablePuente1 = new Scanner(System.in);
    
    int jsp_numeroEntero1;
    jsp_numeroEntero1 = jsp_variablePuente1.nextInt(); //Asigna el numero entero introducido por el usuario a la variable definida 
    
    Scanner jsp_variablePuente2 = new Scanner(System.in);
    
    int jsp_numeroEntero2;
    jsp_numeroEntero2 = jsp_variablePuente2.nextInt();
    
    System.out.println("El valor de F es: " + jsp_numeroEntero1);
    System.out.println("El valor de G es: " + jsp_numeroEntero2);
    
    //Comparar si 2 valores son iguales con == (true si son iguales, false si son diferentes)
    
    boolean jsp_igualdadnumeros = jsp_numeroEntero1 == jsp_numeroEntero2;  
    System.out.println("Los 2 valores introducidos son iguales si el resultado es true si son distintos será false: " + jsp_igualdadnumeros);
    
    //Comparar 2 valores para saber si son distintos (true si lo son, false si son iguales)

    boolean jsp_numerosdistintos = jsp_numeroEntero1 != jsp_numeroEntero2;
    System.out.println("Los 2 valores introducidos son distintos si el resultado es true y son iguales si sale false: " + jsp_numerosdistintos);
    
    //Compara 2 valores para saber si uno es mayor que otro
    
    boolean jsp_numeromayor = jsp_numeroEntero1 > jsp_numeroEntero2;
    System.out.println("El valor de F es mayor que G si el resultado es true si es menor será false: " + jsp_numeromayor);
    
    //Compara 2 valores para saber si uno es mayor o igual que otro
    
    boolean jsp_numeromayorigual = jsp_numeroEntero1 >= jsp_numeroEntero2;
    System.out.println("El valor de F es mayor o igual que G si el resultado es true si es menor será false: " + jsp_numeromayorigual);
    
    //Operadores lógicos
    
    System.out.println("-- Operadores Lógicos --");
    
    //Con las variables booleanas definidas anteiormente realizaremos las siguientes operaciones lógicas
    
    //Operacion logica que consiste en que ambas condiciones se cumples, esto es, son true
    boolean jsp_operacionlogica1 = jsp_igualdadnumeros && jsp_numeromayorigual;
    System.out.println("El valor de la operacion logica es true si F y G son iguales si no sera false: " + jsp_operacionlogica1);
    
    //Operacion logica que consiste en que una de 2 condiciones se cumplan o ambas se cumplan para que el resultadod de la operacion sea true
    
    boolean jsp_operacionlogica2 = jsp_numerosdistintos || jsp_numeromayor;
    System.out.println("El valor de la operacion logica es false solo si F es igual a G si es distinto será true: " + jsp_operacionlogica2);
    
    //Operadores Aritméticos Unarios
    
    System.out.println("-- Operadores Aritméticos Unarios --");
    
    //Con las variables F y G realizamos las siguientes operaciones aritméticas unarias
    
    //Incrementar en 1 el valor de la variable jsp_F

    int jsp_F = jsp_numeroEntero1;
    int jsp_G = jsp_numeroEntero2;
    
    System.out.println("Operaciones con pre-: ");
    //Primero con pre-incremento asigna el incremento antes de la salida por pantalla    
    int jsp_preincremento = ++jsp_F;
    System.out.println("El valor del pre-incremento sería sumar 1 a F: " + jsp_preincremento);
    
    //Disminuir en 1 el valor de la variable G
    //Primero con pre-decremento asigna el decremento antes de la salida por pantalla
    int jsp_predecremento = --jsp_G;
    System.out.println("El valor del pre-decremento consiste en disminuir 1 a G: " + jsp_predecremento);
    
    System.out.println("Operaciones con post-: ");
    //Repetimos la operación con el post incremento, ahora primero saca por pantalla y luego asigna el valor
    int jsp_H = jsp_F;
    int jsp_I = jsp_G;
    
    int jsp_postincremento = jsp_H++;
    System.out.println("Primero se muestra el valor anterior al incremento: " + jsp_postincremento);
    System.out.println("Por último, se muestra el valor incrementado: " + jsp_H);
    
    //Y lo mismo para el post decremento
    
    int jsp_postdecremento = jsp_I--;
    System.out.println("Primero sale por pantalla el valor anterior al decremento: " + jsp_postdecremento);
    System.out.println("Ahora el valor decrementado: " + jsp_I);
    
    //Operadores Aritméticos de Asignación
    
    System.out.println("-- Operadores Aritméticos de Asignación --");
    
    int jsp_X = 50; //Se asigna un valor a la variable
    System.out.println("Valor de variable X: " + jsp_X);
    
    jsp_X += 10; //Se le suma 10 al valor de la variable X
    System.out.println("Valor de la operación de suma 10 a variable X: " + jsp_X);
    
    jsp_X *=2; //Se multiplica por 2 el valor de la variable X
    System.out.println("El valor de la multiplicación por 2 a X es: " + jsp_X);
    
    }
    
}
