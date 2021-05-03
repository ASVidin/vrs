package ru.javawebinar.graduation.repository;

import ru.javawebinar.graduation.model.DailyMenu;

import java.time.LocalDate;
import java.util.List;

public interface DailyMenuRepository {
    DailyMenu save(ru.javawebinar.graduation.model.DailyMenu menu, int restaurantId);

    int delete(int id);

    List<DailyMenu> getByDate(LocalDate inputDate);

    List<DailyMenu> getByRestaurant(int restaurantId);

    default DailyMenu getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }
}
