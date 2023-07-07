package actividad07.libreria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import actividad07.exceptions.ErrorAutor;
import actividad07.exceptions.ErrorCadena;
import actividad07.exceptions.ErrorLibro;
import actividad07.utils.Utilidades;

public class GestionBBDD {

	// Declaramos las variables, en este caso seran los datos necesarios
	// para crear una conexion a la BBDD
	private static final String DATOSCONEXION = "jdbc:mysql://localhost/";
	private static final String DBNAME = "daw_m03_act_07";
	private static final String USER = "joshua";
	private static final String PASS = "root";
	// Creamos la variable de conexion de manera estatica para solo conectarse una
	// vez y usar esa variable para el resto de metodos que la necesiten
	private static Connection con;

	// Creamos una variable de clase del tipo de la clase para que siguiendo el
	// patron
	// Singleton solo se genere una unica vez el constructor de la clase GestionBBDD
	private static GestionBBDD instancia = null;

	// El constructor de clase encapsulado y genera la estructura y valores de la
	// bbdd predeterminados
	private GestionBBDD() throws SQLException {
		obtenerConexion();
		borrarBBDD();
		crearBBDD();
		crearTablas();
		añadirLibros();
		añadirAutores();
		añadirAutor_Libro();
	}

	// Unicamente permitimos a otras clases obtener una instancia de esta clase
	public static GestionBBDD obtenerInstancia() throws SQLException {
		if (instancia == null) {
			instancia = new GestionBBDD();
		}
		return instancia;

	}

	// Creamos la conexion al SGBD de MySQL y la retornamos en caso de necesitar
	// utilizarse posteriormente por otras funciones
	private static synchronized Connection obtenerConexion() throws SQLException {

		if (con == null || con.isClosed()) {
			con = DriverManager.getConnection(DATOSCONEXION + "?useSSL=true", USER, PASS);
		}

		return con;
	}

	// Elimina la base de datos si existe
	private void borrarBBDD() throws SQLException {
		// Se define el query para eliminar la base de datos
		String query = "drop database if exists " + DBNAME;
		Statement st = null;

		// Se crea un Statement y se ejecuta el query
		st = con.createStatement();
		st.execute(query);

		// Se cierra el Statement
		st.close();
	}

	// Crea la base de datos si no existe
	private void crearBBDD() throws SQLException {
		// Se define el query para crear la base de datos si no existe
		String query = "create database if not exists " + DBNAME;
		Statement st = null;

		// Se crea un Statement y se ejecuta el query
		st = con.createStatement();
		st.execute(query);

		// Se obtiene la conexión a la base de datos recién creada
		con = DriverManager.getConnection(DATOSCONEXION + DBNAME + "?useSSL=true", USER, PASS);

		// Se cierra el Statement
		st.close();
	}

	// Crea las tablas necesarias en la base de datos
	private void crearTablas() throws SQLException {
		// Se definen los queries para crear cada una de las tablas
		String queryLibros = "CREATE TABLE libros (\r\n" + "    id_libro INT NOT NULL AUTO_INCREMENT,\r\n"
				+ "    titulo VARCHAR(60) NOT NULL,\r\n" + "    pais VARCHAR(40),\r\n" + "    paginas SMALLINT,\r\n"
				+ "    genero VARCHAR(40),\r\n" + "    PRIMARY KEY (id_libro)\r\n" + ");";
		String queryAutor = "CREATE TABLE autores (\r\n" + "    id_autor INT NOT NULL AUTO_INCREMENT,\r\n"
				+ "    nombre VARCHAR(255) NOT NULL,\r\n" + "    apellido VARCHAR(255) NOT NULL,\r\n"
				+ "    PRIMARY KEY (id_autor)\r\n" + ");";
		String queryLibro_Autor = "CREATE TABLE libro_autor (\r\n" + "    id_libro INT NOT NULL,\r\n"
				+ "    id_autor INT NOT NULL,\r\n" + "    PRIMARY KEY (id_libro , id_autor),\r\n"
				+ "    FOREIGN KEY (id_libro)\r\n" + "        REFERENCES libros (id_libro)\r\n"
				+ "        ON DELETE CASCADE\r\n" + "        ON UPDATE CASCADE,\r\n" + "    FOREIGN KEY (id_autor)\r\n"
				+ "        REFERENCES autores (id_autor)\r\n" + "        ON DELETE CASCADE\r\n"
				+ "        ON UPDATE CASCADE\r\n" + ");";

		Statement st = null;

		// Se crea un Statement y se ejecutan los queries
		st = con.createStatement();
		st.execute(queryLibros);
		st.execute(queryAutor);
		st.execute(queryLibro_Autor);

		// Se cierra el Statement
		st.close();
	}

