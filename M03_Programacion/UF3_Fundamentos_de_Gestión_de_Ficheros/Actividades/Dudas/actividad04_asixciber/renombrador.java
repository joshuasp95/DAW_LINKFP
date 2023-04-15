package actividad04_asixciber;

import static actividad04_asixciber.listado_archivos.Ruta1;
import static actividad04_asixciber.listado_archivos.Ruta2;
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
        int user_in_jsp = pideEntero("");

        switch (user_in_jsp) {
            case 1:
                try {
                File arch1_jsp = new File(Ruta1());
                BufferedReader br_jsp = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Introduce el nuevo nombre");
                String nuevo_nombre_jsp = br_jsp.readLine();    //coge el nombre introducido por el usuario
                String rutaProyecto_jsp = System.getProperty("user.dir");
                String separador_jsp = File.separator;
                String rutaCarpeta_jsp = rutaProyecto_jsp + separador_jsp + "archivos";
                File arch_new_name_jsp = new File(rutaCarpeta_jsp + separador_jsp + nuevo_nombre_jsp);  //hay que crear un nuevo objeto con una ruta que contendra el nombre definido por el usuario
                if (arch1_jsp.renameTo(arch_new_name_jsp)) {    //se renombra el objeto anterior con el metodo renameTo y se reemplaza por el nuevo objeto con la ruta mencionada
                    System.out.println("Fichero modificado correctamente");
                } else {
                    System.out.println("No se ha podido cambiar el nombre");
                }
            } catch (IOException e) {
                System.out.println("Error: Nombre no valido");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error: Posicion no validad del array");
            }
            break;
            case 2:
                try {
                File arch2_jsp = new File(Ruta2());
                BufferedReader br_jsp = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Introduce el nuevo nombre");
                String nuevo_nombre_jsp = br_jsp.readLine();
                String rutaProyecto_jsp = System.getProperty("user.dir");
                String separador_jsp = File.separator;
                String rutaCarpeta_jsp = rutaProyecto_jsp + separador_jsp + "archivos";
                File arch_new_name_jsp = new File(rutaCarpeta_jsp + separador_jsp + nuevo_nombre_jsp);
                if (arch2_jsp.renameTo(arch_new_name_jsp)) {
                    System.out.println("Fichero modificado correctamente");
                } else {
                    System.out.println("No se ha podido cambiar el nombre");
                }
            } catch (IOException e) {
                System.out.println("Error: Nombre no valido");
            }
            break;
            default:
                System.out.println("Escoge una de las opciones");
                break;
        }
    }

}
