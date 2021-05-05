package ru.javawebinar.graduation;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javawebinar.graduation.repository.dataJpaRepository.DataJpaDailyMenuRepository;
import ru.javawebinar.graduation.repository.dataJpaRepository.UserRepository;
import ru.javawebinar.graduation.web.*;

@SpringBootApplication
@AllArgsConstructor
public class VrsApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(VrsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
    }
}
