package ru.javawebinar.graduation.repository.dataJpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Dish;
import ru.javawebinar.graduation.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {
    CrudDailyMenuRepositories dailyMenuRepositories;
    CrudDishRepository dishRepository;

    public DataJpaDishRepository(CrudDailyMenuRepositories dailyMenuRepositories, CrudDishRepository dishRepository) {
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
    public List<Dish> getByDate(LocalDate inputDate) {
        return dishRepository.getByDate(inputDate);
    }

    @Override
    public List<Dish> getByMenu(int menuId) {
        return dishRepository.getByMenu(menuId);
    }

    @Override
    public Dish getWithMenu(int id, int menuId) {
        return dishRepository.getWithMenu(id, menuId);
    }

    @Override
    public int delete(int id) {
        return dishRepository.delete(id);
    }
}
