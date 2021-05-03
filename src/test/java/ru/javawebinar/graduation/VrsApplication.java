package ru.javawebinar.graduation;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javawebinar.graduation.repository.dataJpaRepository.*;
import ru.javawebinar.graduation.web.VoteController;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
@AllArgsConstructor
public class VrsApplication implements ApplicationRunner {
    private final DataJpaDishRepository dishRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final DataJpaDailyMenuRepository dailyMenuRepositories;
    private final VoteController voteController;

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
        System.out.println(restaurantRepository.save(RestaurantTestData.getNew()));
        System.out.println(restaurantRepository.findAll());
        System.out.println(restaurantRepository.save(RestaurantTestData.getUpdated()));
        System.out.println(restaurantRepository.delete(3));
        System.out.println("---DailyMenu---");
        System.out.println(dailyMenuRepositories.getByRestaurant(1));
        System.out.println(dailyMenuRepositories.delete(2));
        System.out.println(dailyMenuRepositories.save(DailyMenuTestData.getNew(), 4));
        System.out.println(dailyMenuRepositories.getByDate(LocalDate.now()));
        System.out.println("---Dish---");
        System.out.println(dishRepository.save(DishTestData.getUpdated(), 3));
        System.out.println(dishRepository.getByDate(LocalDate.of(2021, Month.JANUARY, 30)));
        System.out.println(dishRepository.save(DishTestData.getNew(), 4));
        System.out.println(dishRepository.getByMenu(4));
        System.out.println(dishRepository.delete(1));
        System.out.println("---Vote---");
        System.out.println(voteController.getAll());
        System.out.println(voteController.createOrUpdate(dailyMenuRepositories.getByRestaurant(1).get(0)));
        System.out.println(voteController.get(7));
    }
}
