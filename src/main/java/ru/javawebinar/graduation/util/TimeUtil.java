package ru.javawebinar.graduation.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtil {
    private static final LocalTime DEADLINE = LocalTime.of(11, 0);

    public static boolean isBeforeDeadLine(LocalDateTime voteTime) {
        return convertToLocalTime(voteTime).isBefore(DEADLINE);
    }

    private static LocalTime convertToLocalTime(LocalDateTime time) {
        return time.toLocalTime();
    }

    private TimeUtil() {
    }

    public static LocalDate convertToLocalDate(LocalDateTime time) {
        return time.toLocalDate();
    }
}
