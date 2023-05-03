package ru.dmitry.yandexTaxi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmitry.yandexTaxi.entity.City;
import ru.dmitry.yandexTaxi.repository.CityRepository;

import java.util.Optional;


@Service
@Slf4j
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City create(String name, String coordinates) {
        log.info("Создание нового адреса в базу данных с адресом {} и координатами {}", name, coordinates);
        City city = City.builder().name(name).coordinates(coordinates).build();
        cityRepository.save(city);
        return city;
    }

    public Optional<City> findByName(String name) {
        log.info("Обращение в базу данных по адресу {}", name);
        return cityRepository.findByName(name);
    }

    public Optional<City> findByCoordinates(String coordinates) {
        return cityRepository.findByCoordinates(coordinates);
    }

}
