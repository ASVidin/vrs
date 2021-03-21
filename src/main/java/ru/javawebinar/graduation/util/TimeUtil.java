package ru.javawebinar.graduation.util;

import java.time.LocalTime;

public class TimeUtil {
    public static boolean isBeforeDeadLine(LocalTime voteTime) {
        return voteTime.isBefore(LocalTime.of(11, 0));
    }
}
