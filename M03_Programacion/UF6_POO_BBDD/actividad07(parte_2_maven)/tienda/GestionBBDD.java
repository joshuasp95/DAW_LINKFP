package actividad07.tienda;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import actividad07.exceptions.ErrorCadena;
import actividad07.exceptions.ErrorEmpleado;
import actividad07.utils.Utilidades;

public class GestionBBDD {

	// esta clase es responsable de crear instancias de EntityManager,
	// que son objetos que manejan la persistencia de entidades JPA
	private EntityManagerFactory emf;
	// se utiliza para interactuar con la base de datos, como persistir entidades o
	// ejecutar consultas
	private EntityManager em;

	public GestionBBDD() {
		super();
		// Crear un EntityManagerFactory y un EntityManager
		emf = Persistence.createEntityManagerFactory("objectdb:db/tienda.tmp;drop");
		em = emf.createEntityManager();

		// Creamos varios objetos Empleado
		Empleado empleado1 = new Empleado("Juan", "Perez");
		Empleado empleado2 = new Empleado("Maria", "Garcia");
		Empleado empleado3 = new Empleado("Pedro", "Lopez");
		Empleado empleado4 = new Empleado("Laura", "Martinez");
		Empleado empleado5 = new Empleado("Miguel", "Rodriguez");

		// Creamos objetos Tienda y les asignamos los empleados creados
		Tienda tienda1 = new Tienda("Calle Principal 123", 100);
		tienda1.addEmpleado(empleado1);
		tienda1.addEmpleado(empleado2);

		Tienda tienda2 = new Tienda("Avenida Secundaria 456", 200);
		tienda2.addEmpleado(empleado3);

		// Creamos otro objeto Tienda con empleados diferentes
		Tienda tienda3 = new Tienda("Plaza Central 789", 150);
		tienda3.addEmpleado(empleado4);

		Tienda tienda4 = new Tienda("Calle Lateral 999", 300);
		tienda4.addEmpleado(empleado5);

		// Las operaciones que modifican el contenido de las bbdd requieren de una
		// transaccion
		// activa, con persist podemos almacenar objetos en la bbdd
		em.getTransaction().begin();
		em.persist(tienda1);
		em.persist(tienda2);
		em.persist(tienda3);
		em.persist(tienda4);
		em.getTransaction().commit();
	}

	// Método para mostrar todos los empleados
	public void mostrarEmpleados() {
		System.out.println("Mostrando todos los empleados:");

		// Crear consulta para obtener todos los empleados
		TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e", Empleado.class);

		// Obtener lista de empleados a partir de la consulta
		List<Empleado> empleados = query.getResultList();

		// Iterar sobre cada empleado y mostrar sus datos
		for (Empleado empleado : empleados) {
			System.out.println(empleado);
		}
		System.out.println("-------------------------");
	}

	// Método para mostrar todas las tiendas
	public void mostrarTiendas() {
		System.out.println("Mostrando todas las tiendas:");

		// Crear consulta para obtener todas las tiendas
		TypedQuery<Tienda> query = em.createQuery("SELECT t FROM Tienda t", Tienda.class);

		// Obtener lista de tiendas a partir de la consulta
		List<Tienda> tiendas = query.getResultList();

		for (Tienda tienda : tiendas) {
			System.out.println(tienda);
		}
		System.out.println("-------------------------");
	}

	// Método para mostrar tiendas ordenadas por ventas
	public void mostrarTiendasOrdVentas() {
		int opcion;

		// Imprimir mensaje inicial
		System.out.println("Mostrando tiendas ordenadas por ventas");
		System.out.println("Elige el orden de las tiendas: ");
		System.out.println("1- Ascendente");
		System.out.print("2- Descendente");

		// Pedir al usuario la opción de orden
		opcion = Utilidades.pedirEntero("");

		if (opcion == 1) {
			// Crear consulta para obtener tiendas ordenadas por ventas ascendente
			TypedQuery<Tienda> query = em.createQuery("SELECT t FROM Tienda t ORDER BY t.numVentas ASC", Tienda.class);
			List<Tienda> tiendas = query.getResultList();

			for (Tienda tienda : tiendas) {
				System.out.println(tienda);
			}
		} else if (opcion == 2) { // Si el usuario eligió orden descendente
			TypedQuery<Tienda> query = em.createQuery("SELECT t FROM Tienda t ORDER BY t.numVentas DESC", Tienda.class);

			// Obtener lista de tiendas a partir de la consulta
			List<Tienda> tiendas = query.getResultList();

			for (Tienda tienda : tiendas) {
				System.out.println(tienda);
			}
		} else {
			System.out.println("Opcion no valida");
		}

		System.out.println("-------------------------");
	}

