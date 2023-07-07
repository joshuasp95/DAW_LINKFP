package actividad07.tienda;

import java.util.Scanner;

import actividad07.exceptions.ErrorCadena;
import actividad07.exceptions.ErrorEmpleado;

public class GestionaTiendas {

	public static void main(String[] args) {
		int seguir = 1;
		Scanner input = new Scanner(System.in);
		int opcion;

		GestionBBDD db = new GestionBBDD();
		System.out.println("Conexion creada correctamente.");

		do {
			System.out.println("Menu:");
			System.out.println("1. Mostrar los empleados");
			System.out.println("2. Mostrar las tiendas");
			System.out.println("3. Mostrar tiendas ordenadas por ventas");
			System.out.println("4. Editar un empleado");
			System.out.println("5. Crear una nueva tienda");
			System.out.println("0. Salir");
			System.out.print("Ingrese su opcion: ");
			opcion = Integer.parseInt(input.next());

			switch (opcion) {
			case 1:
				db.mostrarEmpleados();
				break;
			case 2:
				db.mostrarTiendas();
				break;
			case 3:
				db.mostrarTiendasOrdVentas();
				break;
			case 4:
				try {
					db.modificarEmpleado();
				} catch (ErrorEmpleado e) {
					e.printStackTrace();
				} catch (ErrorCadena e) {
					e.printStackTrace();
				}
				break;
			case 5:
				try {
					db.crearTienda();
				} catch (ErrorCadena e) {
					e.printStackTrace();
				} catch (ErrorEmpleado e) {
					e.printStackTrace();
				}
				break;
			case 0:
				db.cerrarConexion();
				System.out.println("Conexion cerrada correctamente.");
				seguir = opcion;
				System.out.println("Hasta luego!");
				input.close();
				break;
			default:
				System.out.println("Opcion invalida");
				break;
			}

		} while (seguir != 0);

	}

}
