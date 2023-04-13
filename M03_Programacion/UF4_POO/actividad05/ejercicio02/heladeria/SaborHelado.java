package actividad05.ejercicio02.heladeria;

public class SaborHelado extends Comida {

	private double porcentGrasa;
	private String edulcorante;

	public SaborHelado(double precio, String nombre, double numKcal, double porcentGrasa, String edulcorante) {
		super(precio, nombre, numKcal);
		this.porcentGrasa = porcentGrasa;
		this.edulcorante = edulcorante;
	}

	public double getPorcentGrasa() {
		return porcentGrasa;
	}

	public String getEdulcorante() {
		return edulcorante;
	}

	public void setPorcentGrasa(double porcentGrasa) {
		this.porcentGrasa = porcentGrasa;
	}

	public void setEdulcorante(String edulcorante) {
		this.edulcorante = edulcorante;
	}

	@Override
	public String toString() {
		return "\nSaborHelado [numKcal=" + getNumKcal() + ", porcentGrasa=" + porcentGrasa + "%, edulcorante="
				+ edulcorante + ", Precio=" + getPrecio() + "e, Nombre=" + getNombre() + "]";
	}

}