	// Modificamos el empleado controlando los errores
	public void modificarEmpleado() throws ErrorEmpleado, ErrorCadena {
		// Declaramos las variables
		int idEmpleado;
		int opcion;
		boolean comprobarID = false;
		// Mostramos los empleados disponibles
		System.out.println("Metodo para modificar un empleado");
		System.out.println("Empleados disponibles");
		mostrarEmpleados();

		// Pedimos al usuario que elija un ID del empleado
		idEmpleado = Utilidades.pedirEntero("Introduce el ID del empleado: ");

		TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
		List<Empleado> empleados = query.getResultList();

		// Iniciamos la transaccion para poder modificar elementos de la bbdd
		em.getTransaction().begin();
		for (Empleado empleado : empleados) {
			if (empleado.getID() == idEmpleado) {
				comprobarID = true;
				System.out.println("1- Nombre");
				System.out.println("2- Apellidos");
				System.out.println("0- Ninguno");
				opcion = Utilidades.pedirEntero("Introduce numero de atributo a modificar: ");
				if (opcion == 1) {
					empleado.setNombre(Utilidades.leerCadena("Introduce el nuevo nombre del empleado: "));
				} else if (opcion == 2) {
					empleado.setApellidos(Utilidades.leerCadena("Introduce los nuevos apellidos del empleado: "));
				} else if (opcion == 0) {
					System.out.println("No se ha modificado al empleado");
				} else {
					System.out.println("Opcion incorrecta");
				}
			}
		}
		// autorizamos la transaccion con commit
		em.getTransaction().commit();
		if (!comprobarID) {
			throw new ErrorEmpleado("Error: El ID introcido no existe");
		}

	}

	// Creamos una tienda
	public void crearTienda() throws ErrorCadena, ErrorEmpleado {
		// Declaramos las variables, una nueva tienda y un empleado
		int seguir = 1;
		Tienda nuevaTienda = new Tienda(null, 0);
		Empleado nuevoEmpleado;

		// Requerimos al usuario que introduzca los datos
		System.out.println("Metodo para crear una tienda");
		nuevaTienda.setDireccion(Utilidades.leerCadena("Introducir la direccion de la nueva tienda: "));
		nuevaTienda.setNumVentas(Utilidades.pedirEntero("Introduce el numero de ventas de la nueva tienda: "));

		// Bucle para añadir lista de empleados a la tienda
		do {
			System.out.println("Introduce empleados a tu nueva tienda");
			nuevoEmpleado = añadirEmpleadoTienda(nuevaTienda);
			nuevaTienda.addEmpleado(nuevoEmpleado);
			seguir = Utilidades.pedirEntero("Para salir introduce un 0");
		} while (seguir != 0);

		// Creamos la transaccion para introducir la nueva tienda en la bbdd
		em.getTransaction().begin();
		em.persist(nuevaTienda);
		em.getTransaction().commit();

	}

	// Metodo para añadir empleados a la tienda, puede ser un empleado existente o
	// un nuevo empleado
	// se devuelve y se añade en el metodo crear tienda
	public Empleado añadirEmpleadoTienda(Tienda tienda) throws ErrorCadena, ErrorEmpleado {
		int opcion;
		int idEmpleado;
		boolean comprobarID = false;
		Empleado nuevoEmpleado = new Empleado(null, null);

		System.out.println("Que quieres hacer?");
		System.out.println("1- Crear nuevo empleado");
		System.out.println("2- User ID empleado existente");
		System.out.println("0- No insertar empleados");
		opcion = Utilidades.pedirEntero("Introduce la opcion elegida: ");
		if (opcion == 1) {
			nuevoEmpleado.setNombre(Utilidades.leerCadena("Introduce el nombre del empleado"));
			nuevoEmpleado.setApellidos(Utilidades.leerCadena("Introduce los apellidos del empleado"));
			return nuevoEmpleado;

		} else if (opcion == 2) {
			mostrarEmpleados();
			idEmpleado = Utilidades.pedirEntero("Introduce el ID del empleado: ");

			TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
			List<Empleado> empleados = query.getResultList();

			for (Empleado empleado : empleados) {
				if (empleado.getID() == idEmpleado) {
					nuevoEmpleado = empleado;
					return nuevoEmpleado;
				}
			}
			if (!comprobarID) {
				throw new ErrorEmpleado("Error: El ID introcido no existe");
			}

		} else if (opcion == 0) {
			System.out.println("No se ha insertado ningun empleado");
		} else {
			System.out.println("Opcion no valida");
		}
		return nuevoEmpleado;

	}

	public void cerrarConexion() {
		// Cerrar el EntityManager y EntityManagerFactory si están abiertos
		if (this.em != null && this.em.isOpen()) {
			this.em.close();
		}
		if (this.emf != null && this.emf.isOpen()) {
			this.emf.close();
		}
	}
}
