package projekt;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class WeatherService {

    private static final String API_KEY = "d1c62029fc387217638fd3deda524ece";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static String getWeatherData(String lat, String lon) {
        String url = String.format("%s?lat=%s&lon=%s&appid=%s&units=metric&mode=xml", BASE_URL, lat, lon, API_KEY);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println(WeatherService.getWeatherData("53.1325", "23.1688"));
    }
}
