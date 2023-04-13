package actividad05.ejercicio02.heladeria;

import java.util.Arrays;

import actividad05.ejercicio01.cuentaCorriente.Utilidades;

public class Cucurucho extends Comida {

	private int numBolas;
	private SaborHelado[] saboresHelado = new SaborHelado[numBolas];

	public Cucurucho(double precioBase, String nombre, double numKcal, SaborHelado[] saboresHelado, int numBolas) {
		super(precioBase, nombre, numKcal, "cucurucho");
		this.saboresHelado = saboresHelado;
		this.numBolas = numBolas;
	}

	public SaborHelado[] getSaboresHelado() {
		return saboresHelado;
	}

	public int getNumBolas() {
		return numBolas;
	}

	public void setSaboresHelado(SaborHelado[] saboresHelado) {
		this.saboresHelado = saboresHelado;
	}

	public void setNumBolas(int numBolas) {
		this.numBolas = numBolas;
	}

	@Override
	public String toString() {
		return "Cucurucho [Tipo= " + tipoClase + ", numKcal=" + getNumKcal() + ", numBolas=" + numBolas + ", Precio="
				+ getPrecio() + "e, Nombre=" + getNombre() + ", saboresHelado=" + Arrays.toString(saboresHelado) + "]";
	}

//	NO SE UTILIZA
	public SaborHelado elegirSaborDisponible() {
		for (int i = 0; i < this.saboresHelado.length; i++) {
			System.out.println(i + "- " + saboresHelado[i]);
		}
		int numHeladoElegido = Utilidades
				.pedirEntero("Introduce el numero del sabor de helado que deseas añadir a tu cucurucho: ");

		while (numHeladoElegido >= this.saboresHelado.length || numHeladoElegido < 0) {
			System.out.println("Error. Numero de helado no valido");
			numHeladoElegido = Utilidades
					.pedirEntero("Introduce el numero del sabor de helado que deseas añadir a tu cucurucho: ");
		}

		return this.saboresHelado[numHeladoElegido];
	}

//	Metodo para añadir bola de helado
	public void addBolaHelado(SaborHelado saborBolaHelado) {
//		Declaramos las variables
		int seguir = 1;
//		Ya se pasa como parametro un objeto de tipo SaborHelado para añadir
		SaborHelado saborElegido = saborBolaHelado;
		int numBolasAñadidas = 0;

//		System.out.println("Elige uno de los siguientes sabores disponibles:");
//		saborElegido = elegirSaborDisponible();

//		Se va a ejecutar el bucle mientras no el usuario no introduzca un 0
//		o el numero de bolas añadidas sea menor o igual num de bolas maximas que
//		puede almacenar este cucurucho
		while (seguir != 0 && numBolasAñadidas <= this.numBolas) {
			System.out.println("En que posicion del helado deseas meter la bola de helado?");
			System.out.println("Tienes las siguientes posiciones disponibles: ");
			// Mostramos todas posiciones del array de saboresHelado que su nombre este
			// vacio o null
			for (int i = 0; i < this.saboresHelado.length; i++) {
				if (this.saboresHelado[i].getNombre() == null) {
					System.out.print("[" + i + "]");
				}
			}
			System.out.println("");
			// Pedimos al usuario que elija una posicion entre las disponibles
			int posElegida = Utilidades.pedirEntero("Introduce la posicion: ");

			// Validamos la opcion elegida este disponible, dentro del rango correcto
			while (posElegida >= this.saboresHelado.length || posElegida < 0
					|| this.saboresHelado[posElegida].getNombre() != null) {
				System.out.println("Error. Posicion no valida.");
				posElegida = Utilidades.pedirEntero("Introduce la posicion: ");
				if (this.saboresHelado.length == this.numBolas) {
					break;
				}
			}
			// Añadimos la bola en la posicion elegida
			this.saboresHelado[posElegida] = saborElegido;
			numBolasAñadidas += 1;

			seguir = Utilidades.pedirEntero("Para salir introduce un 0: ");
		}

	}

	public double calcularPrecioTotal() {
		double precioTotal = 0 + this.getPrecio();
		for (int i = 0; i < this.saboresHelado.length; i++) {
			if (this.saboresHelado[i] != null) {
				precioTotal += this.saboresHelado[i].getPrecio();
			}
		}
		return precioTotal;
	}

}
