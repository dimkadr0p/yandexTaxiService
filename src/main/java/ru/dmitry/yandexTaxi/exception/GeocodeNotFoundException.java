package ru.dmitry.yandexTaxi.exception;

public class GeocodeNotFoundException extends RuntimeException {
    public GeocodeNotFoundException(String str) {
        super(str);
    }
}
