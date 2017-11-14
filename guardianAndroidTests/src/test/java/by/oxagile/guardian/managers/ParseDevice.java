package by.oxagile.guardian.managers;


import by.oxagile.guardian.models.Device;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;



public class ParseDevice {
    Logger logger = LoggerFactory.getLogger(CarerManager.class);
    List<Device> list;
    String path = "src/test/resources/devices.json";

    public Device getDevice(String invent) {
        JsonParser parser = new JsonParser();
        Device device = null;
        Type listType = new TypeToken<List<Device>>(){}.getType();
        try {
            Object obj = parser.parse(new FileReader(path));
            JsonObject jsonObj = (JsonObject) obj;
            JsonArray devices = jsonObj.get("device").getAsJsonArray();
            list = new Gson().fromJson(devices, listType);

            for (int i = 0; i < list.size(); i++) {
                Device temp = list.get(i);
                if (temp.getInvent() != null) {
                    if (temp.getInvent().equals(invent)) {
                        device = temp;
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.debug("couldn't parse devices.json");
            e.printStackTrace();
        }

        return device;
    }

}
