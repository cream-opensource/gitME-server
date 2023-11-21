package gitME.common.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RestUtil {

    public static String get(String url, String accessToken) {
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + accessToken);
        headers.add("Accept", "application/json");

        // Set Entity (GET 요청의 경우 본문이 없으므로 null로 설정)
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        // Request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        return response.getBody();
    }

    public static String post(String url, MultiValueMap<String, String> body) {
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        // Set Entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // Request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);


        return response.getBody();
    }
}
