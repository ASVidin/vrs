package ru.javawebinar.graduation.repository;

import ru.javawebinar.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int menuId);

    List<Dish> getByDate(LocalDate inputDate);

    List<Dish> getByMenu(int menuId);

    default Dish getWithMenu(int id, int menuId) {
        throw new UnsupportedOperationException();
    }

    int delete(int id);


}
