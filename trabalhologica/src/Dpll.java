
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marlo
 */
public class Dpll {
    HashMap<Integer, Boolean> valoracao = new HashMap<Integer, Boolean>();
    public int literal;
    int aux;
    
    public ArrayList<Clausula> clone (ArrayList<Clausula> formula){
        ArrayList<Clausula> f = new ArrayList<>();
        
        for (Clausula c : formula){
            Clausula clausula = new Clausula();
            for(int i = 0; i < c.getLiterais().size(); i++){
                clausula.getLiterais().add(c.getLiterais().get(i));
            }
            
            f.add(clausula);
        }
        
        return f;
    }

    public boolean dpll(ArrayList<Clausula> fLinha) {

        ArrayList<Clausula> literalnegado;
        ArrayList<Clausula> literalpositivo;
        
        fLinha = simplifica(fLinha);
        if (fLinha.size() == 0) {
            return true;
        }
        if (ContemVazia(fLinha)) {
            return false;
        }

        int l = fLinha.get(0).retornaLiteral();
  
        literalnegado = this.clone(fLinha);
        Clausula c = new Clausula(l);
        literalpositivo = this.clone(fLinha);
        literalpositivo.add(c);

        if (dpll(literalpositivo)) {
            return true;
        } else {
            c.getLiterais().remove(0);
            c.getLiterais().add(l * -1);
            literalnegado.add(c);
            return dpll(literalnegado);
        }
    }

    public ArrayList<Clausula> simplifica(ArrayList<Clausula> f) {
        while (contemUnitaria(f)) {
            listaV(literal);
            // remove todas as ocorrencias da clausula
            for (int i = 0; i < f.size(); i++) {
                if (f.get(i).literais.contains(literal)) {
                    f.remove(i);
                    i--;
                }
            }
            // remove todas as ocorencias da clausula negada
            for (int i = 0; i < f.size(); i++) {
                if (f.get(i).literais.contains(-literal)) {
                    for (int j = 0; j < f.get(i).literais.size(); j++) {
                        if (f.get(i).literais.get(j).equals(-literal)) {
                            f.get(i).literais.remove(j);
                        }
                    }
                }
            }
        }
        return f;
    }

    public void listaV(int v) {
        int aux = v;
        if (v < 0) {
            aux = aux * -1;
        }
        if (!valoracao.containsKey(aux)) {
            if (v > 0) {
                valoracao.put(aux, true);
            } else {
                valoracao.put(aux, false);
            }
        } else{
            if (v > 0) {
                valoracao.replace(aux, true);
            } else {
                valoracao.replace(aux, false);

            }
        }
    }

    public boolean contemUnitaria(ArrayList<Clausula> f) {
        for (Clausula clausula : f) {
            if (clausula.literais.size() == 1) {
                literal = clausula.literais.get(0).intValue();
                return true;
            }
        }
        return false;
    }

    public boolean ContemVazia(ArrayList<Clausula> fLinha) {
        for (Clausula clausula : fLinha) {
            if (clausula.literais.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
