package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;

import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class UserTestData {

    public static void init(UserService service) {
        userId = service.getByEmail("user@yandex.ru").getId();
        adminId = service.getByEmail("admin@gmail.com").getId();
        USER.setId(userId);
        ADMIN.setId(adminId);
    }

    public static int userId = START_SEQ;
    public static int adminId = START_SEQ + 1;

    public static final User USER = new User(userId, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(adminId, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    public static final ModelMatcher<User> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getCaloriesPerDay(), actual.getCaloriesPerDay())
                            && Objects.equals(expected.isEnabled(), actual.isEnabled())
//                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );
}
