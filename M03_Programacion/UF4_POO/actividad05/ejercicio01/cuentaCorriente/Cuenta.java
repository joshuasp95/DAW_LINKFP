package actividad05.ejercicio01.cuentaCorriente;

public class Cuenta {

//	Atributos de la clase Cuenta
	private String numCuenta;
	private double saldo;
	private String nomTitular;

//	Aunque no hace falta este seria el constructor vacio que se genera por defecto
	public Cuenta() {
		super();
		this.numCuenta = "";
		this.saldo = 0;
		this.nomTitular = "";
	}

//	Constructor de clase con sus atributos
	public Cuenta(String numCuenta, double saldo, String nomTitular) {
		super();
		this.numCuenta = numCuenta;
		this.saldo = saldo;
		this.nomTitular = nomTitular;
	}

//	GETTERS
	public String getNumCuenta() {
		return numCuenta;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getNomTitular() {
		return nomTitular;
	}

//	SETTERS

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setNomTitular(String nomTitular) {
		this.nomTitular = nomTitular;
	}

//	ToString
	@Override
	public String toString() {
		return "Cuenta [numCuenta=" + numCuenta + ", saldo=" + saldo + ", nomTitular=" + nomTitular + "]";
	}

}
