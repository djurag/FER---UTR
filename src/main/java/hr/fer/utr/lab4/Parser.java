package hr.fer.utr.lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Parser {
    public static ArrayList<Character> ulaz;
    public static LinkedHashMap<Character, String> gramatika;

    public static void main(String[] args) throws IOException {

        BufferedReader read = new BufferedReader(new
                InputStreamReader(System.in));

        inicijaliziraj();

        postavigramatiku();
        String ulaz_str;
        ulaz_str = read.readLine();
        for (int y = 0; y < ulaz_str.length(); ++y) {
            ulaz.add(ulaz_str.charAt(y));
        }
        //Provjera.provjeri(datoteka);

        boolean OK = Parsiraj.S();

        System.out.println();
        if (ulaz.size() == 0 && OK) {
            System.out.println("DA");
        } else
            System.out.println("NE");

        read.close();

    }

    private static void postavigramatiku() {
        gramatika.put('S', "aAB|bBA");
        gramatika.put('A', "bC|a");
        gramatika.put('B', "ccSbc|$");
        gramatika.put('C', "AA");
    }

    private static void inicijaliziraj() {
        gramatika = new LinkedHashMap<>();
        ulaz = new ArrayList<>();

    }

}
