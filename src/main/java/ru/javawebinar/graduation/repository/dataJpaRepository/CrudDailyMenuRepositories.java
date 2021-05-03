package ru.javawebinar.graduation.repository.dataJpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.DailyMenu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDailyMenuRepositories extends JpaRepository<DailyMenu, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM DailyMenu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM DailyMenu m WHERE m.inputDate=:date ORDER BY m.restaurant.id")
    List<DailyMenu> getByDate(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);

    @Query("SELECT m FROM DailyMenu m WHERE m.restaurant.id=:restaurant")
    List<DailyMenu> getByRestaurant(@Param("restaurant") int restaurantId);

    @Query("SELECT m FROM DailyMenu m JOIN FETCH m.restaurant WHERE m.id = ?1 and m.restaurant.id = ?2")
    DailyMenu getWithRestaurant(int id, int restaurantId);
}
