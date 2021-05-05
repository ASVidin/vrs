package ru.javawebinar.graduation.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.graduation.model.DailyMenu;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.repository.dataJpaRepository.DataJpaDailyMenuRepository;

@RestController
@RequestMapping(value = DailyMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class DailyMenuController {
    static final String REST_URL = "/rest/restaurant/menu";

    private final DataJpaDailyMenuRepository dailyMenuRepository;

    @PostMapping(value = "/created", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<DailyMenu> create(@RequestBody Restaurant restaurant, @RequestBody DailyMenu dailyMenu) {
        log.info("created for {}", restaurant);
        DailyMenu newMenu = dailyMenuRepository.save(dailyMenu, restaurant.id());

        return new ResponseEntity<>(newMenu, HttpStatus.OK);
    }
}
