package ru.javawebinar.graduation;

import ru.javawebinar.graduation.model.Vote;

import java.time.LocalDate;
import java.time.Month;

public class VoteTestData {
    public static Vote getNew() {
        return new Vote(null, LocalDate.of(2021, Month.APRIL, 18));
    }
}
