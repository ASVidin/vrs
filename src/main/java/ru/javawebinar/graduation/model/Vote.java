package ru.javawebinar.graduation.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Vote {
    private static final LocalTime DEADLINE_TIME = LocalTime.of(11, 0);

    private LocalDateTime voteTime;
    private User fromUser;
    private Restaurant toRestaurant;
}
