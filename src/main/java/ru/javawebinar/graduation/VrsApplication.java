package ru.javawebinar.graduation;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javawebinar.graduation.repository.UserRepository;

@SpringBootApplication
@AllArgsConstructor
public class VrsApplication implements ApplicationRunner {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(VrsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(userRepository.findAll());
    }
}