	// Método para agregar libros a la tabla 'libros'
	private void añadirLibros() throws SQLException {
		String[] queries = {
				"INSERT INTO libros (titulo, pais, paginas, genero) VALUES ('El Quijote', 'Espana', 863, 'Novela')",
				"INSERT INTO libros (titulo, pais, paginas, genero) VALUES ('Cuentos completos', 'Argentina', 400, 'Cuento')",
				"INSERT INTO libros (titulo, pais, paginas, genero) VALUES ('La muerte en Venecia', 'Alemania', 74, 'Novela')",
				"INSERT INTO libros (titulo, pais, paginas, genero) VALUES ('Cien anos de soledad', 'Colombia', 471, 'Novela')",
				"INSERT INTO libros (titulo, pais, paginas, genero) VALUES ('El amor en los tiempos del colera', 'Colombia', 350, 'Novela')" };

		Statement st = null;

		st = con.createStatement();

		// Ejecuta las consultas de inserción de libros
		for (String string : queries) {
			st.executeUpdate(string);
		}

		st.close();

	}

	// Método para agregar autores a la tabla 'autores'
	private void añadirAutores() throws SQLException {
		String[] queries = { "INSERT INTO autores (nombre, apellido) VALUES ('Miguel', 'de Cervantes');",
				"INSERT INTO autores (nombre, apellido) VALUES ('Julio', 'Cortazar');",
				"INSERT INTO autores (nombre, apellido) VALUES ('Jorge Luis', 'Borges');",
				"INSERT INTO autores (nombre, apellido) VALUES ('Thomas', 'Mann');",
				"INSERT INTO autores (nombre, apellido) VALUES ('Gabriel', 'Garcia Marquez');" };

		Statement st = null;

		st = con.createStatement();

		// Ejecuta las consultas de inserción de autores
		for (String string : queries) {
			st.executeUpdate(string);
		}

		st.close();

	}

	// Añadimos los registros que relacionan los libros y autores entre si,
	// se ha diseñado la bbdd tal que 1 libro puede haber sido escrito por más de un
	// autor y 1 autor puede escribir más de un libro
	private void añadirAutor_Libro() throws SQLException {
		String[] queries = { "INSERT INTO libro_autor (id_libro, id_autor) VALUES (1, 1);",
				"INSERT INTO libro_autor (id_libro, id_autor) VALUES (2, 2), (2, 3);",
				"INSERT INTO libro_autor (id_libro, id_autor) VALUES (3, 4);",
				"INSERT INTO libro_autor (id_libro, id_autor) VALUES (4, 5), (5, 5);"

		};

		Statement st = null;

		st = con.createStatement();

		for (String string : queries) {
			st.executeUpdate(string);
		}

		st.close();

	}

	// Creamos el primer metodo del menu que consistira en volver a establecer
	// los valores por defecto de la BBDD como en el constructor
	public void restablecerBBDD() throws SQLException {
		obtenerConexion();
		borrarBBDD();
		crearBBDD();
		crearTablas();
		añadirLibros();
		añadirAutores();
		añadirAutor_Libro();

	}

	// Metodo para mostrar autores disponibles
	public ArrayList<String> mostrarAutores() throws SQLException {

		ArrayList<String> listaAutores = new ArrayList<String>();

		String query = "select * from autores;";

		Statement st = null;
		ResultSet rs = null;

		st = con.createStatement();
		rs = st.executeQuery(query);

		while (rs.next()) {
			listaAutores.add("ID:" + rs.getInt(1) + "; Nombre:" + rs.getString(2) + "; Apellido:" + rs.getString(3));
		}
		for (String string : listaAutores) {
			System.out.println(string);
		}
		rs.close();
		st.close();

		return listaAutores;

	}

	// Metodo para mostrar autores disponibles
	public ArrayList<String> mostrarLibros() throws SQLException {

		ArrayList<String> listaLibros = new ArrayList<String>();

		String query = "select * from libros;";

		Statement st = null;
		ResultSet rs = null;

		st = con.createStatement();
		rs = st.executeQuery(query);

		while (rs.next()) {
			listaLibros.add("ID:" + rs.getInt(1) + "; Titulo:" + rs.getString(2) + "; Pais:" + rs.getString(3)
					+ "; Paginas: " + rs.getString(4) + "; Genero: " + rs.getString(5));
		}
		for (String string : listaLibros) {
			System.out.println(string);
		}
		rs.close();
		st.close();

		return listaLibros;

	}

