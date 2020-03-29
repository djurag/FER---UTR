package hr.fer.utr.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Darko
 */

public class SimNka {
    public static String pocetno;
    public static ArrayList<String> ulaz;
    public static TreeSet<String> skupstanja;
    public static TreeSet<String> abeceda;
    public static TreeSet<String> prihvatljiva;
    public static LinkedHashMap<String, String> prijelazi;

    public static void main(String[] args) throws IOException {
        inicijalizacija();

        BufferedReader read = new BufferedReader(new
                InputStreamReader(System.in));
        String[] s = read.readLine().trim().split("\\|");
        Collections.addAll(ulaz, s);
        s = read.readLine().split(",");
        skupstanja.addAll(Arrays.asList(s));
        s = read.readLine().split(",");
        abeceda.addAll(Arrays.asList(s));
        s = read.readLine().split(",");
        Collections.addAll(prihvatljiva, s);

        String pom;
        pom = read.readLine();
        pocetno = pom;

        while ((pom = read.readLine()) != null) {
            prijelazi.put(pom.split("->")[0], pom.split("->")[1]);
        }

        for (String string : ulaz) {
            TreeSet<String> trenutni = new TreeSet<>();
            TreeSet<String> epsilon;
            TreeSet<String> povprijelaz;
            String[] znakovi = string.split(",");
            trenutni.add(pocetno);
            TreeSet<String> sviprijelazi = new TreeSet<>(trenutni);
            int prosliPov = 0;
            while (true) {
                epsilon = dohvatiE(sviprijelazi);
                if (epsilon.size() == 0) {
                    break;
                } else if (epsilon.size() == prosliPov) {
                    break;
                }
                prosliPov = epsilon.size();
                sviprijelazi.addAll(epsilon);
            }
            trenutni.addAll(epsilon);
            trenutni.addAll(sviprijelazi);
            print(sviprijelazi);
            System.out.print("|");

            for (int i = 0; i < znakovi.length; ++i) {
                sviprijelazi.removeAll(sviprijelazi);
                povprijelaz = dohvatiprijelaze(trenutni, znakovi[i]);
                sviprijelazi.addAll(povprijelaz);
                prosliPov = 0;
                if (povprijelaz.size() != 0) {
                    while (true) {
                        epsilon = dohvatiE(sviprijelazi);
                        if (epsilon.size() == 0) {
                            break;
                        } else if (epsilon.size() == prosliPov) {
                            break;
                        }
                        prosliPov = epsilon.size();
                        sviprijelazi.addAll(epsilon);
                    }
                    if (sviprijelazi.contains("#") && sviprijelazi.size() != 1) {
                        sviprijelazi.remove("#");
                    }
                    print(sviprijelazi);
                    if (i != znakovi.length - 1) {
                        System.out.print("|");
                    }
                    trenutni.clear();
                    trenutni.addAll(sviprijelazi);
                } else {
                    trenutni.clear();
                    trenutni.add("#");
                    System.out.print("|#");
                }

            }
            System.out.println();

        }
        read.close();
    }

    private static TreeSet<String> dohvatiprijelaze(TreeSet<String> trenutni, String string) {
        TreeSet<String> pov = new TreeSet<>();
        for (int i = 0; i < trenutni.size(); ++i) {
            String prijelaz = trenutni.toArray()[i].toString().concat(",").concat(string);
            if (prijelazi.containsKey(prijelaz)) {
                String nova = prijelazi.get(prijelaz);
                pov.addAll(Arrays.asList(nova.split(",")));
            } else {
                pov.add("#");
            }
        }
        return pov;
    }

    private static void print(TreeSet<String> trenutna) {
        for (int i = 0; i < trenutna.size(); ++i) {
            System.out.print(trenutna.toArray()[i]);
            if (i != trenutna.size() - 1) {
                System.out.print(",");
            }
        }
    }

    private static TreeSet<String> dohvatiE(TreeSet<String> trenutna) {
        TreeSet<String> pov = new TreeSet<>();
        for (int i = 0; i < trenutna.size(); ++i) {
            String prijelaz = trenutna.toArray()[i].toString().concat(",$");
            if (prijelazi.containsKey(prijelaz)) {
                String nova = prijelazi.get(prijelaz);
                pov.addAll(Arrays.asList(nova.split(",")));
            }
        }
        return pov;
    }

    private static void inicijalizacija() {
        ulaz = new ArrayList<>();
        skupstanja = new TreeSet<>();
        abeceda = new TreeSet<>();
        prihvatljiva = new TreeSet<>();
        prijelazi = new LinkedHashMap<>();

    }

}
