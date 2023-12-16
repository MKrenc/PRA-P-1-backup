package projekt;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlCreator {

    public static Document createWeatherDocument(String temperature, String pressure, String humidity) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("WeatherData");
            doc.appendChild(rootElement);

            appendWeatherElement(doc, rootElement, "Temperatura", temperature);
            appendWeatherElement(doc, rootElement, "Ciśnienie", pressure);
            appendWeatherElement(doc, rootElement, "Wilgotność", humidity);

            return doc;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void appendWeatherElement(Document doc, Element parent, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        parent.appendChild(element);
    }
}
