package projekt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "current")
public class WeatherData {
    private Temperature temperature;
    private Humidity humidity;
    private Pressure pressure;

    // Gettery
    public Temperature getTemperature() {
        return temperature;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public Pressure getPressure() {
        return pressure;
    }

    // Settery
    @XmlElement(name = "temperature")
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @XmlElement(name = "humidity")
    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    @XmlElement(name = "pressure")
    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }
}

class Temperature {
    private double value;

    // Getter
    public double getValue() {
        return value;
    }

    // Setter
    @XmlElement(name = "value")
    public void setValue(double value) {
        this.value = value;
    }
}

class Humidity {
    private int value;

    // Getter
    public int getValue() {
        return value;
    }

    // Setter
    @XmlElement(name = "value")
    public void setValue(int value) {
        this.value = value;
    }
}

class Pressure {
    private int value;

    // Getter
    public int getValue() {
        return value;
    }

    // Setter
    @XmlElement(name = "value")
    public void setValue(int value) {
        this.value = value;
    }
}