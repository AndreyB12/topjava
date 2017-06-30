package ru.javawebinar.topjava.util.exception;

/**
 * Created by butkoav on 27.06.2017.
 */
public class DuplicateRowConstraint extends RuntimeException {
    public DuplicateRowConstraint(String message) {
        super(message);
    }
}
