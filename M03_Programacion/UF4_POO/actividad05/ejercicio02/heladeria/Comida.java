package actividad05.ejercicio02.heladeria;

public abstract class Comida implements Vendible {

	public static String tipo = "Comida";
	private double precio;
	private String nombre;
	private double numKcal;
	protected String tipoClase;

	public Comida(double precio, String nombre, double numKcal) {
		super();
		this.precio = precio;
		this.nombre = nombre;
		this.numKcal = numKcal;
	}

	public Comida(double precio, String nombre, double numKcal, String tipo) {
		super();
		this.precio = precio;
		this.nombre = nombre;
		this.numKcal = numKcal;
		this.tipoClase = tipo;
	}

	@Override
	public double getPrecio() {
		// TODO Auto-generated method stub
		return this.precio;
	}

	@Override
	public void setPrecio(double precio) {
		// TODO Auto-generated method stub
		this.precio = precio;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getNumKcal() {
		return numKcal;
	}

	public void setNumKcal(double numKcal) {
		this.numKcal = numKcal;
	}

	@Override
	public String toString() {
		return "Comida [precio=" + precio + "e, nombre=" + nombre + ", numKcal=" + numKcal + "]";
	}

}
