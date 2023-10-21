package gitME.global.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static JsonObject parseJsonObjectString(String jsonString){
        JsonParser parser = new JsonParser();

        // String을 JSON 객체로 변환

        return parser.parse(jsonString).getAsJsonObject();
    }

    public static JsonArray parseJsonObjectArrayString(String jsonString){
        JsonParser parser = new JsonParser();

        // String을 JSON 배열로 변환
        return parser.parse(jsonString).getAsJsonArray();
    }

    public static List<Map<String, Object>> jsonArrayToMapList(JsonArray jsonArray){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
        return gson.fromJson(jsonArray, listType);
    }

    public static Map<String, Object> jsonObjectToMap(JsonObject jsonObject) {
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(jsonObject, mapType);
    }
}

