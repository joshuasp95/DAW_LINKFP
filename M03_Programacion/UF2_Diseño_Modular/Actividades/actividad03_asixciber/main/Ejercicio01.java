package actividad03_asixciber.main;

import static actividad03_asixciber.introduceDatos.Pregunta.pideDouble;
import static actividad03_asixciber.introduceDatos.Pregunta.pideEntero;
import static actividad03_asixciber.operaciones.Valores.muestraE;
import static actividad03_asixciber.operaciones.Valores.muestraValorAbsoluto;
import static actividad03_asixciber.operaciones.Valores.muestraValorAleatorio;
import static actividad03_asixciber.operaciones.aritmeticas.Operaciones.calculaPotencia;
import static actividad03_asixciber.operaciones.aritmeticas.Operaciones.muestraLogaritmo;
import static actividad03_asixciber.operaciones.logicas.Operaciones.calculaMayor3Numeros;
import static actividad03_asixciber.operaciones.logicas.Operaciones.muestraNombreMes;


public class Ejercicio01 {//clase donde estara el menu con las distintas operaciones a hacer

    public static void main(String[] args) {//funcion principal que ira llamando a las demas funciones que contienen las operaciones

        int user_in_jsp = 1;//variable que servira para mantener el do while

        do {

            System.out.println("Selecciona una de las siguientes opciones introduciendo el numero que le corresponde: ");
            System.out.println("1: Mostrar el numero E");
            System.out.println("2: Mostrar el valor del numero absoluto");
            System.out.println("3: Mostrar valores aleatorios entre 0 y el numero a introducir: ");
            System.out.println("4: Mostrar el logaritmo en base E del numero introducido");
            System.out.println("5: Mostrar el resultado de elevar el primer numero introducido al segundo");
            System.out.println("6: Mostrar el mayor de los 3 numeros decimales introducidos");
            System.out.println("7: Mostrar el nombre del mes correspondiente al numero introducido");
            System.out.println("0: Salir del programa");

            user_in_jsp = pideEntero("");//funcion que pide un numero entero con un try catch para evitar error de tipo string
            //la variable user_in_jsp recoge ese valor introducido y lo guarda para recorrer el switch

            switch (user_in_jsp) {
                case 0://opcion para que no vaya por el default y se rompa el ciclo aqui
                    break;
                case 1:
                    muestraE();
                    break;
                case 2:
                    muestraValorAbsoluto();
                    break;
                case 3:
                    muestraValorAleatorio();
                    break;
                case 4:
                    double result_log_jsp = muestraLogaritmo(pideDouble(""));
                    System.out.println("El valor de Ln del numero introducido es: "+ result_log_jsp);
                    /*Se podria haber hecho tambien con: muestraLogaritmo('valor fijo puesto por el programador');
                    pero me gustaba mas que introdujese el valor el usuario*/
                    break;
                case 5:
                   /* Se podria haber hecho con: calculaPotencia(pideDouble(""), pideDouble("")); 
                    lo mismo que en el caso anterior se puede aplicar a este
                    en la funcion aparece comentado el trozo de codigo que serviria para obtener el mismo resultado*/
                    calculaPotencia(0,0);
                    break;
                case 6:
                    double num_mayor_jsp =  calculaMayor3Numeros(0);
                    System.out.println("\nEl numero mas alto es: " +num_mayor_jsp);
                    /*Se podria haber hecho con:
                    calculaMayor3Numeros(); y añadiendo la linea que aparece comentada en la funcion correspondiente (System.out...)
                    de hecho me parece mas limpia esta opcion*/
                    break;

                case 7:
                    muestraNombreMes();
                    break;
                default://en caso de que el numero introducido no este en ninguna de las opciones del switch
                    System.out.println("\nIntroduce un numero correspondiente a las opciones del menu\n");
                    break;
            }

        } while (user_in_jsp != 0);
    }

}
