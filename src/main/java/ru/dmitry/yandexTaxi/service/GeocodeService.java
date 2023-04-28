package ru.dmitry.yandexTaxi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;


//Запрос на  Москву:::{"response":{"GeoObjectCollection":{"metaDataProperty":{"GeocoderResponseMetaData":{"boundedBy":{"Envelope":{"lowerCorner":"37.038186 55.312148","upperCorner":"38.2026 56.190802"}},"request":"Москва","results":"10","found":"1"}},"featureMember":[{"GeoObject":{"metaDataProperty":{"GeocoderMetaData":{"precision":"other","text":"Россия, Москва","kind":"province","Address":{"country_code":"RU","formatted":"Россия, Москва","Components":[{"kind":"country","name":"Россия"},{"kind":"province","name":"Центральный федеральный округ"},{"kind":"province","name":"Москва"}]},"AddressDetails":{"Country":{"AddressLine":"Россия, Москва","CountryNameCode":"RU","CountryName":"Россия","AdministrativeArea":{"AdministrativeAreaName":"Москва"}}}}},"name":"Москва","description":"Россия","boundedBy":{"Envelope":{"lowerCorner":"36.803268 55.142226","upperCorner":"37.967799 56.021286"}},"Point":{"pos":"37.617698 55.755864"}}}]}}}

public class GeocodeService {
    @Value("${api.key}")
    private String ApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getGeocodeCoordinates(String location) throws JsonProcessingException {
        return requestByGetCoordinate(location).get("response").get("GeoObjectCollection")
                .get("featureMember").get(0)
                .get("GeoObject").get("Point")
                .get("pos").toString()
                .replaceAll("\"", "");
    }


    private JsonNode requestByGetCoordinate(String location) throws JsonProcessingException {

        String url = "https://geocode-maps.yandex.ru/1.x/?format=json&apikey=" + ApiKey + "&geocode=" + location;

        String responseEntity = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readTree(responseEntity);
    }

}
