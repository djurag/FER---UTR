package hr.fer.utr.lab3;

import java.util.Stack;

public class PA {
    public static String stanje;
    public static char vrhstoga;
    public static String ispis;
    public static Stack<Character> stog;

    public static void simuliraj() {
        stog = new Stack<>();
        for (String ulaz : SimPa.ulazninizovi) {
            stog.push(SimPa.pocetnostoga);
            stanje = SimPa.pocetno;
            vrhstoga = stog.peek();
            ispis = stanje + "#" + vrhstoga + "|";
            String[] niz = ulaz.split(",");
            boolean ispravno = false;

            for (int k = 0; k < niz.length; k++) {
                if (SimPa.prijelazi.containsKey(stanje + ",$," + vrhstoga)) {
                    String noviPr = SimPa.prijelazi.get(stanje + ",$," + vrhstoga);
                    stanje = SimPa.prijelazi.get(stanje + ",$," + vrhstoga).split(",")[0];
                    staviNaStog(noviPr.split(",")[1]);
                    ispis += stanje + "#" + ispisStoga(stog) + "|";
                    vrhstoga = stog.peek();
                    k--;
                } else if (SimPa.prijelazi.containsKey(stanje + "," + niz[k] + "," + vrhstoga)) {
                    String noviPr = SimPa.prijelazi.get(stanje + "," + niz[k] + "," + vrhstoga);
                    stanje = SimPa.prijelazi.get(stanje + "," + niz[k] + "," + vrhstoga).split(",")[0];
                    staviNaStog(noviPr.split(",")[1]);
                    ispis += stanje + "#" + ispisStoga(stog) + "|";
                    vrhstoga = stog.peek();
                } else {
                    ispis += "fail|0";
                    ispravno = true;
                    break;
                }
            }
            boolean promjena = true;
            if (SimPa.prihvatljiva.contains(stanje)) {
                promjena = false;
            }
            while (promjena) {
                promjena = provjeriE();
                if (SimPa.prihvatljiva.contains(stanje))
                    promjena = false;
            }

            if (!ispravno) {

                if (SimPa.prihvatljiva.contains(stanje))
                    ispis += "1";
                else
                    ispis += "0";
            }

            stog.clear();
            System.out.println(ispis);
            ispis = "";
        }
    }

    private static void staviNaStog(String znakovinastogu) {
        String novo = new StringBuilder(znakovinastogu).reverse().toString();
        if (!stog.isEmpty())
            stog.pop();
        for (int i = 0; i < novo.length(); i++) {
            stog.push(novo.charAt(i));
        }
        if (novo.charAt(0) == '$') {
            stog.pop();
        }
        if (stog.isEmpty())
            stog.push('$');
    }

    private static String ispisStoga(Stack<Character> stog2) {
        StringBuilder m = new StringBuilder();
        StringBuilder str = new StringBuilder();
        if (stog2.isEmpty()) {
            str.append("$");
            return str.toString();
        }
        for (Character x : stog2) {
            m.append(x);
        }
        char[] x = m.toString().toCharArray();

        for (int i = x.length - 1; i >= 0; --i) {
            str.append(x[i]);
        }
        return str.toString();
    }

    private static boolean provjeriE() {
        boolean promjena = false;
        if (SimPa.prijelazi.containsKey(stanje + ",$," + vrhstoga)) {
            String noviPr = SimPa.prijelazi.get(stanje + ",$," + vrhstoga);
            String znakovistoga = noviPr.split(",")[1];
            staviNaStog(znakovistoga);
            stanje = noviPr.split(",")[0];
            ispis += stanje + "#" + ispisStoga(stog) + "|";
            promjena = true;
            vrhstoga = stog.peek();
        }
        return promjena;
    }

}
