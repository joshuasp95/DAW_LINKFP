package actividad01;

public class Ejercicio02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	/*EJERCICIO 4
            Para cada tipo de dato simple añade dentro de la función main de Ejercicio02  un comentario indicando el tipo de dato, declara una variable de ese tipo, 
            indícale un valor y muestra un mensaje que explique las características del tipo de dato y muestre el valor de la variable
            */
		//Tipo de dato entero de 32 bits de longitud
		int variableEntera =10;
		System.out.println("Variable tipo int muestra datos enteros de 32 bits. Ejemplo:"+variableEntera);
		
		//  Boolean: 1 byte (8 bits) almacenar valores true false.
		boolean esVerdadero = true;	
		System.out.println("Boolean: 1 byte (8 bits) almacenar valores true false. Ejemplo:"+esVerdadero);
		// Short: 2 bytes para almacenar un valor entero entre -32768  y 32767
		short corto = 32767;
		System.out.println("Short: 2 bytes para almacenar un valor entero entre -32768  y 32767. Ejemplo:"+corto);
		// Int: 4 bytes para almacenar un valor entero entre 2.147.483.648 y 2.147.483.647
		int num_ent = 1;	
		System.out.println("Int: 4 bytes para almacenar un valor entero entre 2.147.483.648 y 2.147.483.647. Ejemplo:"+num_ent);
		// Long: 8 bytes. Valor entre -9.223.372.036.854.775.808 y 9.223.372.036.854.775.807
		long num_ent_largo = 12345678901L;
		System.out.println("Long: 8 bytes. Valor entre -9.223.372.036.854.775.808 y 9.223.372.036.854.775.807. Ejemplo:"+num_ent_largo);
		// Float: 4 bytes  Valor entre  (3.4e-038 a 3.4e+038).
		float num_real = 625.25f;
		System.out.println("Float: 4 bytes  Valor entre  (3.4e-038 a 3.4e+038). Ejemplo:"+num_real);
		// Double: 8 bytes . Valor entre  (1.7e-308 a 1.7e+308)
		double num_doble_prec = 625.253333d;
		System.out.println("Double: 8 bytes . Valor entre  (1.7e-308 a 1.7e+308). Ejemplo:"+num_doble_prec);
		// Char: 2bytes para almacenar un caracteres.
		char letra = 'A';	
		System.out.println("Char: 2bytes para almacenar un caracteres.ej:"+letra);

	/*EJERCICIO 4añade un ejemplo de uso de cada uno de los operadores aritméticos,  relacionales, lógicos , unitarios y de asignación. 
                Indicando con un comentario y con un mensaje por consola cual será el resultado de cada uno de ellos.
                */
		// Delaracion de variables para las operaciones
		int num_1=1, num_2=2, num_3=3, num_4=4, num_5=5, num_6=6 ;

		// USO DE OPERADORES ARITMETICOS
		System.out.println(num_1 + " + " + num_2 + "? " + (num_1 + num_2));
		System.out.println(num_1 + " - " + num_2 + "? " + (num_1 - num_2));
		System.out.println(num_1 + " x " + num_2 + "? " + (num_1 * num_2));
		System.out.println(num_1 + " / " + num_2 + "? " + (num_1 / num_2));
		System.out.println(num_1 + " resto_division " + num_2 + "? " + (num_1 % num_2));
		
		
		//USO DE OPERADORES RELACIONALES
		System.out.println(num_1 + " == " + num_2 + "? " + (num_1 == num_2));
		System.out.println(num_1 + " != " + num_2 + "? " + (num_1 != num_2));
		System.out.println(num_1 + " < " + num_2 + "? " + (num_1 < num_2));
		System.out.println(num_1 + " > " + num_2 + "? " + (num_1 > num_2));
		System.out.println(num_1 + " <= " + num_2 + "? " + (num_1 <= num_2));
		System.out.println(num_1 + " >= " + num_2 + "? " + (num_1 >= num_2));

		
		// USO DE OPERADORES LOGICOS

		System.out.println("esVerdadero && esVerdadero ? " + (true && true));
		System.out.println("esVerdadero && esFalso ? " + (true && false));
		System.out.println("esFalso && esFalso ? " + (false && false));
		System.out.println("esFalso && esVerdadero ? " + (false && true));

		System.out.println("esVerdadero || esVerdadero ? " + (true || true));
		System.out.println("esVerdadero || esFalso ? " + (true || false));
		System.out.println("esFalso || esFalso ? " + (false || false));
		System.out.println("esFalso || esVerdadero ? " + (false || true));

		System.out.println("! esVerdadero ? " + ( !true ));
		System.out.println("! esFalso ? " + ( !false ));

		
		// USO DE OPERADORES UNARIOS

		num_3 = num_1 ;
		num_4 = num_2 ;

		System.out.println(" pre-incremento ++" + num_1 + " ? " + (++num_1));
		System.out.println(" post-incremento " + num_3 + "++ ? " + (num_3++));
		System.out.println(" pre-decremento --" + num_2 + " ? " + (--num_2));
		System.out.println(" post-decremento " + num_4 + "-- ? " + (num_4--));

		// USO DE OPERADORESDE ASIGNACION
		num_5 = num_2 ;
		num_6 = num_2 ;
		num_6 += 5 ;
		System.out.println("num_5="+num_5+" y num_5+=5 hace que num_5=" + num_6);
		num_5 = num_6 ;
		num_6 *= 5 ;
		System.out.println("num_5="+num_5+" y num_5*=5 hace que num_5=" + num_6);
	}
}
