package actividad06.exceptions;

public class ErrorLeerArchivo extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorLeerArchivo(String mensaje) {
		super(mensaje);
	}

}
