
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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
public class MainDpll {
    public static void main(String[] args) throws FileNotFoundException {
       
        Dpll dpll = new Dpll();
        Queean quean = new Queean();
        ArrayList<Clausula> c = new ArrayList<Clausula>(); 
        
        System.out.println("-> N-rainhas");
        quean.gerar(args);
        Scanner sc = new Scanner(new FileReader(quean.nomeArquivo));
        
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] linha = line.split(" ");
            c.add(new Clausula(linha));

        }
        System.out.println("-> DPLL");
        long inicio = System.currentTimeMillis();
        boolean satisfazivel = dpll.dpll(c);
        double fim = (System.currentTimeMillis()-inicio);

        if (satisfazivel) {
            System.out.println("satisfazivel");
            int x = (int) Math.sqrt(dpll.valoracao.size());
            for (int i = 0; i <dpll.valoracao.size(); i++) {
                if (i % x == 0) {
                    System.out.println("");
                }
                if (dpll.valoracao.get(i+1).booleanValue()) {
                    System.out.print("[X]");
                }else{
                    System.out.print("[ ]");
                }
            }
             System.out.println("");
             System.out.println("duração: " + fim/1000 + " seg");
        } else {
            System.out.println("insatisfazivel");
        }
    }
}
