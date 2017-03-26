package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.storage.MealsStorage;
import ru.javawebinar.topjava.storage.MealsStorageInMemory;

/**
 * Created by butkoav on 26.03.2017.
 */
public class StorageUtils {

    private static final MealsStorage storage = new MealsStorageInMemory();

    public static MealsStorage getStorage() {
        return storage;
    }
}
