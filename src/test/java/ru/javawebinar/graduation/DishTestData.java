package ru.javawebinar.graduation;

import ru.javawebinar.graduation.model.Dish;

import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDate.of;

public class DishTestData {

    public static final int DISH_ID_FOR_2_RESTAURANT = 3;

    public static Dish getNew() {
        return new Dish(null, "RIBS", 33, LocalDate.of(2021, Month.APRIL, 18));
    }

    public static Dish getUpdated() {
        return new Dish(DISH_ID_FOR_2_RESTAURANT, "Обновленное блюдо", 66, of(2020, Month.APRIL, 15));
    }
}
