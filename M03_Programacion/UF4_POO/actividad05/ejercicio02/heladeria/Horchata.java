package actividad05.ejercicio02.heladeria;

public class Horchata extends Comida {

	private int cantidad;
	private int porcentChufa;

	public Horchata(double precio, String nombre, double numKcal, int cantidad, int porcentChufa) {
		super(precio, nombre, numKcal, "horchata");
		this.cantidad = cantidad;
		this.porcentChufa = porcentChufa;
	}

	public int getCantidad() {
		return cantidad;
	}

	public int getPorcentChufa() {
		return porcentChufa;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setPorcentChufa(int porcentChufa) {
		this.porcentChufa = porcentChufa;
	}

	@Override
	public String toString() {
		return "Horchata [Tipo= " + tipoClase + ", cantidad=" + this.cantidad + ", porcentChufa="
				+ this.porcentChufa + "%, NumKcal= " + getNumKcal() + ", Precio=" + getPrecio() + "e, Nombre="
				+ getNombre() + "]";
	}

}
