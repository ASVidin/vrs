package ru.javawebinar.graduation.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.DailyMenu;

@Repository
public class DataJpaDailyMenuRepository {
    RestaurantRepository restaurantRepository;
    DailyMenuRepositories dailyMenuRepositories;

    public DataJpaDailyMenuRepository(RestaurantRepository restaurantRepository, DailyMenuRepositories dailyMenuRepositories) {
        this.dailyMenuRepositories = dailyMenuRepositories;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public DailyMenu save(DailyMenu menu, int restaurantId) {
        if (!menu.isNew() && dailyMenuRepositories.getWithRestaurant(menu.getId(), restaurantId) == null) {
            return null;
        }
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return dailyMenuRepositories.save(menu);
    }
}
