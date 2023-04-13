package actividad04_asixciber;

import static actividad04_asixciber.listado_archivos.verArchivos;
import static actividad04_asixciber.opcion_user.pideEntero;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class renombrador {

    public static void renombrarArchivos() {
        System.out.println("\nArchivos Disponibles:");
        verArchivos();
        System.out.println("Introduce el numero del archivo que deseas renombrar");
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
                    try {
                        BufferedReader br_jsp = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("Introduce el nuevo nombre");
                        String nuevo_nombre_jsp = br_jsp.readLine();    //coge el nombre introducido por el usuario
                        File arch_new_name_jsp = new File(rutaCarpeta_jsp + separador_jsp + nuevo_nombre_jsp);  //hay que crear un nuevo objeto con una ruta que contendra el nombre definido por el usuario
                        if (arch_jsp.renameTo(arch_new_name_jsp)) {    //se renombra el objeto anterior con el metodo renameTo y se reemplaza por el nuevo objeto con la ruta mencionada
                            System.out.println("Fichero modificado correctamente");
                        } else {
                            System.out.println("No se ha podido cambiar el nombre");
                        }
                    } catch (IOException e) {
                        System.out.println("Error: Nombre no valido");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Error: Demasiados elementos en el array");
                    }
                } else {
                    System.out.println("El fichero: " + arch_jsp.getName() + "; con ruta: " + arch_jsp.getPath() + " no existe");
                }
            } else {
                System.out.println("Escoge una de las opciones");
            }
        } else {
            System.out.println("El directorio: " + carpeta_jsp.getName() + "; con ruta: " + carpeta_jsp.getPath() + " no existe");
        }
    }
}
