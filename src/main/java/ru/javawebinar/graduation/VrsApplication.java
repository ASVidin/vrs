package ru.javawebinar.graduation;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class VrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(VrsApplication.class, args);
    }

}
