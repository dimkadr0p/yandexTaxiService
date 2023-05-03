package ru.dmitry.yandexTaxi.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dmitry.yandexTaxi.entity.City;
import ru.dmitry.yandexTaxi.service.CityService;

@RestController
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("tg/address/")
    public City create(@RequestBody City city) {
        return cityService.create(city.getName(), city.getCoordinates());
    }

}
