package projekt;

import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.ClientProtocolException;

class Main {
    public static void main(String[] args) {
        String jsonFilePath = "src/main/resources/miasta.json";
        String chosenCity;
        Map<String, Coordinates> cityCoordinatesMap = CityCoordinatesLoader.loadCityCoordinates(jsonFilePath);
        List<String> miasta = new ArrayList<>(cityCoordinatesMap.keySet());
        // W przyszłości być może obsługa wyjątków związanych z odczytem

        Set<String> formatyZapisu = new HashSet<>(Arrays.asList("PDF", "JSON", "XML"));
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("P-Podaj miasto, Z-Zakończ");
            String opcja1 = scan.nextLine();

            switch (opcja1) {
                case "P":
                    chosenCity =  wybierzMiastoIWyswietlDane(scan, miasta);
                    break;
                case "Z":
                    if (wybierzFormatIZapisz(scan, formatyZapisu)) {
                        return;
                    }
                    break;
                default:
                    System.out.println("Podano niedostępną opcję!"); // Można tutaj rzucić wyjątek, jeśli to konieczne
                    break;
            }
        }
    }

    private static String wybierzMiastoIWyswietlDane(Scanner scan, List<String> miasta) {
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
        return miasto;
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

