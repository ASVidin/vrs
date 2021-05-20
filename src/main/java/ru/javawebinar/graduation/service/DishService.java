package ru.javawebinar.graduation.service;

import ru.javawebinar.graduation.model.Dish;

public interface DishService {
    Dish save(Dish dish, int menuId);

    int delete(int id);
}
