package actividad05.ejercicio02.heladeria;

public class Loteria implements Vendible {

	private double precio;
	private String numLoteria;

	public Loteria(double precio, String numLoteria) {
		super();
		this.precio = precio;
		this.numLoteria = numLoteria;
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

	public String getNumLoteria() {
		return numLoteria;
	}

	public void setNumLoteria(String numLoteria) {
		this.numLoteria = numLoteria;
	}

	@Override
	public String toString() {
		return "Loteria [precio=" + precio + "e, numLoteria=" + numLoteria + "]";
	}

}
