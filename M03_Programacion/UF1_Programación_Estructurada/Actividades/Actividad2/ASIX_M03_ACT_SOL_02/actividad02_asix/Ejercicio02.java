package actividad02_asix;

/**
 * escribe un programa que almacene en un array bidimensional cuatro
 * puntuaciones de tres películas con los valores mostrados en el esquema
 * mostrado después del enunciado. Seguidamente se mostrará por consola todos
 * los valores almacenados en el array y los siguientes resultados (calculados
 * según los valores del array
 */
public class Ejercicio02 {

    public static void main(String[] args) {

        final int x = 3;
        final int y = 4;


        int[][] puntuaciones = new int[x][y];

        // puntuaciones = RellenarDatos.introducirNotas(puntuaciones);
        puntuaciones[0][0] = 8;
        puntuaciones[0][1] = 9;
        puntuaciones[0][2] = 10;
        puntuaciones[0][3] = 4;
        puntuaciones[1][0] = 3;
        puntuaciones[1][1] = 8;
        puntuaciones[1][2] = 9;
        puntuaciones[1][3] = 7;
        puntuaciones[2][0] = 9;
        puntuaciones[2][1] = 7;
        puntuaciones[2][2] = 6;
        puntuaciones[2][3] = 8;

        // Mostramos los valores del array
        System.out.println("Valores del array:: ");
        for (int i = 0; i < puntuaciones.length; i++) {
            for (int j = 0; j < puntuaciones[i].length; j++) {
                System.out.print(puntuaciones[i][j] + "   ");
            }
            System.out.println("");
        }
        System.out.println("");
        // mostramos las notas de la primera pelicula
        System.out.print("Notas de la primera película: ");
        for (int i = 0; i < puntuaciones.length; i++) {
            System.out.print(puntuaciones[i][0] + " , ");
        }

        // • La media de la segunda película
        System.out.println("---Media de la segunda película---");
        double notaMedia = 0;
        for (int i = 0; i < puntuaciones[1].length; i++) {
            notaMedia += puntuaciones[1][i];
        }
        notaMedia = notaMedia / puntuaciones[1].length;
        System.out.println("La media de la seguna película es un:" + notaMedia);

        // • El valor más alto de la tercera película.
        System.out.println("---- El valor más alto de la tercera película---");
        double notaMaxima = puntuaciones[2][0];
        for (int i = 0; i < puntuaciones[2].length; i++) {
            if (notaMaxima < puntuaciones[2][i]) {
                notaMaxima = puntuaciones[2][i];
            }
        }
        System.out.println("El valor más alto de la tercera película es:" + notaMaxima);

    }

}
