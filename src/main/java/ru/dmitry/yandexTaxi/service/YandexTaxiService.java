package ru.dmitry.yandexTaxi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.dmitry.yandexTaxi.convert.ConvertToBigDecimal;
import ru.dmitry.yandexTaxi.entity.Trip;
import ru.dmitry.yandexTaxi.exception.RouteNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;


@Slf4j
@Service
public final class YandexTaxiService {

    private final GeocodeService geocodeService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();


    @Autowired
    public YandexTaxiService(GeocodeService geocodeService) {
        this.geocodeService = geocodeService;
    }


    public Trip getRouteStats(String where_from, String where) throws JsonProcessingException {

        BigDecimal[] oneLocation = ConvertToBigDecimal.convert(geocodeService.getGeocodeCoordinates(where_from).getCoordinates().split(" "));
        BigDecimal[] twoLocation = ConvertToBigDecimal.convert(geocodeService.getGeocodeCoordinates(where).getCoordinates().split(" "));

        String jsonBody = "{\"route\":[["+ oneLocation[0] +"," +oneLocation[1] +"],["+ twoLocation[0] + "," + twoLocation[1] + "]], \"extended_description\": true}";

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/112.0");
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        String resp = restTemplate.postForObject("https://ya-authproxy.taxi.yandex.ru/3.0/routestats", request, String.class);

        Optional<Trip> trip = Optional.ofNullable(getResultParsJson(resp));
        return trip.orElseThrow(RouteNotFoundException::new);

    }


    private Trip getResultParsJson(String resp) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(resp);
        String distance = jsonNode.get("distance").asText();
        String time = jsonNode.get("time").asText();
        String price = jsonNode.get("service_levels").get(0).get("description").asText().split(".,")[0];

        return Trip.builder().
                distance(distance).time(time).
                price(price).build();
    }


}