package ru.dmitry.yandexTaxi;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import ru.dmitry.yandexTaxi.entity.UserAccountYandex;
import ru.dmitry.yandexTaxi.exception.AuthorizationException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;


//TODO::Вернуть с класса cookie полученные с авторизации

public class YandexAuthorization {

    private final UserAccountYandex userAccountYandex;

    public YandexAuthorization(UserAccountYandex userAccountYandex) {
        this.userAccountYandex = userAccountYandex;
    }

    @PostConstruct
    public Map<String, String> auth() throws IOException, AuthorizationException {

        Connection.Response document = Jsoup.connect("https://passport.yandex.com/auth/")
                .data("login", userAccountYandex.getLogin())
                .data("passwd", userAccountYandex.getPassword())
                .header("Connection", "keep-alive")
                .method(Connection.Method.POST).execute();

        checkAuth(document);

        return document.cookies();
    }

    private void checkAuth(Connection.Response document) throws AuthorizationException {
        if(!document.cookies().toString().contains(userAccountYandex.getLogin())) {
            throw new AuthorizationException();
        }
    }
}
