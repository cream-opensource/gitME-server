package gitME.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static JsonObject parseStringToJsonObject(String jsonString) {
        // String을 JSON 객체로 변환
        return gson.fromJson(jsonString, JsonObject.class);
    }

    public static JsonArray parseStringToJsonArray(String jsonString) {
        // String을 JSON 배열로 변환
        return gson.fromJson(jsonString, JsonArray.class);
    }

    public static List<Map<String, Object>> parseJsonArrayToMapList(JsonArray jsonArray) {
        Type listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
        return gson.fromJson(jsonArray, listType);
    }

    public static Map<String, Object> parseJsonObjectToMap(JsonObject jsonObject) {
        Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(jsonObject, mapType);
    }
}