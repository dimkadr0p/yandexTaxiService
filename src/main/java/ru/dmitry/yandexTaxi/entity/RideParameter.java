package ru.dmitry.yandexTaxi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RideParameter {
    private String distance;
    private String time;
    private String price;
}
