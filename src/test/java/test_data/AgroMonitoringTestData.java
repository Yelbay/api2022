package test_data;

import java.util.HashMap;
import java.util.Map;

public class AgroMonitoringTestData {

    public float coordinates[][][] = {{{-121.1958F, 37.6683F},
            {-121.1779F, 37.6687F},
            {-121.1773F, 37.6792F},
            {-121.1958F, 37.6792F},
            {-121.1958F, 37.6683F}}};


    public Map<String, Object> geometrySetUp() {
        Map<String, Object> geometry = new HashMap<>();
        geometry.put("type", "Polygon");
        geometry.put("coordinates", coordinates);
        return geometry;
    }

    Map<String, Object> properties = new HashMap<>();

    public Map<String, Object> geo_jsonSetUp() {
        Map<String, Object> geo_json = new HashMap<>();

        geo_json.put("type", "Feature");
        geo_json.put("properties", properties);
        geo_json.put("geometry", geometrySetUp());

        return geo_json;
    }

    public float center[] = {-121.1867F, 37.67385F};

    public Map<String, Object> requestBody() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("geo_json", geo_jsonSetUp());
        requestBody.put("name", "Polygon Sample");
        requestBody.put("center", center);
        requestBody.put("area", 190.9484);

        return requestBody;
    }


}