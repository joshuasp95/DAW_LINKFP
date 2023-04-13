package actividad05.ejercicio01.cuentaCorriente;

public class TestCuenta {

//	Creamos un array de clase que contendra las cuentas 
//	El numero de elementos del array se puede variar 
	static Cuenta cuentas[] = new Cuenta[2];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Creamos las cuentas
		Cuenta cuenta1 = new Cuenta("AAA1", 15849.47, "Inyaki Urdangarin");
		Cuenta cuenta2 = new Cuenta("BBB2", 5485.567, "Jano Garcia");

//		Los introducimos dentro del array de clase
		cuentas[0] = cuenta1;
		cuentas[1] = cuenta2;

//		Otras Variables
		int opcion = 0;
//		Declaramos una variable de tipo cuenta que sera la que elija el usuario
		Cuenta cuentaElegida;

		do {
			System.out.println("***********************************************");
			System.out.println("--- MENU PRINCIPAL ---");
			System.out.println("1. Consultar saldo");
			System.out.println("2. Ingresar dinero");
			System.out.println("3. Sacar dinero");
			System.out.println("4. Realizar transferencia");
			System.out.println("5. Salir");

			opcion = Utilidades.pedirEntero("Elige una opcion: ");

			switch (opcion) {

			case 1: // Consultar saldo
				cuentaElegida = elegirCuenta(cuentas);
				consultarSaldo(cuentaElegida);
				break;

			case 2: // Ingresar dinero
				cuentaElegida = elegirCuenta(cuentas);
				ingresarDinero(cuentaElegida);
				break;

			case 3: // Sacar dinero
				cuentaElegida = elegirCuenta(cuentas);
				sacarDinero(cuentaElegida);
				break;

			case 4: // Realizar transferencia
				cuentaElegida = elegirCuenta(cuentas);
				realizarTransferencia(cuentaElegida);
				break;

			case 5: // Salir
				System.out.println("Saliendo del programa. Adios!");
				break;

			default:
				System.out.println("Opcion no valida, vuelve a intentarlo");
				break;
			}

		} while (opcion != 5);

	}

//	Funcion para elegir cuenta, se controla que la posicion elegida sea valida dentro del array
	public static Cuenta elegirCuenta(Cuenta cuentas[]) {
		System.out.println("Cuentas dispobibles: ");
		for (int i = 0; i < cuentas.length; i++) {
			System.out.println((i + 1) + "- " + cuentas[i].getNumCuenta());
		}
		int numCuentaElegida = Utilidades
				.pedirEntero("Introduce el numero de " + "la cuenta sobre la que quieres operar: ");

		while (numCuentaElegida > cuentas.length || numCuentaElegida < 1) {
			System.out.println("Error. Numero de cuenta no valido");
			numCuentaElegida = Utilidades
					.pedirEntero("Introduce el numero de " + "la cuenta sobre la que quieres operar: ");
		}
//	Para facilitar la legibilidad al usuario se ha modificado la salida por pantalla para que en 
//	lugar de mostrar las posiciones desde 0 a final empiece en 1 a final
		return cuentas[numCuentaElegida - 1];
	}

	public static void consultarSaldo(Cuenta cuenta) {
		System.out.println("***********************************************");
//		Accedemos al getter de la clase para obtener el valor del numero de cuenta y el saldo
		String numCuenta = cuenta.getNumCuenta();

		System.out.println("Saldo de cuenta " + numCuenta + ": " + "" + "" + cuenta.getSaldo());

	}

	public static void ingresarDinero(Cuenta cuenta) {
		System.out.println("***********************************************");

		double dineroIngresar = Utilidades.pedirDecimal("Introduce la cantidad de dinero a ingresar: ");

		double dineroCuentaAntes = cuenta.getSaldo();

		double dineroCuentaDespues = dineroIngresar + dineroCuentaAntes;

		cuenta.setSaldo(dineroCuentaDespues);

	}

//	Para la transferencia voy a usar el mismo metodo pero pasandole la cantidad retirada de 
//	la otra cuenta, con polimorfismo puedo usar una funcion con el mismo nombre pero distintos parametros
	public static void ingresarDinero(Cuenta cuenta, double dineroIngresar) {
		System.out.println("***********************************************");

		double dineroCuentaAntes = cuenta.getSaldo();

		double dineroCuentaDespues = dineroIngresar + dineroCuentaAntes;

		cuenta.setSaldo(dineroCuentaDespues);

	}

	public static void sacarDinero(Cuenta cuenta) {

		double dineroRetirar = Utilidades.pedirDecimal("Introduce la cantidad de dinero a retirar: ");

		double dineroCuentaAntes = cuenta.getSaldo();

		double dineroCuentaDespues = dineroCuentaAntes - dineroRetirar;

//		Mientras haya dinero en la cuenta se va actualizar el saldo disponible
//		si no hay dinero (<0), no se actualiza y se muestra el saldo por pantalla
		if (dineroCuentaDespues < 0) {
			System.out.println("Error: No se puede realizar la operacion.");
			System.out.println("El dinero a retirar supera la cantidad que hay en la cuenta");
			consultarSaldo(cuenta);

		} else {
			System.out.println("Operacion realizada correctamente.");
			cuenta.setSaldo(dineroCuentaDespues);
			consultarSaldo(cuenta);
		}

	}

	public static void realizarTransferencia(Cuenta cuenta) {
		Cuenta cuentaTransferencia;

		double dineroRetirar = Utilidades.pedirDecimal("Introduce la cantidad a transferir: ");
		double dineroDisponible = cuenta.getSaldo();

//		Dara un error si se quiere retirar mas dinero del disponible
		if (dineroRetirar > dineroDisponible) {
			System.out.println("Error: No se puede realizar la operacion.");
			System.out.println("El dinero a retirar supera la cantidad que hay en la cuenta");
			consultarSaldo(cuenta);
		} else {
//			Actualizamos el dinero en la cuenta original
			double dineroCuentaDespues = dineroDisponible - dineroRetirar;
			cuenta.setSaldo(dineroCuentaDespues);

//			Transferimos el dinero a una cuenta que elija el usuario
			double dineroTransferir = dineroRetirar;
			System.out.println("Operacion de transferencia autorizada.");
			System.out.println("Ahora elige la cuenta a la que vas a transfeir el dinero");
			cuentaTransferencia = elegirCuenta(cuentas);
			ingresarDinero(cuentaTransferencia, dineroTransferir);
			System.out.println("Transferencia realizada");
		}

	}

}
