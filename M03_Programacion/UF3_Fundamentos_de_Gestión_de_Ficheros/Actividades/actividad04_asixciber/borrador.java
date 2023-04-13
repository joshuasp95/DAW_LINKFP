package actividad04_asixciber;

import static actividad04_asixciber.listado_archivos.verArchivos;
import static actividad04_asixciber.opcion_user.pideEntero;
import java.io.File;

public class borrador {

    public static void eliminarArchivos() {
        System.out.println("\nArchivos Disponibles:");
        verArchivos();
        System.out.println("Introduce el numero del archivo que deseas eliminar");
        int valor_user_jsp = pideEntero("");
        String rutaProyecto_jsp = System.getProperty("user.dir");
        String separador_jsp = File.separator;
        String rutaCarpeta_jsp = rutaProyecto_jsp + separador_jsp + "archivos";
        File carpeta_jsp = new File(rutaCarpeta_jsp);
        if (carpeta_jsp.exists()) {
            File[] lista_jsp = carpeta_jsp.listFiles();
            String[] ruta_jsp = new String[lista_jsp.length];
            if ((valor_user_jsp > 0) && (valor_user_jsp <= lista_jsp.length)) {
                int valor_array_jsp = valor_user_jsp - 1;
                ruta_jsp[valor_array_jsp] = lista_jsp[valor_array_jsp].getPath();
                File arch_jsp = new File(ruta_jsp[valor_array_jsp]);
                if (arch_jsp.exists()) {
                    if (arch_jsp.delete()) {
                        System.out.println("El fichero se ha eliminado");
                    } else {
                        System.out.println("No se ha podido eliminar el fichero");
                    }
                } else {
                    System.out.println("El fichero: " + arch_jsp.getName() + "; con ruta: " + arch_jsp.getPath() + " no existe");
                }
            } else {
                System.out.println("Escoge alguno de los archivos");
            }
        } else {
            System.out.println("El directorio: " + carpeta_jsp.getName() + "; con ruta: " + carpeta_jsp.getPath() + " no existe");
        }

    }
}
