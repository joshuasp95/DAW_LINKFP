package actividad02_asix;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio01 {

    /*
     * : escribe un programa que pida al usuario 5 valores decimales del 0 al 100.
     * Se deben almacenar en un array y mostrarlos por consola. Si el usuario
     * introduce un valor incorrecto, se ha de volver a pedir
     * 
     * @param args
     */
    public static void main(String[] args) {
        int opcion = 0;
        double[] valores = new double[5];

        // rellena el array con valores del 0 al 10
        System.out.println("Introduce 5 valores ");
        for (int i = 0; i < valores.length; i++) {
            double numero = 0;
            boolean valorCorrecto = false;
            while (!valorCorrecto) {
                System.out.println("Indica un valor decimal entre 0 y 100");
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    numero = Double.parseDouble(br.readLine());
                    if (numero < 0 || numero > 100) {
                        System.out.println("Valor incorrecto. Ha de ser un valor entre 0 y 100");
                        continue;
                    }
                    valorCorrecto = true;
                } catch (Exception e) {
                    System.out.println("Valor incorrecto. Ha de ser un valor numerico.");
                }
            }
            valores[i] = numero;
        }
        // Se deben almacenar a un array y mostrarlas por consola.
        for (int i = 0; i < valores.length; i++) {
            System.out.print("[" + valores[i] + "]");
        }
        /*
         * Mediante un SWITCH, el programa debe mostrar el siguiente mensaje al usuario:
         * Introduzca la operación a realizar del siguiente menú de opciones: 1-
         * Modificar el valor almacenado en una posición. 2- Mostrar el resultado de
         * sumar todos los números 3- Mostrar el número más alto y más bajo. 4- Ordena
         * el array situando primero todos los números pares y luego los impares
         * 0-Finalizar Cada vez que se realice la operación 1, 2, 3 y 4 se ha de mostrar
         * por pantalla los valores almacenados en el array. Controla mediante un DO
         * WHILE que una vez realizada la operación seleccionada se vuelva a mostrar el
         * menú de operaciones excepto si el usuario ha introducido un 0. Si se
         * introduce un valor menor a 0, indica que se ha introducido un valor
         * incorrecto y vuelve a mostrar el menú.
         * 
         */
        do {

            System.out.print("\nIntroduzca la operación a realizar del siguiente menú de opciones:\n"
                    + "1- Modificar el valor almacenado en una posición.\n"
                    + "2- Mostrar el resutlado de sumar todos los números\n"
                    + "3- Mostrar el número más alto y más bajo.\n"
                    + "4- Ordena el array situando primero todos los números pares y luego los impares\n"
                    + "0-Finalizar\n");
            boolean repite = true;
            // si no nos da un numero se lo pedimos de nuevo
            while (repite) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    String op = br.readLine();
                    opcion = Integer.parseInt(op);
                    repite = false;
                } catch (Exception e) {
                    System.out.println("No has introducido un valor numerico entero. Intantalo de nuevo.");
                }
            }

            switch (opcion) {

                // Si el usuario selecciona la opción 1 el programa debe pedir al usuario una
                // posición de array válida y un valor numerico entre 0 y 100 para situarlo en
                // la posicion indicada.

                case 1: // Modificar un valor: en este caso se pedirá qué posición del array se quiere
                        // modificar y su nuevo valor. A continuación ha de mostrar todos los valores
                        // almacenados en el array.
                    int posicion = 0;
                    System.out.println("Que posicion ocupa el valor que quieres modificar?"
                            + " (Debe ser un nuermo entre 0 y " + (valores.length - 1));
                    repite = true;
                    while (repite || posicion >= valores.length || posicion < 0) {
                        try {
                            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                            String op = br.readLine();
                            posicion = Integer.parseInt(op);
                            repite = false;
                        } catch (Exception e) {
                            System.out.println("No has introducido un valor numerico entero. Intentalo de nuevo.");
                        }
                    }
                    double numero = 0;
                    boolean valorCorrecto = false;
                    while (!valorCorrecto) {
                        System.out.println("Indica el nuevo valor decimal");
                        try {
                            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                            numero = Double.parseDouble(br.readLine());
                            if (numero < 0 || numero > 100) {
                                System.out.println("Valor incorrecto. Ha de ser un valor entre 0 y 100");
                                continue;
                            }
                            valorCorrecto = true;
                        } catch (Exception e) {
                            System.out.println("Valor incorrecto. Ha de ser un valor numerico.");
                        }
                    }

                    valores[posicion] = numero;

                    // Mostramos el array por consola
                    System.out.println("contenio del array:");
                    for (int i = 0; i < valores.length; i++) {
                        System.out.print("[" + valores[i] + "]");
                    }
                    System.out.println("");
                    break;

                case 2:
                    // Si el usuario selecciona la opción 2 el programa debe sumar todos los números
                    // almacenados en el array y mostrar el resultado.
                    double resultado = 0;
                    for (int i = 0; i < valores.length; i++) {
                        resultado += valores[i];
                    }
                    System.out.println("El resultado de la suma es:" + resultado);
                    // Mostramos el array por consola
                    System.out.println("contenio del array:");
                    for (int i = 0; i < valores.length; i++) {
                        System.out.print("[" + valores[i] + "]");
                    }
                    System.out.println("");

                    break;

                case 3:
                    // Si el usuario selecciona la opción 3 el programa debe mostrar el número más
                    // alto y el más bajo almacenados en el array.

                    double valorBajo = valores[0];
                    double valorAlto = valores[0];
                    for (int i = 0; i < valores.length; i++) {
                        if (valores[i] < valorBajo) {
                            valorBajo = valores[i];
                        }
                        if (valores[i] > valorAlto) {
                            valorAlto = valores[i];
                        }
                    }
                    System.out.println("El valor mas bajo es: " + valorBajo + " el valor mas alto es:" + valorAlto);
                    // Mostramos el array por consola
                    System.out.println("contenio del array:");
                    for (int i = 0; i < valores.length; i++) {
                        System.out.print("[" + valores[i] + "]");
                    }
                    System.out.println("");
                    break;

                case 4:
                    // 4- Ordena el array situando primero todos los números pares y luego los
                    // impares
                    double valoresPares[] = new double[valores.length];
                    int numeroDePares = 0;
                    double valoresImpares[] = new double[valores.length];
                    int numeroDeImpares = 0;

                    // recorremos el array para destriar los pares de los impares
                    for (int i = 0; i < valores.length; i++) {
                        // miramos si es un numero par
                        if (valores[i] % 2 == 0) {
                            valoresPares[numeroDePares] = valores[i];
                            numeroDePares++;
                        } else {
                            valoresImpares[numeroDeImpares] = valores[i];
                            numeroDeImpares++;
                        }
                    }
                    // guardamos primero los pares
                    for (int i = 0; i < numeroDePares; i++) {
                        valores[i] = valoresPares[i];

                    }
                    // guardamos los impares
                    for (int i = 0; i < numeroDeImpares; i++) {
                        // empzamos a guardar el impar después del último par
                        valores[numeroDePares + i] = valoresImpares[i];
                    }

                    // Mostramos el array por consola
                    System.out.println("contenio del array:");
                    for (int i = 0; i < valores.length; i++) {
                        System.out.print("[" + valores[i] + "]");
                    }
                    System.out.println("");
                    break;
                case 0:
                    System.out.println("Cerrando Aplicacion. Hasta pronto!");
                    break;

                default:
                    System.out.println("La opcion elegida no es valida. Opciones del 0 al 4");
            }

        } while (opcion != 0);

    }
}
