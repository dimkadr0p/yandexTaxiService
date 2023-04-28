package ru.dmitry.yandexTaxi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.dmitry.yandexTaxi.entity.UserAccountYandex;
import ru.dmitry.yandexTaxi.service.GeocodeService;
import ru.dmitry.yandexTaxi.service.YandexTaxiService;

import java.io.IOException;

@Configuration
@PropertySource("application.properties")
public class SpringConfig {

    @Bean
    public YandexTaxiService yandexTaxiService() throws IOException {
        return new YandexTaxiService(geocodeService());
    }
    @Bean
    public GeocodeService geocodeService() {
        return new GeocodeService();
    }

    @Bean
    public UserAccountYandex userAccountYandex() {
        return new UserAccountYandex();
    }
}
