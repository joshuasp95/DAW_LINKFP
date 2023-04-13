package actividad04_asixciber;

import static actividad04_asixciber.listado_archivos.Ruta1;
import static actividad04_asixciber.listado_archivos.Ruta2;
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

        switch (valor_user_jsp) {
            case 1:
                String ruta_archivo1 = Ruta1();
                File arch1 = new File(ruta_archivo1);
                try {
                    FileWriter fw_jsp = new FileWriter(arch1);  //esta vez en lugar de usar la clase FileWriter para escribir sobre texto vacio, se usara para sobreescribir el contenido del archivo especificado en la ruta
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
                break;
            case 2:
                String ruta_archivo2_jsp = Ruta2();
                File arch2_jsp = new File(ruta_archivo2_jsp);
                try {
                    FileWriter fw_jsp = new FileWriter(arch2_jsp);
                    BufferedWriter bw_jsp = new BufferedWriter(fw_jsp);
                    BufferedReader br_jsp = new BufferedReader(new InputStreamReader(System.in));
                    String texto_user_jsp = br_jsp.readLine();
                    bw_jsp.write(texto_user_jsp);
                    bw_jsp.flush();
                    bw_jsp.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Error: Ruta de archivo desconocida");
                } catch (IOException e) {
                    System.out.println("Error: E/S de datos incorrecta");
                }
                break;
            default:
                System.out.println("Elige alguna de las opciones");
                break;
        }
    }
}
