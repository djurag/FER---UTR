package hr.fer.utr.lab5;


public class Touring {

    private static String trStanje;
    private static Integer trPolozaj;
    private static boolean prihv;

    public static void simuliraj() {
        trStanje = SimTS.pocetno;
        trPolozaj = SimTS.pocetnipolozaj;
        while (true) {
            if (SimTS.prijelazi.get(trStanje + "," + SimTS.zapisstroja[trPolozaj]) != null) {
                String prijelaz = SimTS.prijelazi.get(trStanje + "," + SimTS.zapisstroja[trPolozaj]);
                trStanje = prijelaz.split(",")[0];
                SimTS.zapisstroja[trPolozaj] = prijelaz.trim().split(",")[1].toCharArray()[0];
                if (prijelaz.split(",")[2].equals("R") && trPolozaj == 69) {
                    trPolozaj = 69;
                    break;
                } else if (prijelaz.split(",")[2].equals("L") && trPolozaj == 0) {
                    trPolozaj = 0;
                    break;
                }
                if (prijelaz.split(",")[2].equals("L"))
                    trPolozaj--;
                else
                    trPolozaj++;
            } else {
                break;
            }
        }
        prihv = SimTS.prihvatljiva.contains(trStanje);
        ispis();
    }

    private static void ispis() {
        if (prihv) {
            System.out.println(trStanje + "|" + trPolozaj + "|" + ispisTrake() + "|" + "1");

        } else {
            System.out.println(trStanje + "|" + trPolozaj + "|" + ispisTrake() + "|" + "0");

        }
    }

    private static String ispisTrake() {
        StringBuilder m = new StringBuilder();
        for (char x : SimTS.zapisstroja) {
            m.append(x);
        }

        return m.toString();
    }

}
