package calc;

import java.util.*;

import java.util.stream.Collectors;

public class Calculator {

    public static void main(String[] args) {

        Converter con = new Converter();
        String a = "";
        String b = "";
        String c = "";
        int num1 = 0;
        int num2 = 0;
        int ar = 0;
        int r = 0;
        int ar2 = 0;

        String[] arab = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        };
        String[] rom = {
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"
        };

        System.out.println("Какой пример решить? ");

        while (true) {
            Scanner scanner = new Scanner(System.in);

            a = scanner.next();

            if(a.equals("end"))
                break;

            for (int i = 0; i < 10; i++) {
                if (a.equals(arab[i])) {
                    num1 = con.toArab(a);
                    ar++;

                } else if (a.equals(rom[i])) {
                    num1 = con.toRom(a);
                    r++;

                }
            }
            c = scanner.next();

            b = scanner.next();
            for (int i = 0; i < 10; i++) {
                if (b.equals(arab[i])) {
                    num2 = con.toArab(b);
                    ar++;

                } else if (b.equals(rom[i])) {
                    num2 = con.toRom(b);
                    r++ ;
                }
            }

            if (c.equals("+") && ar == 2) {
                System.out.println(num1 + num2);
            } else if (c.equals("+") && r == 2) {
                System.out.println(con.arabicToRoman(num1 + num2));
            } else if (c.equals("-") && ar == 2) {
                System.out.println(num1 - num2);
            } else if (c.equals("-") && r == 2) {
                System.out.println(con.arabicToRoman(num1 - num2));
            } else if (c.equals("*") && ar == 2) {
                System.out.println(num1 * num2);
            } else if (c.equals("*") && r == 2) {
                System.out.println(con.arabicToRoman(num1 * num2));
            } else if (c.equals("/") && ar == 2) {
                System.out.println(num1 / num2);
            } else if (c.equals("/") && r == 2) {
                System.out.println(con.arabicToRoman(num1 / num2));
            } else {
                throw new IllegalArgumentException("ERROR");

            }
            ar = 0;
            r = 0;
            System.out.println("Еще пример? Если хотите завершить введите end");
        }
    }
}

class Converter {
    public static int toRom(String v) {
        int n1 = 0;
        if (v.equals("I"))
            n1 = 1;
        if (v.equals("II"))
            n1 = 2;
        if (v.equals("III"))
            n1 = 3;
        if (v.equals("IV"))
            n1 = 4;
        if (v.equals("V"))
            n1 = 5;
        if (v.equals("VI"))
            n1 = 6;
        if (v.equals("VII"))
            n1 = 7;
        if (v.equals("VIII"))
            n1 = 8;
        if (v.equals("IX"))
            n1 = 9;
        if (v.equals("X"))
            n1 = 10;
        return n1;

    }

    public static int toArab(String v) {
        int n2 = 0;
        if (v.equals("1"))
            n2 = 1;
        if (v.equals("2"))
            n2 = 2;
        if (v.equals("3"))
            n2 = 3;
        if (v.equals("4"))
            n2 = 4;
        if (v.equals("5"))
            n2 = 5;
        if (v.equals("6"))
            n2 = 6;
        if (v.equals("7"))
            n2 = 7;
        if (v.equals("8"))
            n2 = 8;
        if (v.equals("9"))
            n2 = 9;
        if (v.equals("10"))
            n2 = 10;
        return n2;

    }

    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private final int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }
    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}