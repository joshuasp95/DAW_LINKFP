package actividad06;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import actividad06.exceptions.ErrorLeerArchivo;

public class VentanaBienvenida extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static String archivoNombre = "mapEmpleados.obj";

	// Etiquetas de texto
	private JLabel etiquetaNombre;
	private JLabel etiquetaPassword;
	private JLabel etiquetaError;

	// Campos de texto
	private JTextField campoNombre;
	private JPasswordField campoPassword;

	// Botón de inicio de sesión
	private JButton botonLogin;

	public VentanaBienvenida() {
		super(); // usamos el contructor de la clase padre JFrame
		configurarVentana(); // configuramos la ventana
		inicializarComponentes(); // inicializamos los atributos o componentes
	}

	private void configurarVentana() {
		this.setTitle("Inicia Sesion"); // colocamos titulo a la ventana
		this.setSize(410, 310); // colocamos tamanio a la ventana (ancho, alto)
		this.setLocationRelativeTo(null); // centramos la ventana en la pantalla
		this.setLayout(null); // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
		this.setResizable(true); // hacemos que la ventana no sea redimiensionable
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // hacemos que cuando se cierre la ventana termina todo
																// proceso
	}

	private void inicializarComponentes() {

		// Etiquetas de texto
		etiquetaNombre = new JLabel();
		etiquetaNombre.setText("Nombre de usuario:");
		etiquetaNombre.setBounds(50, 50, 120, 25);
		this.add(etiquetaNombre);

		etiquetaPassword = new JLabel();
		etiquetaPassword.setText("Contraseña:");
		etiquetaPassword.setBounds(50, 80, 120, 25);
		this.add(etiquetaPassword);

		// Campos de texto
		campoNombre = new JTextField();
		campoNombre.setBounds(170, 50, 120, 25);
		this.add(campoNombre);

		campoPassword = new JPasswordField();
		campoPassword.setBounds(170, 80, 120, 25);
		this.add(campoPassword);

		// Botón de inicio de sesión
		botonLogin = new JButton();
		botonLogin.setText("Iniciar Sesión");
		botonLogin.setBounds(100, 130, 120, 25);
		botonLogin.addActionListener(this);
		this.add(botonLogin);

		etiquetaError = new JLabel();
		etiquetaError.setBounds(50, 110, 200, 25);
		etiquetaError.setForeground(Color.RED);
		this.add(etiquetaError);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nombre = campoNombre.getText();
		char[] password = campoPassword.getPassword();
		Empleado emp;
		Map<String, Empleado> mapEmp;

		// Lógica de inicio de sesión aquí...
		mapEmp = VentanaPrincipal.mapEmpleados;

		try {

			emp = comprobarEmpleado(mapEmp, nombre, password);

			// Comprobar cajas vacias
			if (nombre.isEmpty() || String.valueOf(password).isEmpty()) {
				etiquetaError.setText("DEBES INTRODUCIR LOS DATOS");
			} else {

				if (emp != null) {
					leerArchivo();
					mostrarPanelBienvenida(nombre, password);
				} else {
					etiquetaError.setText("CREDENCIALES INCORRECTAS");
				}
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ErrorLeerArchivo ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: " + ex);
			this.dispose();

		}
	}

	public static void leerArchivo() throws ErrorLeerArchivo, IOException, ClassNotFoundException {
		String path = "mapEmpleado.obj";
		ObjectInputStream ois = null;
		Empleado e = null;

		try {
			File archivo = new File(path);
			FileInputStream fis = new FileInputStream(archivo);
			ois = new ObjectInputStream(fis);

			while ((e = (Empleado) ois.readObject()) != null) {
				System.out.println(e.toString());
			}

		} catch (EOFException ex) {
			System.out.println("Fin del archivo.");
		} catch (FileNotFoundException ex) {
			throw new ErrorLeerArchivo("La ruta del fichero es incorrecta");
		} catch (Exception ex) {
			System.out.println("Error detectado: " + ex);
		} finally {

			try {
				if (ois != null) {
					ois.close();
				}
			} catch (Exception ex) {
				System.out.println("Error detectado: " + ex);
			}
		}

	}

	public static Empleado comprobarEmpleado(Map<String, Empleado> mapEmpleados, String nombre, char[] pass) {
		Iterator<String> it = mapEmpleados.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (mapEmpleados.get(key).getNombre().compareTo(nombre) == 0
					&& String.valueOf(pass).equals(mapEmpleados.get(key).getApellido())) {
				return mapEmpleados.get(key);
			}
		}

		return null;
	}

	public static void escribirMapArchivo(HashMap<String, Empleado> mapEmpleados) {
		Iterator<String> it = mapEmpleados.keySet().iterator();
		ObjectOutputStream oos = null;

		try {
			File archivo = new File(archivoNombre);
			oos = new ObjectOutputStream(new FileOutputStream(archivo));

			while (it.hasNext()) {
				String key = (String) it.next();
				oos.writeObject(mapEmpleados.get(key));
			}

		} catch (IOException e) {
			System.out.println("IOException: ");
			e.printStackTrace();
		} catch (Exception eGen) {
			System.out.println("Error desconocido: " + eGen);
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void mostrarPanelBienvenida(String nombre, char[] password) {
		// Crear un JPanel para contener el mensaje y el botón
		JPanel panelBienvenida = new JPanel(new BorderLayout());

		// Agregar el mensaje de bienvenida
		JLabel mensajeBienvenida = new JLabel("Bienvenido " + nombre + " " + String.valueOf(password) + ".");
		panelBienvenida.add(mensajeBienvenida, BorderLayout.NORTH);

		// Agregar el botón de cierre
		JButton botonCerrar = new JButton("Cerrar");
		botonCerrar.addActionListener(event -> {
			// Cerrar la ventana al presionar el botón
			this.dispose();
		});
		panelBienvenida.add(botonCerrar, BorderLayout.SOUTH);

		// Mostrar el panel en un JOptionPane personalizado
		JOptionPane.showMessageDialog(this, panelBienvenida);
	}

}
