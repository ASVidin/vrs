package ru.javawebinar.graduation.repository;

import ru.javawebinar.graduation.model.DailyMenu;

public interface DailyMenuRepository {
    DailyMenu save(ru.javawebinar.graduation.model.DailyMenu menu, int restaurantId);

    int delete(int id);
}
