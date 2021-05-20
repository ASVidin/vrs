package ru.javawebinar.graduation.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.graduation.model.DailyMenu;
import ru.javawebinar.graduation.model.Dish;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.service.DailyMenuServiceImpl;
import ru.javawebinar.graduation.service.DishServiceImpl;
import ru.javawebinar.graduation.util.TimeUtil;
import ru.javawebinar.graduation.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class DishController {
    static final String REST_URL = "/rest/restaurants/{restaurantId}/dishes";

    private final DishServiceImpl dishService;
    private final DailyMenuServiceImpl dailyMenuService;
    private final RestaurantRepository restaurantRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {
        LocalDate currentDate = TimeUtil.convertToLocalDate(LocalDateTime.now());

        DailyMenu dailyMenu = restaurantRepository.getWithDailyMenu(restaurantId).getMenu().stream()
                .filter(menu -> menu.getDate().isEqual(currentDate))
                .peek(menu -> log.info("Return existing menu {} for restaurant {}", menu, restaurantId))
                .findFirst()
                .orElseGet(() -> {
                    log.info("Create new menu for restaurant {}", restaurantId);
                    return dailyMenuService.save(new DailyMenu(currentDate), restaurantId);
                });

        Dish created = new Dish(dish);
        ValidationUtil.checkNew(created);
        dishService.save(created, dailyMenu.id());

        return new ResponseEntity<>(dish, HttpStatus.OK);
    }
}
