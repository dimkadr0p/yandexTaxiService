package ru.dmitry.yandexTaxi;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dmitry.yandexTaxi.config.SpringConfig;
import ru.dmitry.yandexTaxi.exception.AuthorizationException;
import ru.dmitry.yandexTaxi.service.YandexTaxiService;

import java.io.IOException;


@SpringBootApplication
public class YandexTaxiApplication {

	public static void main(String[] args) throws IOException, AuthorizationException, InterruptedException {
		SpringApplication.run(YandexTaxiApplication.class, args);
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		YandexTaxiService yandexTaxiService = applicationContext.getBean(YandexTaxiService.class);
		//System.out.println(yandexTaxiService.getRouteStats("Людиново", "Брянск"));
		System.out.println(yandexTaxiService.getRouteStats("Людиново", "Брянск"));

	}
}
