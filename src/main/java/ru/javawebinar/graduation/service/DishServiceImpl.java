package ru.javawebinar.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Dish;
import ru.javawebinar.graduation.repository.DailyMenuRepositories;
import ru.javawebinar.graduation.repository.DishRepository;

@Service
public class DishServiceImpl implements DishService {
    DailyMenuRepositories dailyMenuRepositories;
    DishRepository dishRepository;

    public DishServiceImpl(DailyMenuRepositories dailyMenuRepositories, DishRepository dishRepository) {
        this.dailyMenuRepositories = dailyMenuRepositories;
        this.dishRepository = dishRepository;
    }

    @Transactional
    @Override
    public Dish save(Dish dish, int menuId) {
        if (!dish.isNew() && dishRepository.getWithMenu(dish.id(), menuId) == null) {
            return null;
        }
        dish.setDailyMenu(dailyMenuRepositories.getOne(menuId));
        return dishRepository.save(dish);
    }

    @Override
    public int delete(int id) {
        return dishRepository.delete(id);
    }
}
