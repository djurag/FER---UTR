package hr.fer.utr.lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SimTS {
    public static SortedSet<String> skupstanja;
    public static List<String> ulazniznakovi;
    public static List<String> skupznakovatrake;
    public static String praznacelija;
    public static char[] zapisstroja;
    public static SortedSet<String> prihvatljiva;
    public static String pocetno;
    public static Integer pocetnipolozaj;
    public static LinkedHashMap<String, String> prijelazi;

    public static void main(String[] args) throws IOException {
        inicijaliziraj();

//		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String datoteka="C:/Users/darko/Desktop/Darko/FER/4. semestar/UTR/Labos/lab5/test/test01/test.in";
        FileReader citaj = new FileReader(
                new File(datoteka));
        BufferedReader read = new BufferedReader(citaj);

        String[] s = read.readLine().split(",");
        Collections.addAll(skupstanja, s);
        s = read.readLine().split(",");
        Collections.addAll(ulazniznakovi, s);
        s = read.readLine().split(",");
        Collections.addAll(skupznakovatrake, s);
        praznacelija = read.readLine();
        String stringcitaj = read.readLine();
        zapisstroja = stringcitaj.toCharArray();
        s = read.readLine().split(",");
        Collections.addAll(prihvatljiva, s);
        pocetno = read.readLine();

        stringcitaj = read.readLine();

        pocetnipolozaj = Integer.parseInt(stringcitaj);

        while ((stringcitaj = read.readLine()) != null) {
            prijelazi.put(stringcitaj.split("->")[0], stringcitaj.split("->")[1]);
        }

        //Provjera.provjeri(datoteka);

        Touring.simuliraj();

        read.close();

    }

    private static void inicijaliziraj() {
        skupstanja = new TreeSet<>();
        ulazniznakovi = new LinkedList<>();
        skupznakovatrake = new LinkedList<>();
        prihvatljiva = new TreeSet<>();
        prijelazi = new LinkedHashMap<>();

    }

}
