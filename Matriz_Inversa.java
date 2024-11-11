import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;  // Importamos DecimalFormat

public class Matriz_Inversa {

    public static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    public static String entrada;

    public static DecimalFormat df = new DecimalFormat("#.##");

    // imprimir matrices
    public static void imprimirMatriz(double[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(df.format(m[i][j]) + "   ");
            }
            System.out.println();
        }
    }

    // matriz a
    public static double[][] matrizA() throws IOException {
        System.out.println("Escribe el número de renglones para la matriz A: ");
        entrada = buffer.readLine();
        int renglones = Integer.parseInt(entrada);

        System.out.println("Escribe el número de columnas para la matriz A: ");
        entrada = buffer.readLine();
        int columnas = Integer.parseInt(entrada);

        double[][] matrizA = new double[renglones][columnas];

        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.println("Introduce el valor para la posición [" + i + "][" + j + "] de la matriz A: ");
                entrada = buffer.readLine();
                matrizA[i][j] = Integer.parseInt(entrada);
            }
        }

        return matrizA;
    }

    public static double[][] matrizInversa(double[][] matriz){
        int n = matriz.length;
        
        if (matriz.length != matriz[0].length) {
            System.out.println("La matriz no es cuadrada, no tiene inversa.");
            return null;
        }

        double[][] inversa = new double[n][n];

        //Matriz identidad
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    inversa[i][j] = 1;
                } else {
                    inversa[i][j] = 0;
                }
            }
        }

        // Diagonal igual a 1
        for (int i = 0; i < n; i++) {
            if (matriz[i][i] == 0) {
                System.out.println("La matriz no tiene inversa.");
                return null;
            }

            double punto = matriz[i][i];
            for (int j = 0; j < n; j++) {
                matriz[i][j] /= punto;
                inversa[i][j] /= punto;
            }

            // hacer 0 los demas valores
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double factor = matriz[j][i];
                    for (int k = 0; k < n; k++) {
                        matriz[j][k] -= matriz[i][k] * factor;
                        inversa[j][k] -= inversa[i][k] * factor;
                    }
                }
            }
        }

        return inversa;
    }

    public static void main(String[] args) throws IOException {
        double[][] A = matrizA();

        System.out.println("Matriz A:");
        imprimirMatriz(A);

        double[][] inversa = matrizInversa(A);

        System.out.println("La matriz inversa de A es:");
        imprimirMatriz(inversa);
        
        
    }
}
