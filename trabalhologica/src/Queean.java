
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marlo
 */
public class Queean {

    public static int n, cont = 0;
    public static PrintWriter arq;
    public String nomeArquivo;

    public void gerar(String[] args) {
        try {
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                Scanner input = new Scanner(System.in);
                System.out.println("valor de N:");
                n = input.nextInt();

                input.close();
            }
            nomeArquivo = n + "queens.cnf";
            arq = new PrintWriter(nomeArquivo);

            long inicio = System.currentTimeMillis();
            gerarClausulas();
            escreverArq();
            double fim = (System.currentTimeMillis() - inicio);
            System.out.println("duração: " + fim / 1000 + " seg");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void gerarClausulas() throws Exception {
        linha(true);
        coluna();
        diagonalp();
        diagonalS();
        linha(false);
    }

    private static void diagonalS() {
        //diagonal secundaria// quadrante superior
        int x = 0;
        for (int i = n; i > 0; i--) {
            int[] diagonalAux = new int[x + 1];
            int y = 0;
            //4,7,10,13.....3,6,9
            for (int j = i; j <= n * (x + 1); j += n + 1) {
                diagonalAux[y] = j;
                y++;
            }
            x++;
            AdicionarClausulas(diagonalAux);
        }

        int y = n - 1;

        //diagonal secundaria//quadrante inferior
        for (int i = n + 1; i < n * n; i += n) {
            int[] diagonalAux = new int[y];
            x = 0;
            for (int j = i; j < n * n; j += n + 1) {
                diagonalAux[x] = j;
                x++;
            }
            y--;
            AdicionarClausulas(diagonalAux);
        }
    }

    public static void linha(boolean condicao) {
        //i is row count
        for (int i = 0; i < n; i++) {
            //1 or 2 or 3 or.. n
            int[] linhaAux = new int[n];
            for (int j = 0; j < n; j++) {
                linhaAux[j] = (j + 1) + (i * n);
            }
            if (condicao) {
                AdicionarClausulas(linhaAux);
            } else {
                clausulasLinhas(linhaAux);
            }
        }
    }

    public static void coluna() {
        for (int i = 1; i <= n; i++) {
            //1 or 5 or 9 or.. n
            int[] colunaAux = new int[n];
            for (int j = 0; j < n; j++) {
                colunaAux[j] = (j * n) + i;

            }
            AdicionarClausulas(colunaAux);
        }
    }

    public static void diagonalp() {
        //diagonal principal//quadrante superior
        for (int i = n; i > 0; i--) {
            int[] diagonal = new int[i];
            int x = 0;
            for (int j = i; x < i; j += n - 1) {
                diagonal[x] = j;
                x++;
            }
            AdicionarClausulas(diagonal);
        }

        int y = n - 1;
        //diagonal principal// quadrante inferior
        for (int i = n * 2; i < n * n; i += n) {

            int[] diagonal = new int[y];
            int x = 0;
            for (int j = i; j < n * n; j += n - 1) {
                diagonal[x] = j;
                x++;
            }
            y--;
            AdicionarClausulas(diagonal);
        }
    }

    public static void clausulasLinhas(int[] x) {
        String s = new String();
        for (int i = 0; i < x.length; i++) {
            s += x[i] + " ";
        }
        s += "0";
        cont++;
        arq.println(s);
    }

    public static void AdicionarClausulas(int[] x) {
        if (x.length == 1) {

        } else {
            for (int k = 0; k < x.length; k++) {
                for (int l = k + 1; l < x.length; l++) {
                    cont++;
                    arq.println("-" + x[k] + " -" + x[l] + " 0");

                }
            }
        }
    }

    public static void escreverArq() {
        //  arq.println("p cnf " + n + " " +cont);
        //System.out.println("quant clausulas: " + cont);
        arq.close();
    }
}
