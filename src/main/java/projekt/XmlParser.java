package projekt;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class XmlParser {

    public static WeatherData parseWeatherData(String xmlData) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(WeatherData.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xmlData);
        return (WeatherData) unmarshaller.unmarshal(reader);
    }
}
