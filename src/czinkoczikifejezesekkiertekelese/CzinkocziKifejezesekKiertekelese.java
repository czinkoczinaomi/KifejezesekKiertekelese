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

    /**
     * @param args the command line arguments
     */
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

    public static void main(String[] args) {

    }

}
