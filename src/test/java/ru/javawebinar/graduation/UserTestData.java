package ru.javawebinar.graduation;

import ru.javawebinar.graduation.model.Role;
import ru.javawebinar.graduation.model.User;

import java.util.Collections;

public class UserTestData {

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static final User user = new User(USER_ID, "User_First", "user@gmail.com", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin_First", "admin@ya.ru", "admin", Role.ADMIN, Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
