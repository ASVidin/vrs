package ru.javawebinar.graduation;

import ru.javawebinar.graduation.model.DailyMenu;

import java.time.LocalDate;

public class DailyMenuTestData {
    public static DailyMenu getNew() {
        return new DailyMenu(null, LocalDate.now());
    }
}
