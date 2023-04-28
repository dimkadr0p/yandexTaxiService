package ru.dmitry.yandexTaxi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.dmitry.yandexTaxi.entity.RideParameter;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
public final class YandexTaxiService {

    private final GeocodeService geocodeService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();

    public YandexTaxiService(GeocodeService geocodeService) throws IOException {
        this.geocodeService = geocodeService;
    }

    public RideParameter getRouteStats(String where_from, String where) throws JsonProcessingException {

        BigDecimal[] oneLocation = convertToDoubleArr(geocodeService.getGeocodeCoordinates(where_from).split(" "));
        BigDecimal[] twoLocation = convertToDoubleArr(geocodeService.getGeocodeCoordinates(where).split(" "));

        String jsonBody = "{\"route\":[["+ oneLocation[0] +"," +oneLocation[1] +"],["+ twoLocation[0] + "," + twoLocation[1] + "]], \"extended_description\": true}";

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/112.0");
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        String resp = restTemplate.postForObject("https://ya-authproxy.taxi.yandex.ru/3.0/routestats", request, String.class);

        return getResultParsJson(resp);

    }

    private BigDecimal[] convertToDoubleArr(String[] strings) {
        BigDecimal[] doubles = new BigDecimal[strings.length];

        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = new BigDecimal(strings[i]);
        }

        return doubles;
    }

    private RideParameter getResultParsJson(String resp) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(resp);
        String distance = jsonNode.get("distance").toString();
        String time = jsonNode.get("time").toString();
        String price = jsonNode.get("service_levels").get(0).get("description").toString().split(" ")[1];

        return new RideParameter(distance, time, price);
    }

}