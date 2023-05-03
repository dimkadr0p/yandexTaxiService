package ru.dmitry.yandexTaxi.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dmitry.yandexTaxi.entity.Trip;
import ru.dmitry.yandexTaxi.exception.RouteNotFoundException;
import ru.dmitry.yandexTaxi.service.YandexTaxiService;
import ru.dmitry.yandexTaxi.util.RouteErrorResponse;


@RestController
@RequestMapping("/route")
public class YandexController {

    private final YandexTaxiService yandexTaxiService;

    @Autowired
    public YandexController(YandexTaxiService yandexTaxiService) {
        this.yandexTaxiService = yandexTaxiService;
   }

   @RequestMapping(path = "/{where_from}/{where}", method = RequestMethod.GET)
   public Trip getRoute(@PathVariable String where_from, @PathVariable String where) throws JsonProcessingException {
       return yandexTaxiService.getRouteStats("Россия, Город Москва, Москва, округ Тверской, 109012, Красная Площадь 3", "Брянск");
   }

   @ExceptionHandler
   private ResponseEntity<RouteErrorResponse> handleException(RouteNotFoundException ex) {
        RouteErrorResponse response = new RouteErrorResponse(
                "Route not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
   }

}

