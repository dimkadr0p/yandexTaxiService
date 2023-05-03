package ru.dmitry.yandexTaxi.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip {
    private String distance;
    private String time;
    private String price;

    @Override
    public String toString() {
        return "Trip{" +
                "distance='" + distance + '\'' +
                ", time='" + time + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}
