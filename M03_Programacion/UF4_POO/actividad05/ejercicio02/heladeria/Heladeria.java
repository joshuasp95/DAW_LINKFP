package actividad05.ejercicio02.heladeria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import actividad05.ejercicio01.cuentaCorriente.Utilidades;

public class Heladeria {

	public static void main(String[] args) {
		System.out.println("**********Heladeria*********");

//		Declaramos e inicializamos las variables
		Loteria loteria = new Loteria(3.5, "119383939");
		Horchata horchata = new Horchata(2.5, "Horchata", 20, 250, 30);
		Cucurucho cucurucho1 = new Cucurucho(1, "Cucurucho de vainilla y chocolate", 20,
				new SaborHelado[] { new SaborHelado(1, "vainilla", 30, 15, "azucar"),
						new SaborHelado(1, "chocolate", 15, 15, "aspartamo"), new SaborHelado(0, null, 0, 0, null) },
				3);
		Cucurucho cucurucho2 = new Cucurucho(1.5, "Cucurucho de galleta", 25,
				new SaborHelado[] { new SaborHelado(1.25, "cookies", 35, 20, "azucar"),
						new SaborHelado(1, "fresa", 10, 5, "aspartamo"), new SaborHelado(0, null, 0, 0, null),
						new SaborHelado(0, null, 0, 0, null) },
				4);
		SaborHelado sabor1 = new SaborHelado(5, "Brownie de Nueces", 40, 20, "sin edulcorantes");
		SaborHelado sabor2 = new SaborHelado(3.4, "Oreo Chocolate Blanco", 50, 30, "dextrosa");

		// Inicializar el array "pedido" con los valores dados en el enunciado
		Vendible[] pedido = { loteria, horchata, cucurucho1, cucurucho2 };

		// Imprimir el pedido
		verPedido(pedido);

		// Calcular y mostrar el precio total del pedido
		double precioTotal = 0;
		double precioSaboresCucuruchos = cucurucho1.calcularPrecioTotal() + cucurucho2.calcularPrecioTotal();

		precioTotal += precioSaboresCucuruchos;
		for (Vendible vendible : pedido) {
			if (vendible instanceof Cucurucho) {
				continue;
			}
			precioTotal += vendible.getPrecio();
		}

//		Precio total del pedido
		System.out.println("Precio total del pedido: " + precioTotal + "e");

//		Añadir bolas de helado
		System.out.println("\nFalta por escoger bolas de helado.");

		cucurucho1.addBolaHelado(sabor1);
		cucurucho2.addBolaHelado(sabor2);

//		Menu de modificaciones
		cambiarMenuHorchata(pedido);
	}

	public static void cambiarMenuHorchata(Vendible[] pedido) {
		int salir = 1;

		do {
			System.out.println("------- Menu -------");
			System.out.println("1. Ver pedido actual");
			System.out.println("2. Sustituir elemento del pedido por horchata");
			System.out.println("0. Salir");

			int opcion = Utilidades.pedirEntero("Introduce la opcion deseada: ");

			switch (opcion) {
			case 1:
				verPedido(pedido);
				break;
			case 2:
				modificarPedido(pedido);
				break;
			case 0:
				salir = 0;
				System.out.println("Hasta luego!");
				break;
			default:
				System.out.println("Opcion incorrecta. Prueba otra vez.");
				break;
			}

		} while (salir != 0);
	}

	public static void verPedido(Vendible[] pedido) {
		System.out.println("Pedido disponible:");
		for (Vendible vendible : pedido) {
			System.out.println(vendible.toString());
		}
	}

	public static String leerCadena(String pregunta) {
		System.out.println(pregunta);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cadena = null;
		try {
			cadena = br.readLine();
		} catch (IOException e) {
			System.out.println("Error al leer la entrada del usuario: " + e.getMessage());
		}
		return cadena;
	}

	public static int elegirPedido(Vendible[] pedido) {
		for (int i = 0; i < pedido.length; i++) {
			System.out.println(i + "- " + pedido[i]);
		}
		int numPedidoElegido = Utilidades.pedirEntero("Introduce la posicion del pedido a modificar: ");

		while (numPedidoElegido >= pedido.length || numPedidoElegido < 0) {
			System.out.println("Error. Numero de posicion no valida");
			numPedidoElegido = Utilidades.pedirEntero("Introduce la posicion del pedido a modificar: ");
		}

		return numPedidoElegido;

	}

//	Creamos una nueva horchata y la sustituimos en la posicion del pedido seleccionada por el usuario
	public static void modificarPedido(Vendible[] pedido) {
		int index = elegirPedido(pedido);
		Horchata horchata = new Horchata(0, "", 0, 0, 0);
		System.out.println("Introduce datos de la horchata");
		horchata.setNombre(leerCadena("Nombre: "));
		horchata.setPrecio(Utilidades.pedirDecimal("Precio: "));
		horchata.setNumKcal(Utilidades.pedirDecimal("Num Kcal: "));
		horchata.setCantidad(Utilidades.pedirEntero("Cantidad de horchata: "));
		horchata.setPorcentChufa(Utilidades.pedirEntero("Porcentaje de chufa: "));
		pedido[index] = horchata;

	}

}
