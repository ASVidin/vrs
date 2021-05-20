package ru.javawebinar.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.DailyMenu;
import ru.javawebinar.graduation.repository.DailyMenuRepositories;
import ru.javawebinar.graduation.repository.RestaurantRepository;

@Service
public class DailyMenuServiceImpl implements DailyMenuService {
    RestaurantRepository restaurantRepository;
    DailyMenuRepositories dailyMenuRepositories;

    public DailyMenuServiceImpl(RestaurantRepository restaurantRepository, DailyMenuRepositories dailyMenuRepositories) {
        this.dailyMenuRepositories = dailyMenuRepositories;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    @Override
    public DailyMenu save(DailyMenu menu, int restaurantId) {
        if (!menu.isNew() && dailyMenuRepositories.getWithRestaurant(menu.id(), restaurantId) == null) {
            return null;
        }
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return dailyMenuRepositories.save(menu);
    }

    @Override
    public int delete(int id, int restaurantId) {
        return dailyMenuRepositories.delete(id, restaurantId);
    }
}
