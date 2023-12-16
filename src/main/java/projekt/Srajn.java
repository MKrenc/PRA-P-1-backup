package projekt;

import org.w3c.dom.Document;
public class Srajn {
    public static void main(String[] args) {
        String temperature = "20";
        String pressure = "1013";
        String humidity = "80";

        Document weatherDoc = XmlCreator.createWeatherDocument(temperature, pressure, humidity);

        if (weatherDoc != null) {
            DataSaver2.saveAsXml(weatherDoc, "src/main/resources/pogoda.xml");
        }
    }
}
