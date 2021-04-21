package ru.javawebinar.graduation;

import ru.javawebinar.graduation.model.Restaurant;

public class RestaurantTestData {
    public static final int RESTAURANT_ID = 2;

    public static Restaurant getNew() {
        return new Restaurant(null, "New_Rest");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Обновленный ресторан");
    }
}
