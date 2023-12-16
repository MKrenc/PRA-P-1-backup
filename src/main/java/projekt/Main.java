package projekt;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            String jsonFilePath = "src/main/resources/miasta.json";
            String chosenCity;
            String temperature = "", pressure = "", humidity = "";
            Map<String, Coordinates> cityCoordinatesMap = CityCoordinatesLoader.loadCityCoordinates(jsonFilePath);
            List<String> miasta = new ArrayList<>(cityCoordinatesMap.keySet());

            Set<String> formatyZapisu = new HashSet<>(Arrays.asList("PDF", "JSON", "XML"));
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

                        temperature = getAttributeValue(doc, "temperature", "value");
                        pressure = getAttributeValue(doc, "pressure", "value");
                        humidity = getAttributeValue(doc, "humidity", "value");

                        System.out.println("Temperatura: " + temperature + "°C");
                        System.out.println("Ciśnienie: " + pressure + " hPa");
                        System.out.println("Wilgotność: " + humidity + "%");
                        break;
                    case "Z":
                        if (wybierzFormatIZapisz(scan, formatyZapisu, temperature, pressure, humidity)) {
                            return;
                        }
                        break;
                    default:
                        System.out.println("Podano niedostępną opcję!");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    private static boolean wybierzFormatIZapisz(Scanner scan, Set<String> formatyZapisu, String temperature, String pressure, String humidity) {
        while (true) {
            System.out.print("Podaj format zapisu [PDF, JSON, XML]: ");
            String opcja2 = scan.nextLine();
            if (formatyZapisu.contains(opcja2)) {
                System.out.println("Zapis w formacie " + opcja2);
                String danePogodowe = temperature + "°C, " + pressure + "hPa, " + humidity + "%";
                if (Objects.equals(opcja2, "PDF")) {
                    DataSaver2.saveAsPdf(danePogodowe, "src/main/resources/pogoda.pdf");
                } else if (Objects.equals(opcja2, "JSON")) {
                    DataSaver2.saveAsJson(danePogodowe, "src/main/resources/pogoda.json");
                } else if (Objects.equals(opcja2, "XML")) {
                    Document weatherDoc = XmlCreator.createWeatherDocument(temperature, pressure, humidity);
                    if (weatherDoc != null) {
                        DataSaver2.saveAsXml(weatherDoc, "src/main/resources/pogoda.xml");
                    }
                }
                return true;
            } else {
                System.out.println("Podano niewłaściwy format!");
            }
        }
    }

    private static String getAttributeValue(Document doc, String tagName, String attribute) {
        NodeList nodeList = doc.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            return element.getAttribute(attribute);
        }
        return "";
    }

}

