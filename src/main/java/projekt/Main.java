package projekt;

import java.util.*;

class Main {
    public static void main(String[] args) {
        List<String> miasta = Arrays.asList("Warszawa", "Szczecin", "Łódź", "Poznań", "Białystok");
        // W przyszłości, wczytywanie miast z pliku JSON i obsługa wyjątków związanych z odczytem

        Set<String> formatyZapisu = new HashSet<>(Arrays.asList("PDF", "JSON", "XML"));
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("P-Podaj miasto, Z-Zakończ");
            String opcja1 = scan.nextLine();

            switch (opcja1) {
                case "P":
                    wybierzMiastoIWyswietlDane(scan, miasta);
                    break;
                case "Z":
                    if (wybierzFormatIZapisz(scan, formatyZapisu)) {
                        return; // Wyjście z programu po zapisie danych
                    }
                    break;
                default:
                    System.out.println("Podano niedostępną opcję!"); // Można tutaj rzucić wyjątek, jeśli to konieczne
                    break;
            }
        }
    }

    private static void wybierzMiastoIWyswietlDane(Scanner scan, List<String> miasta) {
        String miasto;
        while (true) {
            System.out.println("Dostępne miasta: " + String.join(", ", miasta));
            miasto = scan.nextLine();
            if (miasta.contains(miasto)) {
                // Pobierz i wyświetl dane pogodowe dla wybranego miasta
                break;
            } else {
                System.out.println("Nie można wyświetlić danych dla podanego miasta.");
            }
        }
    }

    private static boolean wybierzFormatIZapisz(Scanner scan, Set<String> formatyZapisu) {
        while (true) {
            System.out.print("Podaj format zapisu [P-PDF J-JSON X-XML]: ");
            String opcja2 = scan.nextLine();
            if (formatyZapisu.contains(opcja2)) {
                System.out.println("Wykonywanie instrukcji zapisu w formacie " + opcja2);
                // Zapisz dane w wybranym formacie
                return true;
            } else {
                System.out.println("Podano niewłaściwy format!");
            }
        }
    }
}
