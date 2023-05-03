package ru.dmitry.yandexTaxi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.dmitry.yandexTaxi.entity.City;
import ru.dmitry.yandexTaxi.exception.GeocodeNotFoundException;

import java.util.Optional;


@Service
@Slf4j
public class GeocodeService {
    @Value("${api.key}")
    private String ApiKey;

    private final CityService cityService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public GeocodeService(CityService cityService) {
        this.cityService = cityService;
    }

    public City getGeocodeCoordinates(String location) throws JsonProcessingException {

        Optional<City> cityLocation = cityService.findByName(location);
        if(cityLocation.isPresent()) {
            return cityLocation.get();
        }

        JsonNode resp = requestByGetCoordinate(location);

        JsonNode jsonNode = resp.get("response").get("GeoObjectCollection")
                .get("metaDataProperty").get("GeocoderResponseMetaData");

        checkFoundGeocode(jsonNode, location);

        System.out.println("Ку");

        String nameCity = jsonNode.get("request").asText();

        String coordinates = resp.get("response").get("GeoObjectCollection")
                .get("featureMember").get(0)
                .get("GeoObject").get("Point")
                .get("pos").asText()
                .replaceAll("\"", "");


        return cityService.create(nameCity, coordinates);
    }


    private JsonNode requestByGetCoordinate(String location) throws JsonProcessingException {

        String url = "https://geocode-maps.yandex.ru/1.x/?format=json&apikey=" + ApiKey + "&geocode=" + location;

        String responseEntity = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readTree(responseEntity);
    }

    private void checkFoundGeocode(JsonNode jsonNode, String location) {
        if (jsonNode.get("found").asText().equals("0")) {
            log.error("Not found Geocode for {}", location);
            throw new GeocodeNotFoundException("Not found Geocode");
        }
    }

}
