package ru.javawebinar.graduation;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.repository.dataJpaRepository.DataJpaDailyMenuRepository;
import ru.javawebinar.graduation.repository.dataJpaRepository.UserRepository;
import ru.javawebinar.graduation.web.DailyMenuController;
import ru.javawebinar.graduation.web.DishController;
import ru.javawebinar.graduation.web.RestaurantController;
import ru.javawebinar.graduation.web.VoteController;

import java.util.Objects;

@SpringBootApplication
@AllArgsConstructor
public class VrsApplication implements ApplicationRunner {
    private final UserRepository userRepository;
    private final DataJpaDailyMenuRepository dailyMenuRepositories;
    private final VoteController voteController;
    private final RestaurantController restaurantController;
    private final DishController dishController;
    private final DailyMenuController dailyMenuController;

    public static void main(String[] args) {
        SpringApplication.run(VrsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {

        System.out.println("---User---");
        System.out.println(userRepository.save(UserTestData.getNew()));
        System.out.println(userRepository.save(UserTestData.getUpdated()));
        System.out.println(userRepository.findByEmailIgnoreCase("new@gmail.com"));
        System.out.println(userRepository.delete(3));
        System.out.println("---Restaurant---");
        System.out.println(restaurantController.create(RestaurantTestData.getNew()));
        Restaurant restaurant = restaurantController.get(2).getBody();
        System.out.println(restaurant);
        System.out.println("---DailyMenu---");
        System.out.println(dailyMenuController.create(restaurant, DailyMenuTestData.getNew()).getBody());
        System.out.println("---Dish---");
        System.out.println(dishController.create(DishTestData.getNew(), Objects.requireNonNull(restaurant)));
        System.out.println("---Vote---");
        System.out.println(voteController.getAll());
        System.out.println(voteController.createOrUpdate(Objects.requireNonNull(restaurant)));
        System.out.println(voteController.get(7));
    }
}
