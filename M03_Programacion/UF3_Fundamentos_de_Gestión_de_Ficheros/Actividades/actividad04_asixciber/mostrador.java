package actividad04_asixciber;

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
        int valor_user_jsp = pideEntero(""); //pedimos al usuario un numero entero para determinar el camino del switch
        String rutaProyecto_jsp = System.getProperty("user.dir");
        String separador_jsp = File.separator;
        String rutaCarpeta_jsp = rutaProyecto_jsp + separador_jsp + "archivos";
        File carpeta_jsp = new File(rutaCarpeta_jsp);
        if(carpeta_jsp.exists()){
        File[] lista_jsp = carpeta_jsp.listFiles();
        String[] ruta_jsp = new String[lista_jsp.length];
        if ((valor_user_jsp > 0) && (valor_user_jsp <= lista_jsp.length)) {
            int valor_array_jsp = valor_user_jsp - 1;   //para que coja la posicion correcta del array que empieza en 0 y no en 1 como aparece por pantalla
            ruta_jsp[valor_array_jsp] = lista_jsp[valor_array_jsp].getPath();
            File arch_jsp = new File(ruta_jsp[valor_array_jsp]);
            if (arch_jsp.exists()) {
                try {
                    FileReader fr_jsp = new FileReader(arch_jsp); // clase que crea un nuevo stream que servira para coger bytes del fichero para ser leidos
                    BufferedReader br_jsp = new BufferedReader(fr_jsp); // clase que coge los  bytes del stream y los alamcena en un buffer para ser leidos como string
                    String linea_jsp = br_jsp.readLine();
                    while (linea_jsp != null) {
                        System.out.println(linea_jsp);
                        linea_jsp = br_jsp.readLine();
                    }
                    br_jsp.close(); //se cierra el buffer de lectura
                } catch (FileNotFoundException e) {
                    System.out.println("Error: Ruta de archivo desconocida");
                } catch (IOException e) {
                    System.out.println("Error: E/S de datos incorrecta");
                }
            } else {
                System.out.println("El fichero: " + arch_jsp.getName() + "; con ruta: " + arch_jsp.getPath() + " no existe");
            }
        }
        else{
            System.out.println("Escoge alguno de los archivos");
        }
        }else{
            System.out.println("El directorio: "+carpeta_jsp.getName()+"; con ruta: "+carpeta_jsp.getPath()+" no existe");
        }
    }

}
