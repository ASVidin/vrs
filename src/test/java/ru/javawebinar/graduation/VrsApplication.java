package ru.javawebinar.graduation;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javawebinar.graduation.repository.*;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
@AllArgsConstructor
public class VrsApplication implements ApplicationRunner {
    private final DishRepository dishRepository;
    private final DataJpaDishRepository dataJpaDishRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final DailyMenuRepositories dailyMenuRepositories;
    private final DataJpaDailyMenuRepository dataJpaDailyMenuRepository;
    private final VoteRepository voteRepository;
    private final DataJpaVoteRepository dataJpaVoteRepository;

    public static void main(String[] args) {
        SpringApplication.run(VrsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(userRepository.save(UserTestData.getNew()));
        System.out.println(userRepository.save(UserTestData.getUpdated()));
//        System.out.println(userRepository.delete(1));
        System.out.println(userRepository.findByEmailIgnoreCase("new@gmail.com"));

        System.out.println(restaurantRepository.save(RestaurantTestData.getNew()));
//        System.out.println(restaurantRepository.delete(1));
        System.out.println(restaurantRepository.findAll());
        System.out.println(restaurantRepository.save(RestaurantTestData.getUpdated()));

        System.out.println(dailyMenuRepositories.findAll());
        System.out.println(dailyMenuRepositories.getByRestaurant(1));
        System.out.println(dailyMenuRepositories.getByDate(LocalDate.of(2021, Month.APRIL, 21)));
        System.out.println(dataJpaDailyMenuRepository.save(DailyMenuTestData.getNew(), 4));

        System.out.println(dataJpaDishRepository.save(DishTestData.getUpdated(), 3));
        System.out.println(dishRepository.getByDate(LocalDate.of(2021, Month.JANUARY, 30)));
        System.out.println(dataJpaDishRepository.save(DishTestData.getNew(), 4));
        System.out.println(dishRepository.getByMenu(4));
        System.out.println(dishRepository.delete(1));

        voteRepository.findAll()
                .forEach(System.out::println);
        System.out.println(voteRepository.delete(1));
        voteRepository.getAllByDate(LocalDate.of(2021, Month.JANUARY, 27))
                .forEach(System.out::println);
        voteRepository.getByDateForUser(LocalDate.of(2021, Month.JANUARY, 27), 1)
                .forEach(System.out::println);
        voteRepository.getAllByRestaurant(3)
                .forEach(System.out::println);
        System.out.println(dataJpaVoteRepository.save(VoteTestData.getNew(), 4, 3));
    }
}
