package ru.javawebinar.graduation;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javawebinar.graduation.repository.DataJpaDishRepository;
import ru.javawebinar.graduation.repository.DishRepository;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.repository.UserRepository;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
@AllArgsConstructor
public class VrsApplication implements ApplicationRunner {
    private final DishRepository dishRepository;
    private final DataJpaDishRepository dataJpaDishRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public static void main(String[] args) {
        SpringApplication.run(VrsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(userRepository.save(UserTestData.getNew()));
        System.out.println(userRepository.save(UserTestData.getUpdated()));
        System.out.println(userRepository.delete(1));
        System.out.println(userRepository.findByEmailIgnoreCase("new@gmail.com"));

        System.out.println(restaurantRepository.save(RestaurantTestData.getNew()));
        System.out.println(restaurantRepository.delete(1));
        System.out.println(restaurantRepository.findAll());
        System.out.println(restaurantRepository.save(RestaurantTestData.getUpdated()));

        System.out.println(dishRepository.getAllMenuByDate(LocalDate.of(2021, Month.APRIL, 18)));
        System.out.println(dataJpaDishRepository.save(DishTestData.getNew(), 2));
        System.out.println(dataJpaDishRepository.save(DishTestData.getUpdated(), 2));
        System.out.println(dishRepository.getRestaurantMenuByDate(LocalDate.of(2020, Month.APRIL, 15), 2));
        System.out.println(dishRepository.delete(4));
        System.out.println(dishRepository.getAllRestaurantMenu(2));


    }

}
