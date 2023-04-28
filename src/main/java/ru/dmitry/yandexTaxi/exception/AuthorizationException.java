package ru.dmitry.yandexTaxi.exception;

public class AuthorizationException extends Exception {
    public AuthorizationException() {
        super("You couldn't log in to your account");
    }
}
