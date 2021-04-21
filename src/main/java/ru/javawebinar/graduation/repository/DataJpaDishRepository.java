package ru.javawebinar.graduation.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Dish;

@Repository
public class DataJpaDishRepository {
    DailyMenuRepositories dailyMenuRepositories;
    DishRepository dishRepository;

    public DataJpaDishRepository(DailyMenuRepositories dailyMenuRepositories, DishRepository dishRepository) {
        this.dailyMenuRepositories = dailyMenuRepositories;
        this.dishRepository = dishRepository;
    }

    @Transactional
    public Dish save(Dish dish, int menuId) {
        if (!dish.isNew() && dishRepository.getWithMenu(dish.getId(), menuId) == null) {
            return null;
        }
        dish.setDailyMenu(dailyMenuRepositories.getOne(menuId));
        return dishRepository.save(dish);
    }
}
