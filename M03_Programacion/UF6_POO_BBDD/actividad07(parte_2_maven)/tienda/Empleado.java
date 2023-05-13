package actividad07.tienda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// La clase está anotada con"@Entity",lo que significa que se puede 
// almacenar en una base de datos relacional utilizando el API de 
// Java Persistence(JPA).
@Entity
public class Empleado {

	@Id // Indica que el campo 'id' es la clave primaria de la entidad
	@GeneratedValue // Genera automáticamente un valor único para la clave primaria
	private long id;
	private String nombre;
	private String apellidos;
	@ManyToOne // Indica que hay una relación muchos-a-uno con la entidad 'Tienda'
	private Tienda tienda;

	public Empleado(String nombre, String apellidos) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public long getID() {
		return this.id;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Override
	public String toString() {
		return "Empleado [ID= " + this.id + "; Nombre=" + this.nombre + ", apellidos=" + this.apellidos + "]";
	}

}
