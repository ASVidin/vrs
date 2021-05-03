package ru.javawebinar.graduation;

import ru.javawebinar.graduation.model.Dish;

public class DishTestData {

    public static final int DISH_ID = 3;

    public static Dish getNew() {
        return new Dish(null, "NEW_DISH", 333);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_ID, "Обновленное блюдо", 66);
    }
}
