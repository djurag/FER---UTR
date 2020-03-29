package hr.fer.utr.lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinDka {
    public static String pocetno;
    public static SortedSet<String> skupstanja;
    public static SortedSet<String> abeceda;
    public static SortedSet<String> prihvatljiva;
    public static LinkedHashMap<String, String> dohvatljiviprijelazi;
    public static SortedSet<String> prijelazi;
    public static TreeSet<String> dohvatljiva;
    public static LinkedHashMap<String, String> lista;
    public static LinkedHashMap<String, String> oznacena;

    public static void main(String[] args) throws IOException {
        inicijaliziraj();

        BufferedReader read = new BufferedReader(new
                InputStreamReader(System.in));

        String[] s = read.readLine().split(",");
        skupstanja.addAll(Arrays.asList(s));
        s = read.readLine().split(",");
        abeceda.addAll(Arrays.asList(s));
        s = read.readLine().split(",");
        Collections.addAll(prihvatljiva, s);
        String pom;
        pom = read.readLine();
        pocetno = pom;
        while ((pom = read.readLine()) != null) {
            prijelazi.add(pom);
        }
        rijesiSeNedohvatljivih();
        prebaciUHash();

        min();
        vratiIzHash();
        ispis();
        read.close();

    }

    private static void min() {
        ArrayList<String> stanja = new ArrayList<>(skupstanja);
        oznacirazlicituprihvatljivost(stanja);
        for (int n = 0; n < skupstanja.size() - 1; ++n) {
            for (int m = 0; m < skupstanja.size(); ++m) {
                for (String ab : abeceda) {
                    String st1 = dohvatljiviprijelazi.get(stanja.get(n) + "," + ab);
                    String st2 = dohvatljiviprijelazi.get(stanja.get(m) + "," + ab);
                    if (st1.compareTo(st2) > 0) {
                        String pom;
                        pom = st1;
                        st1 = st2;
                        st2 = pom;
                    }
                    if (!oznacena.containsKey(st1 + "," + st2)) {
                        lista.put(st1 + "," + st2, stanja.get(n) + "," + stanja.get(m));

                    } else {
                        String key = stanja.get(n) + "," + stanja.get(m);
                        oznacena.put(key, "X");
                    }
                }
            }
        }
        provjerilistu();

        TreeSet<String> mogucnosti = new TreeSet<>();
        ArrayList<String> lstanja = new ArrayList<>(skupstanja);
        for (int i = 0; i < skupstanja.size() - 1; ++i) {
            for (int j = i + 1; j < skupstanja.size(); ++j) {
                mogucnosti.add(lstanja.get(i) + "," + lstanja.get(j));
            }
        }
        TreeSet<String> ista = new TreeSet<>();
        for (String moguceista : mogucnosti) {
            if (!oznacena.containsKey(moguceista)) {
                ista.add(moguceista);
            }
        }
        makniIsta(dohvatljiviprijelazi, ista);

    }

    private static void oznacirazlicituprihvatljivost(ArrayList<String> stanja) {
        for (int n = 0; n < skupstanja.size() - 1; n++) {
            for (int m = n + 1; m < skupstanja.size(); m++) {
                if ((prihvatljiva.contains(stanja.get(n)) && !(prihvatljiva.contains(stanja.get(m))))
                        || ((!prihvatljiva.contains(stanja.get(n))) && prihvatljiva.contains(stanja.get(m)))) {
                    oznacena.put(stanja.get(n) + "," + stanja.get(m), "X");
                }
            }
        }

    }

    private static void provjerilistu() {
        int promjena = 0;
        do {
            for (String st : lista.keySet()) {
                if (oznacena.containsKey(st)) {
                    oznacena.put(lista.get(st), "X");
                    promjena++;
                }
            }
        } while (promjena <= lista.size() * 4 && promjena != 0);

    }

    private static void vratiIzHash() {
        prijelazi.clear();
        for (String x : dohvatljiviprijelazi.keySet()) {
            prijelazi.add(x + "->" + dohvatljiviprijelazi.get(x));
        }

    }

    private static void makniIsta(LinkedHashMap<String, String> dohvatljiviprijelazi2, TreeSet<String> ista) {
        for (String stanje : ista) {
            for (String ab : abeceda) {
                String isto = stanje.split(",")[1] + "," + ab;
                dohvatljiviprijelazi2.remove(isto);
                skupstanja.remove(stanje.split(",")[1]);
                prihvatljiva.remove(stanje.split(",")[1]);
                if (pocetno.equals(stanje.split(",")[1])) {
                    pocetno = stanje.split(",")[0];
                }
            }
        }
        for (String s : dohvatljiviprijelazi2.keySet()) {
            for (String isto : ista) {
                if (isto.split(",")[1].equals(dohvatljiviprijelazi2.get(s))) {
                    dohvatljiviprijelazi2.put(s, isto.split(",")[0]);
                }
            }
        }
    }

    private static void rijesiSeNedohvatljivih() {
        dohvatljiva(dohvatljiva, prijelazi);
        makninedohvatljiveprijelaze(prijelazi, dohvatljiva);
        promijeniskupstanja(skupstanja, dohvatljiva);
        makninedohvatljiva(prihvatljiva, skupstanja);

    }

    private static void ispis() {
        StringBuilder ispis = new StringBuilder();
        int i = 0;
        for (String pom : skupstanja) {
            if (i < skupstanja.size() - 1) {
                ispis.append(pom).append(",");
            } else {
                ispis.append(pom);
            }
            ++i;
        }
        System.out.println(ispis);
        ispis = new StringBuilder();
        i = 0;
        for (String pom : abeceda) {
            if (i < abeceda.size() - 1) {
                ispis.append(pom).append(",");
            } else {
                ispis.append(pom);
            }
            ++i;
        }
        System.out.println(ispis);
        ispis = new StringBuilder();
        i = 0;
        for (String pom : prihvatljiva) {
            if (i < prihvatljiva.size() - 1) {
                ispis.append(pom).append(",");
            } else {
                ispis.append(pom);
            }
            ++i;
        }
        System.out.println(ispis);
        System.out.println(pocetno);
        for (String pom : prijelazi) {
            System.out.println(pom);
        }

    }

    private static void dohvatljiva(TreeSet<String> dohvatljiva2, SortedSet<String> prijelazi2) {
        dohvatljiva2.add(pocetno);
        do {
            TreeSet<String> poma = new TreeSet<>();
            for (String x : dohvatljiva2) {
                for (int i = 0; i < abeceda.size(); ++i) {
                    for (String y : prijelazi2) {
                        if (y.split("->")[0].split(",")[0].equals(x)) {
                            if (!(dohvatljiva2.contains(y.split("->")[1]))) {
                                poma.add(y.split("->")[1]);
                            }
                        }
                    }
                }
            }
            if (poma.isEmpty())
                break;
            dohvatljiva2.addAll(poma);
            poma.clear();
        } while (true);
    }

    private static void makninedohvatljiva(SortedSet<String> prihvatljiva2,
                                           SortedSet<String> skupstanja2) {
        TreeSet<String> makniprihvatljiva = new TreeSet<>();
        for (String p : prihvatljiva2) {
            if (!(skupstanja2.contains(p)))
                makniprihvatljiva.add(p);
        }
        prihvatljiva2.removeAll(makniprihvatljiva);
    }

    private static void promijeniskupstanja(SortedSet<String> skupstanja2, TreeSet<String> dohvatljiva2) {
        skupstanja2.clear();
        skupstanja2.addAll(dohvatljiva2);
    }

    private static void makninedohvatljiveprijelaze(SortedSet<String> prijelazi2,
                                                    TreeSet<String> dohvatljiva2) {
        do {
            TreeSet<String> poma = new TreeSet<>();
            for (String z : prijelazi2) {
                if (!(dohvatljiva2.contains(z.split("->")[0].split(",")[0]))) {
                    poma.add(z);
                }
            }
            prijelazi2.removeAll(poma);
            poma.clear();
        } while (prijelazi2.size() != dohvatljiva2.size() * abeceda.size());
    }

    private static void prebaciUHash() {
        for (String x : prijelazi) {
            dohvatljiviprijelazi.put(x.split("->")[0], x.split("->")[1]);
        }

    }

    private static void inicijaliziraj() {
        lista = new LinkedHashMap<>();
        dohvatljiviprijelazi = new LinkedHashMap<>();
        oznacena = new LinkedHashMap<>();
        skupstanja = new TreeSet<>();
        abeceda = new TreeSet<>();
        prihvatljiva = new TreeSet<>();
        prijelazi = new TreeSet<>();
        dohvatljiva = new TreeSet<>();

    }

}
