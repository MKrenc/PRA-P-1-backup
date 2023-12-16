package projekt;

import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;



class Main {
    public static void main(String[] args) throws Exception {
        String jsonFilePath = "src/main/resources/miasta.json";
        String chosenCity;
        Map<String, Coordinates> cityCoordinatesMap = CityCoordinatesLoader.loadCityCoordinates(jsonFilePath);
        List<String> miasta = new ArrayList<>(cityCoordinatesMap.keySet());

        Set<String> formatyZapisu = new HashSet<>(Arrays.asList("P", "J", "X"));
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("P-Podaj miasto, Z-Zakończ");
            String opcja1 = scan.nextLine();

            switch (opcja1) {
                case "P":
                    chosenCity =  wybierzMiastoIWyswietlDane(scan, miasta);
                    WeatherService weatherService = new WeatherService();
                    Coordinates coords = cityCoordinatesMap.get(chosenCity);
                    String xmlString = weatherService.getWeatherData(String.valueOf(coords.getLat()), String.valueOf(coords.getLon()));
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));

                    doc.getDocumentElement().normalize();

                    NodeList temperatureNodeList = doc.getElementsByTagName("temperature");
                    Element temperatureElement = (Element) temperatureNodeList.item(0);
                    String temperature = temperatureElement.getAttribute("value");

                    NodeList pressureNodeList = doc.getElementsByTagName("pressure");
                    Element pressureElement = (Element) pressureNodeList.item(0);
                    String pressure = pressureElement.getAttribute("value");

                    NodeList humidityNodeList = doc.getElementsByTagName("humidity");
                    Element humidityElement = (Element) humidityNodeList.item(0);
                    String humidity = humidityElement.getAttribute("value");

                    System.out.println("Temperatura: " + temperature + "°C");
                    System.out.println("Ciśnienie: " + pressure + " hPa");
                    System.out.println("Wilgotność: " + humidity + "%");
                    break;
                case "Z":
                    if (wybierzFormatIZapisz(scan, formatyZapisu)) {
                        return;
                    }
                    break;
                default:
                    System.out.println("Podano niedostępną opcję!");
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
                // zapis
                return true;
            } else {
                System.out.println("Podano niewłaściwy format!");
            }
        }
    }
}

