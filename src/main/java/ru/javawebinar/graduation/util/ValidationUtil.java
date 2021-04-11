package ru.javawebinar.graduation.util;

import ru.javawebinar.graduation.model.AbstractEntity;

public class ValidationUtil {

    public static void checkNew(AbstractEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    //  http://stackoverflow.com/a/32728226/548473
    public static void assureIdConsistent(AbstractEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
            throw new IllegalArgumentException(entity + " must has id=" + id);
        }
    }
}