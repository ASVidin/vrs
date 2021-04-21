package ru.javawebinar.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Query("SELECT d FROM Dish d WHERE d.dailyMenu.inputDate=:date ORDER BY d.dailyMenu.id")
    List<Dish> getByDate(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);

    @Query("SELECT d FROM Dish d WHERE d.dailyMenu.id=:menu")
    List<Dish> getByMenu(@Param("menu") int menuId);

    @Query("SELECT d FROM Dish d JOIN FETCH d.dailyMenu WHERE d.id = ?1 and d.dailyMenu.id = ?2")
    Dish getWithMenu(int id, int menuId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);
}
