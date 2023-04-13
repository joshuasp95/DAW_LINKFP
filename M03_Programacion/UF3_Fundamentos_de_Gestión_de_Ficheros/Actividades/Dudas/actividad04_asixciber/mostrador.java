package actividad04_asixciber;

import static actividad04_asixciber.listado_archivos.Ruta1;
import static actividad04_asixciber.listado_archivos.Ruta2;
import static actividad04_asixciber.listado_archivos.verArchivos;
import static actividad04_asixciber.opcion_user.pideEntero;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class mostrador {

    public static void muestraArchivos() {  //funcion que muestra el contenido de los archivos
        System.out.println("\nArchivos Disponibles:");
        verArchivos();
        System.out.println("Selecciona un archivo:");
        int valor_usuario_jsp = pideEntero(""); //pedimos al usuario un numero entero para determinar el camino del switch
        switch (valor_usuario_jsp) {
            case 1:
                String ruta_archivo1_jsp = Ruta1(); //guardamos el valor de la funcion en una variable strin (aunque se puede hacer poniendo la funcion direcamente en "new File(Ruta1())")
                File arch1_jsp = new File(ruta_archivo1_jsp);
                try {
                    FileReader fr_jsp = new FileReader(arch1_jsp); // clase que crea un nuevo stream que servira para coger bytes del fichero para ser leidos
                    BufferedReader br_jsp = new BufferedReader(fr_jsp); // clase que coge los  bytes del stream y los alamcena en un buffer para ser leidos como string
                    /*while (linea != null){ con while no funciona no entiendo porque
                        linea = br.readLine();
                        System.out.println(linea);
                    }*/
                    for (String linea_jsp = br_jsp.readLine(); linea_jsp != null; linea_jsp = br_jsp.readLine()) {  //bucle para mostrar todas las lineas del stream hasta que se vacie el buffer cuando sea de valor null
                        System.out.println(linea_jsp);
                    }
                    br_jsp.close(); //se cierra el buffer de lectura
                } catch (FileNotFoundException e) {
                    System.out.println("Error: Ruta de archivo desconocida");
                } catch (IOException e) {
                    System.out.println("Error: E/S de datos incorrecta");
                }
                break;
            case 2:
                String ruta_archivo2_jsp = Ruta2();
                File arch2_jsp = new File(ruta_archivo2_jsp);
                try {
                    FileReader fr_jsp = new FileReader(arch2_jsp);
                    BufferedReader br_jsp = new BufferedReader(fr_jsp);
                    for (String linea_jsp = br_jsp.readLine(); linea_jsp != null; linea_jsp = br_jsp.readLine()) {
                        System.out.println(linea_jsp);
                    }
                    br_jsp.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Error: Ruta de archivo desconocida");
                } catch (IOException e) {
                    System.out.println("Error: E/S de datos incorrecta");
                }
                break;
            default:
                System.out.println("Escoge alguno de los archivos");
                break;
        }
    }

}