	public void modificarAutor() throws SQLException, ErrorAutor, ErrorCadena {
		// Se crea un objeto Scanner que leerá la entrada del usuario desde la consola
		Scanner sc = new Scanner(System.in);

		// Se muestra una lista de autores disponibles para que el usuario seleccione el
		// autor que desea modificar
		mostrarAutores();
		Integer autorID = null;
		PreparedStatement ps = null;

		// Se define una consulta SQL para actualizar el nombre y el apellido de un
		// autor en la base de datos
		String query = "update autores set nombre=?, apellido=? where id_autor=?";

		// Se pide al usuario que introduzca el ID del autor que desea modificar
		autorID = Utilidades.pedirEntero("Introduce el ID del autor");

		// Si se encuentra un autor con el ID proporcionado por el usuario, se procede a
		// actualizar su información
		if (comprobarAutorConID(autorID)) {
			// Se prepara la consulta SQL con los valores proporcionados por el usuario
			ps = con.prepareStatement(query);
			ps.setString(1, Utilidades.leerCadena("Introduce el nombre del autor: "));
			ps.setString(2, Utilidades.leerCadena("Introduce el apellido del autor: "));
			ps.setInt(3, autorID);

			// Se ejecuta la consulta SQL para actualizar la información del autor en la
			// base de datos
			ps.executeUpdate();

			ps.close();

		} else {
			// Si no se encuentra un autor con el ID proporcionado por el usuario, se lanza
			// una excepción personalizada
			throw new ErrorAutor("Error: No se ha encontrado ningun autor con ese ID");
		}
	}

	// Función para comprobar si existe un autor con un determinado ID
	private boolean comprobarAutorConID(Integer autorID) throws SQLException {
		Statement st = null;
		ResultSet rs = null;

		// Se define una consulta SQL para buscar todos los autores en la base de datos
		String query = "select * from autores;";

		// Se ejecuta la consulta SQL y se recorre el resultado en busca del autor con
		// el ID proporcionado
		st = con.createStatement();
		rs = st.executeQuery(query);

		while (rs.next()) {
			if (autorID == rs.getInt(1)) {
				// Si se encuentra un autor con el ID proporcionado, se devuelve true
				return true;
			}
		}

		// Si no se encuentra un autor con el ID proporcionado, se devuelve false
		return false;
	}

	public void eliminarLibro() throws SQLException, ErrorLibro {
		// Se muestra una lista de libros disponibles para que el usuario seleccione el
		// libro que desea eliminar
		mostrarLibros();
		Integer libroID = null;
		PreparedStatement ps = null;

		// Se definen tres consultas SQL: una para eliminar registros de la tabla
		// libro_autor, otra para eliminar registros de la tabla autores y una tercera
		// para eliminar registros de la tabla libros
		String queryLibro_Autor = "delete from libro_autor where id_libro=?";
		String queryAutor = "delete from autores where id_autor not in (select distinct id_autor from libro_autor)";
		String queryLibro = "delete from libros where id_libro=?";

		// Se pide al usuario que introduzca el ID del libro que desea eliminar
		libroID = Utilidades.pedirEntero("Introduce el ID del libro que quieres eliminar: ");

		// Si se encuentra un libro con el ID proporcionado por el usuario, se procede a
		// eliminarlo de la base de datos
		if (comprobarLibroconID(libroID)) {
			// Se preparan y ejecutan las consultas SQL para eliminar los registros
			// correspondientes a este libro
			ps = con.prepareStatement(queryLibro_Autor);
			ps.setInt(1, libroID);
			ps.executeUpdate();
			ps.close();

			ps = con.prepareStatement(queryAutor);
			ps.executeUpdate();
			ps.close();

			ps = con.prepareStatement(queryLibro);
			ps.setInt(1, libroID);
			ps.executeUpdate();
			ps.close();

		} else {
			// Si no se encuentra un libro con el ID proporcionado por el usuario, se lanza
			// una excepción personalizada
			throw new ErrorLibro("Error: ID de libro no disponible");
		}
	}

	// Función auxiliar para comprobar si existe un libro con un determinado ID
	private boolean comprobarLibroconID(Integer libroID) throws SQLException {
		Statement st = null;
		ResultSet rs = null;

		// Se define una consulta SQL para buscar todos los libros en la base de datos
		String query = "select * from libros";

		// Se ejecuta la consulta SQL y se recorre el resultado en busca del libro con
		// el ID proporcionado
		st = con.createStatement();
		rs = st.executeQuery(query);

		while (rs.next()) {
			if (libroID == rs.getInt(1)) {
				// Si se encuentra un libro con el ID proporcionado, se devuelve true
				return true;
			}
		}

		// Si no se encuentra un libro con el ID proporcionado, se devuelve false
		return false;
	}

}
