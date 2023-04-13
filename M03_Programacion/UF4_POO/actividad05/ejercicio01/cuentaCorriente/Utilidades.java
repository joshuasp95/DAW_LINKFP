package actividad05.ejercicio01.cuentaCorriente;

import java.util.Scanner;

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

//	Metodo de clase que devuelve un decimal ingresado por el usuario (si introduce un 
//	entero lo convierte a decimal; e.j 25 -> 25.0
	public static double pedirDecimal(String pregunta) {
//	Creamos las variables al igual que en el caso anterior
		Scanner sc = new Scanner(System.in);
		double numDecimal = 0;
		boolean esDecimal = false;

		do {
//			Se va a preguntar al usuario la pregunta mientras no se cumpla la condicion
			System.out.println(pregunta);
//			Comprobamos que el numero introducido es un decimal, debe ser con ',' en lugar de '.'
			if (sc.hasNextDouble()) {
				numDecimal = sc.nextDouble();
//				Cambiamos el valor del booleano para terminar el bucle
				esDecimal = true;
			} else {
				// Si no es un decimal se muestra al usuario el siguiente mensaje
//				Si el usuario introduce un '.' tambien saldra error
				System.out.println("Error: Numero introducido no valido. ");
				sc.next();
			}

		} while (!esDecimal);

		return numDecimal;
	}

}
