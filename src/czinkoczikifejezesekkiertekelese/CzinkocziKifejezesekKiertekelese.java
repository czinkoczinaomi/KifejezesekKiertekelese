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

        return lengyelforma;
    }
    
    
    private void kiszamitas(ArrayList<String> lengyelForma) {
        while(lengyelForma.size() > 0){
            String elem = lengyelForma.get(0);
            if (elem instanceof Integer) {
                verem.add(elem);
            }
            if (elem instanceof String) {
                String muvelet = ;
                int ertek1 = Integer.parseInt(verem.get(0));
                int ertek2 = Integer.parseInt(verem.get(1));
                double eredmeny = kiszamol(ertek2, muvelet, ertek1);
                verem.add(eredmeny);
            }
        }
        double vegeredmeny = Double.parseDouble(verem.get(0));
        System.out.println(vegeredmeny);
    }

    private double kiszamol(int ertek2, String muvelet, int ertek1) {
        double osszeg = 0;
        if (muvelet.equals("+")) {
            osszeg = ertek2 + ertek1;
        }
        if (muvelet.equals("-")) {
            osszeg = ertek2 - ertek1;
        }
        if (muvelet.equals("*")) {
            osszeg = ertek2 * ertek1;
        }
        if (muvelet.equals("/")) {
            osszeg =  ertek2 / ertek1;
        }
        return osszeg;
    }

    public static void main(String[] args) {


    }

}
