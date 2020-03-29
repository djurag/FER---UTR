package hr.fer.utr.lab4;


public class Parsiraj {

    public static boolean S() {
        System.out.print("S");
        if (Parser.ulaz.size() != 0) {
            char x = Parser.gramatika.get('S').split("\\|")[0].charAt(0);
            if (Parser.ulaz.get(0).equals(x)) {
                Parser.ulaz.remove(0);
                if (A()) {
                    return B();
                }
            }

        } else {
            return false;
        }
        if (Parser.ulaz.size() != 0) {
            char x = Parser.gramatika.get('S').split("\\|")[1].charAt(0);
            if (Parser.ulaz.get(0).equals(x)) {
                Parser.ulaz.remove(0);
                if (B()) {
                    return A();
                }
            }

        } else {
            return false;
        }
        return false;
    }

    public static boolean A() {
        System.out.print("A");

        if (Parser.ulaz.size() != 0) {
            char x = Parser.gramatika.get('A').split("\\|")[0].charAt(0);
            if (Parser.ulaz.get(0).equals(x)) {
                Parser.ulaz.remove(0);
                return C();
            }

        } else {
            return false;
        }
        char x = Parser.gramatika.get('A').split("\\|")[1].charAt(0);
        if (Parser.ulaz.get(0).equals(x)) {
            Parser.ulaz.remove(0);
            return true;
        }
        return false;
    }

    public static boolean B() {
        System.out.print("B");
        if (Parser.ulaz.size() != 0) {
            char x = Parser.gramatika.get('B').split("\\|")[0].charAt(0);
            if (Parser.ulaz.get(0).equals(x)) {
                Parser.ulaz.remove(0);
                x = Parser.gramatika.get('B').split("\\|")[0].charAt(1);
                if (Parser.ulaz.size() != 0 && Parser.ulaz.get(0).equals(x)) {
                    Parser.ulaz.remove(0);
                    x = Parser.gramatika.get('B').split("\\|")[0].charAt(3);
                    if (S() && Parser.ulaz.size() != 0 && Parser.ulaz.get(0).equals(x)) {
                        Parser.ulaz.remove(0);
                        x = Parser.gramatika.get('B').split("\\|")[0].charAt(4);
                        if (Parser.ulaz.size() != 0 && Parser.ulaz.get(0).equals(x)) {
                            Parser.ulaz.remove(0);
                            return true;

                        } else {

                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean C() {
        System.out.print("C");
        if (A())
            return A();
        return false;
    }
}

