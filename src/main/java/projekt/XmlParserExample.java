package projekt;

import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;


public class XmlParserExample {
    public static void main(String[] args) throws Exception {
        String jsonFilePath = "src/main/resources/miasta.json";
        Map<String, Coordinates> cityCoordinatesMap = CityCoordinatesLoader.loadCityCoordinates(jsonFilePath);
        String chosenCity =  "Warszawa";
        WeatherService weatherService = new WeatherService();
        Coordinates coords = cityCoordinatesMap.get(chosenCity);
        String xmlString = weatherService.getWeatherData(String.valueOf(coords.getLat()), String.valueOf(coords.getLon()));
        // String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<current><city id=\"2643743\" name=\"London\"><coord lon=\"-0.1257\" lat=\"51.5085\"></coord><country>GB</country><timezone>0</timezone><sun rise=\"2023-12-15T07:59:29\" set=\"2023-12-15T15:51:32\"></sun></city><temperature value=\"6.64\" min=\"3.65\" max=\"7.92\" unit=\"celsius\"></temperature><feels_like value=\"3.81\" unit=\"celsius\"></feels_like><humidity value=\"85\" unit=\"%\"></humidity><pressure value=\"1037\" unit=\"hPa\"></pressure><wind><speed value=\"4.12\" unit=\"m/s\" name=\"Gentle Breeze\"></speed><gusts></gusts><direction value=\"240\" code=\"WSW\" name=\"West-southwest\"></direction></wind><clouds value=\"99\" name=\"overcast clouds\"></clouds><visibility value=\"10000\"></visibility><precipitation mode=\"no\"></precipitation><weather number=\"804\" value=\"overcast clouds\" icon=\"04n\"></weather><lastupdate value=\"2023-12-15T23:41:46\"></lastupdate></current>";
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
    }
}
