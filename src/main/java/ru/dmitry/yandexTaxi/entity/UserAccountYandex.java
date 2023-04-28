package ru.dmitry.yandexTaxi.entity;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public final class UserAccountYandex {
    @Value("${yandex.account}")
    private String login;
    @Value("${yandex.password}")
    private String password;
}
