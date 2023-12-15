package projekt;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        ArrayList<String> miasta = new ArrayList<String>();
        miasta.add("Warszawa");
        miasta.add("Szczecin");
        miasta.add("Łódź");
        miasta.add("Poznań");
        miasta.add("Białystok"); // dodawanie miast, docelowo lista ma być wypełniana na podstawie czytanego pliku miasta.json, błąd z odczytem może być wykorzystany do realizacji kwestii obsługi wyjątków w programie
        String opcja1;
        String opcja2;
        ArrayList<String> opcje2 = new ArrayList<String>();
        opcje2.add("PDF");
        opcje2.add("JSON");
        opcje2.add("XML");
        String miasto;
        StringBuilder lanchuchProponowanychMiast = new StringBuilder();
        for (String s : miasta) {
            lanchuchProponowanychMiast.append(s); // kłopot ze złączeniem, przecinki ze spacją powinny być pomiędzy miastami
        }
        Scanner scan = new Scanner(System.in);
        boolean podano_miasto = false;
        while (true) {
            System.out.println("P-Podaj miasto, Z-Zakończ");
            opcja1 = scan.nextLine();
            if (Objects.equals(opcja1, "P")) {
                do {
                    System.out.print(String.format("Podaj miasto [%s]: ", lanchuchProponowanychMiast.toString()));
                    miasto = scan.nextLine();
                    if (!miasta.contains(miasto)) {
                        System.out.println("Nie można wyświetlić danych dla podanego miasta.");
                    }
                    else {
                        break;
                    }
                } while(true);
                podano_miasto = true;
            } else if (Objects.equals(opcja1, "Z")) {
                if (podano_miasto) {
                    do {
                        System.out.print("Podaj format zapisu [P-PDF J-JSON X-XML]: ");
                        opcja2 = scan.nextLine();
                        if (!opcje2.contains(opcja2)) {
                            System.out.println("Podano niewłaściwy format!");
                        }
                        else {
                            System.out.println("Wykonywanie instrukcji");
                            break;
                        }
                    } while(true);
                }
                break;
            }
            else {
                System.out.println("Podano niedostępną opcję!"); // m.in. to rozwiązanie zastąpić zgłoszeniem wyjątku
            }
        }
        System.out.println("Program kończy działanie.");
    }
}