package ru.javawebinar.graduation.service;

import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

public interface VoteService {

    Vote get(int id, int userId);

    Vote save(Vote vote, Restaurant restaurant, int userId);

    int delete(int id);

    Optional<Vote> getByDateForUser(LocalDate voteDate, int userId);
}
