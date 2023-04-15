package actividad04_asixciber;

import java.io.File;

public class listado_archivos {

    public static void verArchivos() {  //funcion para listar archivos en forma de array
        String rutaProyecto_jsp = System.getProperty("user.dir");
        String separador_jsp = File.separator;
        String rutaCarpeta_jsp = rutaProyecto_jsp + separador_jsp + "archivos";
        File carpeta_jsp = new File(rutaCarpeta_jsp);
        File[] lista_jsp = carpeta_jsp.listFiles(); //array que va a recoger las distintas variables de los archivos que hay disponibles en el directorio especificado
        for (int i_jsp = 0; i_jsp < lista_jsp.length; i_jsp++) {    //bucle para recorrer y mostra por pantalla los valores del array
            int contador_jsp = i_jsp + 1;   //para que el resultado por pantalla comience en el 1 y no en el 0
            System.out.print(contador_jsp + "- " + lista_jsp[i_jsp].getName()); //resultado nombre fichero
            System.out.println(" [Ruta: " + lista_jsp[i_jsp].getPath() + "]");  //resultado ruta fichero
        }
    }

    public static String Ruta1() {   //funcion que devuelve la ruta del archivo 1 que se va a necesitar en otras clases
        String rutaProyecto_jsp = System.getProperty("user.dir");
        String separador_jsp = File.separator;
        String rutaCarpeta_jsp = rutaProyecto_jsp + separador_jsp + "archivos";
        File carpeta_jsp = new File(rutaCarpeta_jsp);
        File[] lista_jsp = carpeta_jsp.listFiles();
        String[] rutas_array_jsp = {"", ""};//declaramos e inicializamos el array
        for (int i_jsp = 0; i_jsp < lista_jsp.length; i_jsp++) {
            rutas_array_jsp[i_jsp] = lista_jsp[i_jsp].getPath();
        }
        String ruta1_jsp;
        ruta1_jsp = rutas_array_jsp[0]; //asignamos al string el valor de la primera posicion del array
        return ruta1_jsp;   //devolvemos el valor
    }

    public static String Ruta2() {  //funcion que devuelve la ruta del archivo 2 que se va a necesitar en otras clases
        String rutaProyecto_jsp = System.getProperty("user.dir");
        String separador_jsp = File.separator;
        String rutaCarpeta_jsp = rutaProyecto_jsp + separador_jsp + "archivos";
        File carpeta_jsp = new File(rutaCarpeta_jsp);
        File[] lista_jsp = carpeta_jsp.listFiles();
        String[] rutas_array_jsp = {"", ""};
        for (int i_jsp = 0; i_jsp < lista_jsp.length; i_jsp++) {
            rutas_array_jsp[i_jsp] = lista_jsp[i_jsp].getPath();
        }
        String ruta2_jsp;
        ruta2_jsp = rutas_array_jsp[1]; //asignamos al string el valor de la segunda posicion del array
        return ruta2_jsp;   //devolvemos el valor
    }
}
