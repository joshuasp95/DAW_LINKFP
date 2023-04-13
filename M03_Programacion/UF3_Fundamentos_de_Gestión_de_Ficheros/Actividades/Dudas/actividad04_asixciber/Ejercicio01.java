package actividad04_asixciber;

import static actividad04_asixciber.borrador.eliminarArchivos;
import static actividad04_asixciber.listado_archivos.verArchivos;
import static actividad04_asixciber.modificador.modificaArchivos;
import static actividad04_asixciber.mostrador.muestraArchivos;
import static actividad04_asixciber.renombrador.renombrarArchivos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio01 {//Clase que contiene el codigo principal

    public static void main(String[] args) {//funcion que va a ejecutar el programa
        String rutaProyecto_jsp = System.getProperty("user.dir");//ruta del programa
        String separador_jsp = File.separator;//separador según el SO del sistema
        String rutaCarpeta_jsp = rutaProyecto_jsp + separador_jsp + "archivos"; //nueva ruta correspondiente a la carpeta que se va a generar con File
        File carpeta_jsp = new File(rutaCarpeta_jsp);//clase para crear objeto carpeta
        carpeta_jsp.mkdir();//funcion que crea la carpeta en la ruta especificada (es un metodo dentro del objeto carpeta)
        File archivo_jsp = new File(rutaCarpeta_jsp + separador_jsp + "archivo1.txt");//clase para crear nuevo objeto (archivo)
        File archivo2_jsp = new File(rutaCarpeta_jsp + separador_jsp + "archivo2.txt");
        //en caso de que no exista la ruta especificada o tenga un error de entrada de datos, e.j.  que el archivo ya exista
        try {
            archivo_jsp.createNewFile();//funcion para crear archivo (metodo dentro del objeto archivo)
            archivo2_jsp.createNewFile();
            /**
             * System.out.println(archivo_jsp); ---> Comprobacion de ruta
             */
            FileWriter fw_jsp = new FileWriter(archivo_jsp);//clase para crear un nuevo stream que mandara bytes al fichero con ruta arriba determinada
            BufferedWriter bw_jsp = new BufferedWriter(fw_jsp);//Buffer que almacena el String completo
            String saltoLinea_jsp = System.getProperty("line.separator"); // salto de linea que cambia segun el SO
//Texto que se va a almacenar en el buffer
            bw_jsp.write("El que la ciencia pueda sobrevivir largamente depende de la psicología; es decir, depende de lo que los seres humanos deseen."
                    + saltoLinea_jsp
                    + "¡Qué agradable sería un mundo en el que no se permitiera a nadie operar en bolsa a menos que hubiese pasado un examen de economía y poesía griega, y en el que los políticos estuviesen obligados a tener un sólido conocimiento de la historia y de la novela moderna! ");
            bw_jsp.flush();//se encarga de vaciar el buffer
            bw_jsp.close();//se encarga de cerrar el buffer
            FileWriter fw2_jsp = new FileWriter(archivo2_jsp);
            BufferedWriter bw2_jsp = new BufferedWriter(fw2_jsp);
            bw2_jsp.write("Gran parte de las dificultades por las que atraviesa el mundo se deben a que los ignorantes están completamente seguros y los inteligentes llenos de dudas."
                    + saltoLinea_jsp
                    + "El hombre juicioso sólo piensa en sus males cuando ello conduce a algo práctico; todos los demás momentos los dedica a otras cosas.");
            bw2_jsp.flush();
            bw2_jsp.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Ruta de archivo desconocida");
        } catch (IOException e) {
            System.out.println("Error: Entrada/Salida de datos erronea");
        }
        int user_in_jsp = 99; //variable para el control de opciones del menu por parte del usuario
        int contador_jsp = 0;//variable para mostrar si el usuario introduce muchas veces mal el valor del opciones del menu
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// clase para crear un nuevo buffer que contendra el valor introducido por el usuario
        int contadormenu_jsp = 0;//variable para cambiar el mensaje con el que empieza el menu
        do {
            contadormenu_jsp++;
            if (contadormenu_jsp > 1) {
                System.out.println("Opciones: ");
            } else {
                System.out.println("Buenas, dispones de archivos en memoria, que te gustaría hacer con ellos? ");
            }
            System.out.println("1: Listar archivos");
            System.out.println("2: Mostrar contenido de los archivos");
            System.out.println("3: Modificar archivos");
            System.out.println("4: Borrar archivos");
            System.out.println("5: Renombrar archivos");
            System.out.println("0: Ninguna, quiero salir");
            try {
                String user_string_jsp = br.readLine();//variable para guardar el valor del buffer introducido por el usuario
                user_in_jsp = Integer.parseInt(user_string_jsp);//conversion de string a int
            } catch (Exception e) {
                contador_jsp++;
                if (contador_jsp > 3) {
                    System.out.println("Creo que algo estas haciendo mal...");
                } else {
                    System.out.println("3RR0R#!");
                    System.out.println("Introduce de nuevo la opcion que has elegido");
                }
                user_in_jsp = 99;// valor para que no finalice el programa y vaya por el default y vuelva a mostrar el menu
            }
            switch (user_in_jsp) {
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                case 1:
                    verArchivos();
                    break;
                case 2:
                    muestraArchivos();
                    break;
                case 3:
                    modificaArchivos();
                    break;
                case 4:
                    eliminarArchivos();
                    break;
                case 5:
                    renombrarArchivos();
                    break;
                default:
                    System.out.println("Escoge alguna opción de la lista");
                    break;
            }
            System.out.println(""); //salto de linea para maqueta resultado por consola
        } while (user_in_jsp != 0);
    }

}
