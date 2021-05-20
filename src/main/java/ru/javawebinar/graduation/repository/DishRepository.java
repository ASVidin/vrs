package ru.javawebinar.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Dish;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Query("SELECT d FROM Dish d JOIN FETCH d.dailyMenu WHERE d.id = ?1 and d.dailyMenu.id = ?2")
    Dish getWithMenu(int id, int menuId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);
}
