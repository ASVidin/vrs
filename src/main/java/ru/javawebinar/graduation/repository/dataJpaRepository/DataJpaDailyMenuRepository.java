package ru.javawebinar.graduation.repository.dataJpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.DailyMenu;
import ru.javawebinar.graduation.repository.DailyMenuRepository;

import java.time.LocalDate;

@Repository
public class DataJpaDailyMenuRepository implements DailyMenuRepository {
    RestaurantRepository restaurantRepository;
    CrudDailyMenuRepositories dailyMenuRepositories;

    public DataJpaDailyMenuRepository(RestaurantRepository restaurantRepository, CrudDailyMenuRepositories dailyMenuRepositories) {
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
    public int delete(int id) {
        return dailyMenuRepositories.delete(id);
    }
}
