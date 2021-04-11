package ru.javawebinar.graduation;

import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;
import ru.javawebinar.graduation.model.User;

import java.io.Serial;

@Getter
@ToString(of = "user")
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    @Serial
    private static final long serialVersionUID = 1L;

    private final User user;

    public AuthorizedUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int getId() {
        return user.id();
    }
}