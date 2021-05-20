package ru.javawebinar.graduation.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.graduation.model.DailyMenu;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.service.DailyMenuServiceImpl;
import ru.javawebinar.graduation.util.ValidationUtil;

@RestController
@RequestMapping(value = DailyMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class DailyMenuController {
    static final String REST_URL = "/rest/restaurants/{restaurantId}/menus";

    private final DailyMenuServiceImpl dailyMenuService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DailyMenu> create(@RequestBody DailyMenu dailyMenu, @PathVariable("restaurantId") int restaurantId) {
        log.info("created menu for restaurant {}", restaurantId);
        ValidationUtil.checkNew(dailyMenu);
        DailyMenu newMenu = dailyMenuService.save(dailyMenu, restaurantId);
        return new ResponseEntity<>(newMenu, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody DailyMenu dailyMenu, @PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        log.info("Update {} with id {} for restaurant {}", dailyMenu, id, restaurantId);
        ValidationUtil.assureIdConsistent(dailyMenu, id);
        dailyMenuService.save(dailyMenu, restaurantId);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        log.info("Delete DailyMenu {} for restaurant {}", id, restaurantId);
        dailyMenuService.delete(id, restaurantId);
    }
}
