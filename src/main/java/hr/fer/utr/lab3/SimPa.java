package hr.fer.utr.lab3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SimPa {
    public static String pocetno;
    public static Character pocetnostoga;
    public static List<String> ulazninizovi;
    public static List<String> skupstanja;
    public static List<String> abeceda;
    public static List<String> abecedastoga;
    public static SortedSet<String> prihvatljiva;
    public static LinkedHashMap<String, String> prijelazi;

    public static void main(String[] args) throws IOException {
        inicijaliziraj();

        /*
         * BufferedReader read = new BufferedReader(new
         * InputStreamReader(System.in));
         */
        FileReader citaj = new FileReader(
                new File("C:/Users/darko/Desktop/Darko/FER/4. semestar/UTR/Labos/lab3/test/test20/primjer.in"));
        BufferedReader read = new BufferedReader(citaj);

        String[] s = read.readLine().trim().split("\\|");
        s = getStrings(read, s, ulazninizovi, skupstanja);
        s = getStrings(read, s, abeceda, abecedastoga);
        Collections.addAll(prihvatljiva, s);
        pocetno = read.readLine();
        String x;
        x = read.readLine();
        pocetnostoga = x.charAt(0);

        String stringcitaj;
        while ((stringcitaj = read.readLine()) != null) {
            prijelazi.put(stringcitaj.split("->")[0], stringcitaj.split("->")[1]);
        }
        PA.simuliraj();

        read.close();

    }

    private static String[] getStrings(BufferedReader read, String[] s, List<String> ulazninizovi, List<String> skupstanja) throws IOException {
        Collections.addAll(ulazninizovi, s);
        s = read.readLine().split(",");
        Collections.addAll(skupstanja, s);
        s = read.readLine().split(",");
        return s;
    }

    private static void inicijaliziraj() {
        ulazninizovi = new LinkedList<>();
        skupstanja = new LinkedList<>();
        abeceda = new LinkedList<>();
        abecedastoga = new LinkedList<>();
        prihvatljiva = new TreeSet<>();
        prijelazi = new LinkedHashMap<>();

    }

}
