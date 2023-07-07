package actividad07.libreria;

import java.sql.SQLException;
import java.util.Scanner;

import actividad07.exceptions.ErrorAutor;
import actividad07.exceptions.ErrorCadena;
import actividad07.exceptions.ErrorLibro;
import actividad07.utils.Utilidades;

public class LibreriaMain {
	// Se declara una variable estática "db" de tipo GestionBBDD, que se utilizará
	// para interactuar con la base de datos
	static GestionBBDD db;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		// Se intenta obtener una instancia de la clase GestionBBDD, que se utilizará
		// para interactuar con la base de datos
		try {
			db = GestionBBDD.obtenerInstancia();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Se utiliza un bucle do-while para mostrar un menú de opciones al usuario y
		// ejecutar la acción correspondiente
		do {
			System.out.println("Selecciona una de las siguientes opciones: ");
			System.out.println("1: Restablecer la Base de Datos");
			System.out.println("2: Mostrar autores disponibles");
			System.out.println("3: Mostrar libros disponibles");
			System.out.println("4: Modificar autor");
			System.out.println("5: Eliminar libro");
			System.out.print("0: Salir");

			opcion = Utilidades.pedirEntero("");

			// Se utiliza un bloque switch para ejecutar la acción correspondiente en
			// función de la opción seleccionada por el usuario
			switch (opcion) {
			case 1:
				// Se llama al método "restablecerBBDD" de la clase GestionBBDD para restablecer
				// la base de datos
				try {
					db.restablecerBBDD();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				// Se llama al método "mostrarAutores" de la clase GestionBBDD para mostrar los
				// autores disponibles en la base de datos
				try {
					db.mostrarAutores();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;
			case 3:
				// Se llama al método "mostrarLibros" de la clase GestionBBDD para mostrar los
				// libros disponibles en la base de datos
				try {
					db.mostrarLibros();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;

			case 4:
				// Se llama al método "modificarAutor" de la clase GestionBBDD para modificar la
				// información de un autor en la base de datos
				try {
					db.modificarAutor();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ErrorAutor e) {
					e.printStackTrace();
				} catch (ErrorCadena e) {
					e.printStackTrace();
				}

				break;
			case 5:
				// Se llama al método "eliminarLibro" de la clase GestionBBDD para eliminar un
				// libro de la base de datos
				try {
					db.eliminarLibro();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ErrorLibro e) {
					e.printStackTrace();
				}

				break;

			case 0:
				System.out.println("Hasta luego!");
				break;

			default:
				System.out.println("Debes escoger alguna de las opciones disponibles");
				break;
			}

		} while (opcion != 0);
		sc.close();

	}
}
