package ru.javawebinar.graduation.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.graduation.model.DailyMenu;
import ru.javawebinar.graduation.model.Dish;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.repository.dataJpaRepository.DataJpaDailyMenuRepository;
import ru.javawebinar.graduation.repository.dataJpaRepository.DataJpaDishRepository;
import ru.javawebinar.graduation.repository.dataJpaRepository.RestaurantRepository;
import ru.javawebinar.graduation.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class DishController {
    static final String REST_URL = "/rest/restaurant/menu/dish";

    private final DataJpaDishRepository dishRepository;
    private final DataJpaDailyMenuRepository dailyMenuRepository;
    private final RestaurantRepository restaurantRepository;

    @PostMapping(value = "/created", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Dish> create(@RequestBody Dish dish, @RequestBody Restaurant restaurant) {
        LocalDate currentDate = TimeUtil.convertToLocalDate(LocalDateTime.now());

        DailyMenu dailyMenu = restaurantRepository.getWithDailyMenu(restaurant.id()).getMenu().stream()
                .filter(menu -> menu.getInputDate().isEqual(currentDate))
                .peek(menu -> log.info("Return existing menu {} for restaurant {}", menu, restaurant))
                .findFirst()
                .orElseGet(() -> {
                    log.info("Create new menu for restaurant {}", restaurant);
                    return dailyMenuRepository.save(new DailyMenu(currentDate), restaurant.id());
                });

        Dish created = new Dish(dish);
        dishRepository.save(created, dailyMenu.id());

        return new ResponseEntity<>(dish, HttpStatus.OK);
    }
}
