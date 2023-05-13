package actividad07.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import actividad07.exceptions.ErrorCadena;

public class Utilidades {

//	Metodo de clase que devuelve un entero ingresado por el usuario
	public static int pedirEntero(String pregunta) {
//	Creamos las variables 
		Scanner sc = new Scanner(System.in);
		int numEntero = 0;
		boolean esEntero = false;

		do {
//			Se va a preguntar al usuario la pregunta mientras no se cumpla la condicion
			System.out.println(pregunta);
//			Comprobamos que el numero introducido es un entero
			if (sc.hasNextInt()) {
				numEntero = sc.nextInt();
//				Cambiamos el valor del booleano para terminar el bucle
				esEntero = true;
			} else {
				// Si no es un entero se muestra al usuario el siguiente mensaje
				System.out.println("Error: Numero introducido no valido. ");
				sc.next();
			}

		} while (!esEntero);

		return numEntero;
	}

	// Funcion para introducir datos de texto por el usuario
	public static String leerCadena(String pregunta) throws ErrorCadena {
		// Se crea un objeto BufferedReader que leerá la entrada del usuario desde la
		// consola
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cadena = null;

		// Se utiliza un bucle do-while para leer la entrada del usuario hasta que se
		// proporcione una cadena válida
		do {
			// Se imprime la pregunta para que el usuario sepa qué se espera que ingrese
			System.out.println(pregunta);

			// Se utiliza un bloque try-catch para leer la entrada del usuario y manejar
			// posibles excepciones
			try {
				// Se lee una línea de entrada del usuario utilizando el objeto BufferedReader
				cadena = br.readLine();
			} catch (IOException e) {
				// Si se produce una excepción al leer la entrada del usuario, se establece la
				// variable "cadena" a null
				cadena = null;

				// Se lanza una excepción personalizada para indicar que ha ocurrido un error al
				// leer la entrada de usuario
				throw new ErrorCadena("Error: Cadena de texto introducida no valida");
			}
		} while (cadena == null);

		// Se devuelve la cadena de texto leída del usuario
		return cadena;
	}

}
