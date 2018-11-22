
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marlo
 */
public class Clausula {

    public ArrayList<Integer> literais;

    public Clausula() {
        this.literais = new ArrayList<Integer>();
    }

    public Clausula(String[] linha) {
        this.literais = new ArrayList<Integer>();
        for (int i = 0; i < linha.length; i++) {
            if (!linha[i].equals("0")) {
                literais.add(Integer.parseInt(linha[i]));
            }
        }
    }

    public Clausula(int l) {
        this.literais = new ArrayList<Integer>();
        literais.add(l);
    }

    public int retornaLiteral() {
        return literais.get(0).intValue();
    }

    public ArrayList<Integer> getLiterais() {
        return literais;
    }

    public void setLiterais(ArrayList<Integer> literais) {
        this.literais = literais;
    }
}
