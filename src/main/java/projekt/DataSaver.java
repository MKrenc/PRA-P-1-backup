package projekt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class DataSaver {

    public static void saveDataAsJson(String data, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ObjectNode dataNode = mapper.createObjectNode();
            dataNode.put("weatherData", data);
            mapper.writeValue(new File(filePath), dataNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
