/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package czinkoczikifejezesekkiertekelese;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Nami
 */
public class CzinkocziKifejezesekKiertekelese {

    private ArrayList<String> verem;
    private HashMap<Character, Integer> prece;

    public CzinkocziKifejezesekKiertekelese() {
        init();
    }

    private void init() {
        verem = new ArrayList<>();
        prece = new HashMap<>();
        prece.put('*', 2);
        prece.put('/', 2);
        prece.put('+', 1);
        prece.put('-', 1);
        prece.put('(', 0);
        prece.put(')', 0);
    }

    private ArrayList<String> lengyelFormaraHozas() {
        ArrayList<String> lengyelforma = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String kifejezes = sc.nextLine();
        char aktElem;
        int i = 0;
        while (i < kifejezes.length()) {
            aktElem = kifejezes.charAt(i);
            if (aktElem == '(') {
                verem.add(aktElem + "");
            } else if (aktElem == '*' || aktElem == '/' || aktElem == '-' || aktElem == '+') {
                if (!verem.isEmpty()) {
                    verem.add(aktElem + "");
                } else {
                    while (!verem.isEmpty()) {
                        int j = 0;
                        while (j < prece.size() && prece.containsKey(verem.get(verem.size() - 1))) {
                            j++;
                        }
                        int veremPrec = prece.get(j).intValue();
                        if (veremPrec >= prece.get(aktElem).intValue()) {
                            lengyelforma.add(verem.get(verem.size() - 1));
                            verem.remove(verem.size() - 1);
                        }
                    }
                    verem.add(aktElem + "");
                }
            } else if (aktElem == ')') {
                int k = verem.size();
                while (!verem.get(k).equals('(')) {
                    lengyelforma.add(verem.get(k));
                    verem.remove(k);
                    k--;
                }
            } else {
                lengyelforma.add(aktElem + "");
            }
            
        }
        while (verem.size() > 0) {            
            lengyelforma.add(verem.get(verem.size()-1));
            verem.remove(verem.size()-1);
        }
        return lengyelforma;
    }

    private double kiszamitas(ArrayList<String> lengyelForma) {
        while (lengyelForma.size() > 0) {
            char elem = lengyelForma.get(0).charAt(0);
            if (!(elem == '+' || elem == '-' || elem == '/' || elem == '*')) {
                verem.add(elem + "");
            }
            if (elem == '+' || elem == '-' || elem == '/' || elem == '*') {
                double ertek1 = Double.parseDouble(verem.get(0));
                double ertek2 = Double.parseDouble(verem.get(1));
                double eredmeny = kiszamol(ertek2, elem, ertek1);
                verem.add(eredmeny + "");
            }
        }
        double vegeredmeny = Double.parseDouble(verem.get(0));
        System.out.println(vegeredmeny);
        return vegeredmeny;
    }

    private double kiszamol(double ertek2, char elem, double ertek1) {
        double osszeg = 0;
        if (elem == '+') {
            osszeg = ertek2 + ertek1;
        }
        if (elem == '-') {
            osszeg = ertek2 - ertek1;
        }
        if (elem == '/') {
            osszeg = ertek2 * ertek1;
        }
        if (elem == '*') {
            osszeg = ertek2 / ertek1;
        }
        return osszeg;
    }

    public static void main(String[] args) {
        CzinkocziKifejezesekKiertekelese program = new CzinkocziKifejezesekKiertekelese();
        program.kiszamitas(program.lengyelFormaraHozas());
    }

}
