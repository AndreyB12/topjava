package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        //  System.out.format("Hello Topjava Enterprise!");

        List<User> users = new ArrayList<>();

        users.add(new User(1, "Andrey1", "mail1", "", Role.ROLE_ADMIN));
        users.add(new User(1, "andrey", "mail2", "", Role.ROLE_ADMIN));
        users.add(new User(1, "Andrey2", "mail1", "", Role.ROLE_ADMIN));
        users.add(new User(1, "Andrey", "mail1", "", Role.ROLE_ADMIN));
        users.add(new User(1, "andrey2", "mail2", "", Role.ROLE_ADMIN));

        users.sort(Comparator.comparing((User u) -> u.getName().toLowerCase())
                .thenComparing((User u)->u.getEmail().toLowerCase()));

        users.forEach(System.out::println);

    }
}
