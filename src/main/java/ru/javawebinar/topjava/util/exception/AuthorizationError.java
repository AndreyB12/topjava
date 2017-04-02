package ru.javawebinar.topjava.util.exception;

/**
 * Created by butkoav on 02.04.2017.
 */
public class AuthorizationError extends RuntimeException {
    public AuthorizationError(String message) {
        super(message);
    }
}
