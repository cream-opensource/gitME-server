package gitME.global.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static JsonObject parseJsonObjectString(String jsonString) {
        // String을 JSON 객체로 변환
        return gson.fromJson(jsonString, JsonObject.class);
    }

    public static JsonArray parseJsonArrayString(String jsonString) {
        // String을 JSON 배열로 변환
        return gson.fromJson(jsonString, JsonArray.class);
    }

    public static List<Map<String, Object>> jsonArrayToMapList(JsonArray jsonArray) {
        Type listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
        return gson.fromJson(jsonArray, listType);
    }

    public static Map<String, Object> jsonObjectToMap(JsonObject jsonObject) {
        Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(jsonObject, mapType);
    }
}