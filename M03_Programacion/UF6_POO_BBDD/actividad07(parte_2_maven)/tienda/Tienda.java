package actividad07.tienda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tienda {

	@Id
	@GeneratedValue
	private long id;
	private String direccion;
	private int numVentas;
	// Indica que hay una relación uno-a-muchos con la entidad 'Empleado'
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tienda", orphanRemoval = true)
	private List<Empleado> listaEmpleados = new ArrayList<Empleado>();

	public Tienda(String direccion, int numVentas) {
		super();
		this.direccion = direccion;
		this.numVentas = numVentas;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getNumVentas() {
		return numVentas;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setNumVentas(int numVentas) {
		this.numVentas = numVentas;
	}

	// Añade un empleado a la lista de empleados de la tienda
	public void addEmpleado(Empleado empleado) {
		empleado.setTienda(this); // Establece la tienda de este empleado como la tienda actual
		this.listaEmpleados.add(empleado); // Añade el empleado a la lista de empleados de la tienda
	}

	@Override
	public String toString() {
		// Se crea un StringBuilder para construir la cadena de texto a retornar
		StringBuilder sb = new StringBuilder();
		// Se agregan los atributos de la Tienda a la cadena
		sb.append("Tienda [id=").append(id).append(", direccion=").append(direccion).append(", numVentas=")
				.append(numVentas).append(", Empleados=\n");
		// Se itera sobre la lista de empleados de la tienda utilizando un ciclo
		// for-each
		for (Empleado empleado : listaEmpleados) {
			// Se agrega la representación en forma de cadena de texto de cada objeto
			// Empleado a la cadena, utilizando su método toString()
			sb.append("\t").append(empleado.toString()).append("\n");
		}
		// Se cierra el StringBuilder y se retorna la cadena resultante
		sb.append("]");
		return sb.toString();
	}

}
