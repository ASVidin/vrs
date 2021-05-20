package ru.javawebinar.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.DailyMenu;

@Transactional(readOnly = true)
public interface DailyMenuRepositories extends JpaRepository<DailyMenu, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM DailyMenu m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM DailyMenu m JOIN FETCH m.restaurant WHERE m.id = ?1 AND m.restaurant.id = ?2")
    DailyMenu getWithRestaurant(int id, int restaurantId);
}
