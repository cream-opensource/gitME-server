package gitME.uncategorized.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gitME.global.util.JsonUtil;
import gitME.global.util.RestUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InterlinkService {

    public List<Map<String, Object>> getUserRepositories(String accessToken) {
//        String url = "https://api.github.com/users/특정유저/repos"; b유저의 레포를 a유저의 토큰으로도 들고올 수 있음...
        String url = "https://api.github.com/user/repos";

        String response = RestUtil.get(url, accessToken);
        JsonArray jsonArray = JsonUtil.parseJsonArrayString(response);
        List<Map<String, Object>> mapList = JsonUtil.jsonArrayToMapList(jsonArray);

        for (Map<String, Object> map : mapList) {
            Object nameValue = map.get("name");
            if (nameValue != null) {
                System.out.println(nameValue); // name 키에 해당하는 값을 출력
            } else {
                System.out.println("Name key is missing in this map");
            }
        }
        return mapList;
    }

    public List<Map<String, Object>> getLangByRepo(String accessToken, List<Map<String, Object>> repositories) {

        List<Map<String, Object>> list = new ArrayList<>();

        for (Map<String, Object> repository : repositories) {
            Object repoName = repository.get("name");

            Map<String, Object> Owner = (Map<String, Object>) repository.get("owner");
            Object userName = Owner.get("login");

            String url = "https://api.github.com/repos/" + userName + "/" + repoName + "/languages";
            String response = RestUtil.get(url, accessToken);
            JsonObject jsonObject = JsonUtil.parseJsonObjectString(response);
            Map<String, Object> objectMap = JsonUtil.jsonObjectToMap(jsonObject);
            list.add(objectMap);

        }

        return list;
    }
}
