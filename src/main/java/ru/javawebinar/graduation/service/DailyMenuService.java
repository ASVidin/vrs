package ru.javawebinar.graduation.service;

import ru.javawebinar.graduation.model.DailyMenu;

public interface DailyMenuService {
    DailyMenu save(DailyMenu menu, int restaurantId);

    int delete(int id, int restaurantId);
}
