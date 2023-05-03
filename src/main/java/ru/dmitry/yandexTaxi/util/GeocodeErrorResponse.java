package ru.dmitry.yandexTaxi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeocodeErrorResponse {
    private String message;
    private long timestamp;
}
