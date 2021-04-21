package ru.javawebinar.graduation;

import ru.javawebinar.graduation.model.DailyMenu;

import java.time.LocalDate;
import java.time.Month;

public class DailyMenuTestData {
    public static DailyMenu getNew() {
        return new DailyMenu(null, LocalDate.of(2021, Month.APRIL, 18));
    }
}
