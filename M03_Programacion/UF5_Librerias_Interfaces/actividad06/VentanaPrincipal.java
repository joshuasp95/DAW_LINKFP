package actividad06;

import java.util.HashMap;

public class VentanaPrincipal {

	public static HashMap<String, Empleado> mapEmpleados;

	public static void main(String[] args) {

		// Crear los objetos de Empleado
		Empleado dwight = new Empleado("Dwight", "Schrute", 40000.00);
		Empleado jim = new Empleado("Jim", "Halpert", 55000.00);
		Empleado pam = new Empleado("Pam", "Beesly", 45000.00);
		Empleado michael = new Empleado("Michael", "Scott", 75000.00);
		Empleado stanley = new Empleado("Stanley", "Hudson", 50000.00);

		mapEmpleados = new HashMap<String, Empleado>();

		mapEmpleados.put("Dwight", dwight);
		mapEmpleados.put("Jim", jim);
		mapEmpleados.put("Pam", pam);
		mapEmpleados.put("Michael", michael);
		mapEmpleados.put("Stanley", stanley);

		VentanaBienvenida.escribirMapArchivo(mapEmpleados);

		VentanaBienvenida V = new VentanaBienvenida(); // creamos una ventana
		V.setVisible(true); // hacemos visible la ventana creada
	}

}
