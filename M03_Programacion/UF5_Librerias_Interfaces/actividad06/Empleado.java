package actividad06;

import java.io.Serializable;

public class Empleado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	private double sueldo;
	private transient String clave = "patata";

	public Empleado(String nombre, String apellido, double sueldo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.sueldo = sueldo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public double getSueldo() {
		return sueldo;
	}

	public String getClave() {
		return this.clave;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", apellido=" + apellido + ", sueldo=" + sueldo + "]";
	}

}
