package ru.javawebinar.graduation.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.util.ValidationUtil;

import static ru.javawebinar.graduation.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class RestaurantController {
    static final String REST_URL = "/rest/restaurants";

    private final RestaurantRepository restaurantRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        log.info("created {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        restaurant = restaurantRepository.save(restaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        log.info("Update {} with id = {}", restaurant, id);
        ValidationUtil.assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("Delete Restaurant {}", id);
        restaurantRepository.delete(id);
    }
}
