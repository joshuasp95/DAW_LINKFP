package actividad04_asixciber;

import static actividad04_asixciber.listado_archivos.verArchivos;
import static actividad04_asixciber.opcion_user.pideEntero;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class modificador {

    public static void modificaArchivos() {
        System.out.println("\nArchivos Disponibles:");
        verArchivos();
        System.out.println("Introduce el numero del archivo que deaseas modificar");
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
                        FileWriter fw_jsp = new FileWriter(arch_jsp);  //esta vez en lugar de usar la clase FileWriter para escribir sobre texto vacio, se usara para sobreescribir el contenido del archivo especificado en la ruta
                        BufferedWriter bw_jsp = new BufferedWriter(fw_jsp);
                        BufferedReader br_jsp = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("Escribe el texto que quieras para modificar el contenido del archivo");
                        System.out.println("Si lo haces borraras el contenido anterior");
                        String texto_user_jsp = br_jsp.readLine();
                        bw_jsp.write(texto_user_jsp);
                        bw_jsp.flush();
                        bw_jsp.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: Ruta de archivo desconocida");
                    } catch (IOException e) {
                        System.out.println("Error: E/S de datos incorrecta");
                    }
                } else {
                    System.out.println("El fichero: " + arch_jsp.getName() + "; con ruta: " + arch_jsp.getPath() + " no existe");
                }
            } else {
                System.out.println("Elige alguna de las opciones");
            }
        } else {
            System.out.println("El directorio: " + carpeta_jsp.getName() + "; con ruta: " + carpeta_jsp.getPath() + " no existe");
        }
    }
}
