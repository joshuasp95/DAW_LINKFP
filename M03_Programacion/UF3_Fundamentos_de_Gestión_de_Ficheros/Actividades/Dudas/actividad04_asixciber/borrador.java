package actividad04_asixciber;

import static actividad04_asixciber.listado_archivos.Ruta1;
import static actividad04_asixciber.listado_archivos.Ruta2;
import static actividad04_asixciber.listado_archivos.verArchivos;
import static actividad04_asixciber.opcion_user.pideEntero;
import java.io.File;

public class borrador {

    public static void eliminarArchivos() {
        System.out.println("\nArchivos Disponibles:");
        verArchivos();
        System.out.println("Introduce el numero del archivo que deseas eliminar");
        int user_in_jsp = 99;

        do {    //para no volver a mostrar el menu de opciones
            user_in_jsp = pideEntero("");
            if ((user_in_jsp <= 0) || (user_in_jsp >= 3)) { // para no poner el default siempre
                System.out.println("Escoge uno de los archivos");
            }
            switch (user_in_jsp) {
                case 1:
                    File arch1_jsp = new File(Ruta1());
                    if (arch1_jsp.delete()) {   //funcion o metodo del objeto creado con la clase file que sirve para borrar el archivo especificado en la ruta
                        System.out.print("El fichero se ha eliminado");
                    } else {
                        System.out.println("El fichero no se ha eliminado");
                    }
                    break;
                case 2:
                    File arch2_jsp = new File(Ruta2());
                    if (arch2_jsp.delete()) {
                        System.out.println("El fichero se ha eliminado");
                    } else {
                        System.out.println("El fichero no se ha eliminado");
                    }
                    break;
            }
        } while ((user_in_jsp <= 0) || (user_in_jsp >= 3));
    }
}
